package com.test.spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.spring.data.UserDTO;
import com.test.spring.service.UserService;

@Controller
public class UserController {

	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}	
	
	@GetMapping("/userinfo")
	public String userInfo(Model model, HttpSession session) {
		
		String auth = (String)session.getAttribute("auth");
		UserDTO udto = service.userInfo(auth);
		
		udto.setBirth(udto.getBirth().substring(0, 10));
		udto.setRegdate(udto.getRegdate().substring(0, 10));
		
		model.addAttribute("udto", udto);
		
		if(auth == null) {
			return "redirect: /spring/login";
		} else {
			return "board.userInfo";	
		}
		
	}
	
	@GetMapping("/userinfo/edit")
	public String userInfoEdit(Model model, HttpSession session) {
		
		String auth = (String)session.getAttribute("auth");
		UserDTO udto = service.userInfo(auth);
		
		udto.setBirth(udto.getBirth().substring(0, 10));
		udto.setRegdate(udto.getRegdate().substring(0, 10));
		
		model.addAttribute("udto", udto);
		
		return "board.infoedit";
	}
	
	
	@PostMapping("/userinfo/editok")
	public String userInfoChange(HttpSession session, UserDTO udto) {
		
		udto.setUserseq((String)session.getAttribute("auth"));
		System.out.println(udto.toString());
		
		int result = service.userUpdate(udto);
		
		if(result == 1) {
			return "redirect:/userinfo";			
		} else {
			return "redirect:/userinfo/edit";
		}
		
	}
	
	
	@GetMapping("/userinfo/reset")
	public String userInfoReset(HttpSession session) {
		
		//../resources/images/tempimage/userid.ext 로 저장된 임시 프로필 파일들 삭제하기위해 
		
		
		return "redirect:spring/userinfo";
	}
	
	
	
	@GetMapping("/userpic")
	public String userPic() {
		
		return "userpic";
	}	
	
	
	@PostMapping("/adduserpic")
	public String addpic(HttpServletRequest req, HttpSession session, Model model) {
		
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;
		
		MultipartFile file = multi.getFile("pic");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		
		//파일업로드는 이미 요청이 끝나면 완료됨 > 스프링 임시 폴더에 저장 > 우리가 원하는곳으로 이동해야
		
		String path = req.getRealPath("resources/profiles");
		System.out.println(path);
	
		
		//파일 이름 바꾸기
		UserDTO udto = service.userInfo((String)session.getAttribute("auth"));
		
		String filename = file.getOriginalFilename();
		int index = filename.lastIndexOf(".");
		String tempExt = filename.substring(index);
		
		filename = udto.getId() + tempExt;
		
		//파일 이동하기
		File file2 = new File(path + "\\" + filename );
		
		try {
			
			
			//file.renameTo(file2)
			file.transferTo(file2); //임시폴더 > 업로드 폴더
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("filename", filename);
		return "userpicok";
	}
	
	
	@GetMapping("/register")
	public String userRegist() {
		
		return "board.regist";
	}
	
	@PostMapping("/register/chkid")
	public void chkId(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		int result = service.chkId(id);
		
		//아이디가 정해진 패턴에 맞는지 확인 : 8자 이상, 12자 이하 영소문자+숫자 & 무조건 소문자로 시작해야한다.  
		boolean matches = id.matches("^[a-z]{1,1}(?=.*[A-Za-z])(?=.*\\d)[a-z\\d]{7,11}$");
		System.out.println("matches : " + matches);
		
		
		
		//json 반환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			
			writer.print("{");
			writer.printf("\"result\" : %d, ", result);
			writer.printf("\"matches\" : \"%s\"", matches);
			writer.print("}");
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@PostMapping("/register/chknick")
	public void chkNick(HttpServletRequest req, HttpServletResponse resp) {
		String nick = req.getParameter("nick");
		int result = service.chkNick(nick);
		
		boolean matches = nick.matches("^[가-힣a-zA-Z0-9]{2,8}$");
		System.out.println("matches : " + matches);

		
		//json 반환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			
			writer.print("{");
			writer.printf("\"result\" : %d, ", result);
			writer.printf("\"matches\" : \"%s\"", matches);
			writer.print("}");
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@PostMapping("/registok")
	public String registOk(UserDTO udto) {
		
		System.out.println(udto.toString());
		
		int result = service.regist(udto);
		
		return "redirect:/board";
	}
	
}
