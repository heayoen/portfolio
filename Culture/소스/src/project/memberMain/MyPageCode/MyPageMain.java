package project.memberMain.MyPageCode;

import java.util.Calendar;
import java.util.Scanner;

import project.login.userLogin;

/**
 * 마이페이지메인 메뉴 호출하는 클래스
 * @author 황혜연
 *
 */
public class MyPageMain {
	
	private static String userId;
	
	/**
	 * 
	 * @param userId 로그인한 사용자의 아이디
	 */
	public MyPageMain(String userId) {

		this.userId = userId; 
	}

	/**
	 * 각각의 메뉴로 가는 선택지 호출 
	 * @param 사용자 아이디
	 */
	public static void  myPageMain(String userId) {
		
		Calendar c = Calendar.getInstance();
		
			
		MyInfomation myInfomation = new MyInfomation(userId);
		MyGenre myGenre = new MyGenre(userId);
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println();
			Title.mypageTitle();
			
			Scanner scan = new Scanner(System.in);
			
			String input = scan.nextLine();	
			
			
			if(input.equals("1")) {		
				myInfomation.myInfo();
				//scan.close();
				
			} else if (input.equals("2")) {
			
				myGenre.favGenre();
				//scan.close();
				
			} else if (input.equals("3")) {   	
				//탈퇴
				new DeleteID(userId).quit();
				
			} else if(input.equals("4")) {
				
				//회원메뉴로 나가기
				new userLogin().LoginCategory(userId);

				
			} 
		
		}
		
		
		
	}
	
	
}
