package com.test.spring;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.spring.data.BoardDTO;
import com.test.spring.data.UserDTO;
import com.test.spring.service.BoardService;

@Controller
public class BoardController {

	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}	
	
	@GetMapping("/login")
	public String login(String result, Model model) {
		
//		if(result != null) {
//			model.addAttribute("result", result);
//		}
//		
		return "login";
	}
	
	@GetMapping("/logout")
	public String login(HttpSession session) {
		
		session.removeAttribute("auth");
		
		return "redirect:board";
	}
	
	
	@GetMapping("/board")
	public String board(HttpSession session, Model model) {
		
		model.addAttribute("auth", session.getAttribute("auth"));
		
		List<BoardDTO> list = service.list();
		
		model.addAttribute("list", list);
				
		return "board.list";
	
	}
	
	
	@PostMapping("/loginok")
	public String loginok(UserDTO dto, HttpSession session) {
		
		String result = service.login(dto);
		
		if(result != null) {
			session.setAttribute("auth", result);
			return "redirect:/board";
			
		} else {
			
			return "redirect:/login?result=failed"; 
		}
		
		
	}
	
	
	@GetMapping("/add")
	public String add(Model model) {
			
		List<BoardDTO> category = service.category();
		
		model.addAttribute("category", category);
		
		return "board.add";	
	}

	
	@PostMapping("/addok")
	public String add(HttpSession session, BoardDTO bdto) {
		
		String userseq = (String)(session.getAttribute("auth"));
		
		bdto.setUserseq(userseq);
		
		int result = service.add(bdto);
		
		String contentseq = service.addcontentseq(userseq);
			
		return "redirect: /spring/view?contentseq=" + contentseq;	
		
	}
	

	@GetMapping("/view")
	public String view(Model model, String contentseq, HttpSession session) {
		
		BoardDTO dto = service.view(contentseq);
		
		model.addAttribute("dto", dto);
		model.addAttribute("auth", session.getAttribute("auth"));
		return "board.view";	
	}
	
	
	@GetMapping("/edit")
	public String edit(String contentseq, Model model) {
		
		BoardDTO dto = service.view(contentseq);
		
		model.addAttribute("dto", dto);
		return "board.add";
		
	}
	
	@PostMapping("/editok")
	public String editok(BoardDTO bdto) {
				
		service.edit(bdto);
		
		String contentseq = bdto.getContentseq();
			
		return "redirect: /spring/view?contentseq=" + contentseq;	
		
	}
	
	@GetMapping("/del")
	public String del(String contentseq, Model model) {
		
		BoardDTO dto = service.view(contentseq);
		if(dto.getSubject().length() > 15) {
			dto.setSubject(dto.getSubject().substring(0, 15)+ "...");
		}
		
		model.addAttribute("dto", dto);
	
		return "del";
	}
	
	@GetMapping("/delok")
	public String delok(String contentseq) {
		
		
		service.del(contentseq);
		
		return "redirect: /spring/board";
	}
	
	
	
	
}
