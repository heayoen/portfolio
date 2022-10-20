package project.memberMain.community;

import java.util.Scanner;

public class communityUI {

	private Scanner scan;
	
	public communityUI() {
		this.scan = new Scanner(System.in);
	}
	
	public static void title() {
		System.out.println("                                        \r\n"
				+ "                     .::LPBIBZ       \r\n"
				+ "               .:vEMPIiBBBM  B       \r\n"
				+ "         .vbZPsBBBB    BBB           \r\n"
				+ "    b5bBBBJ   BB                    \r\n"
				+ "    B  BBBY                          \r\n"
				+ "    qQZBBQZqPUri..::7YJJvi7ivs2i     \r\n"
				+ "    :B .BBBb   vBBQB ..rBBBB  PB     \r\n"
				+ "    :B.. IBBBQ.. 2BBBB.. vBBBg1Q     \r\n"
				+ "    :B                        iB     \r\n"
				+ "    iB                        YB     \r\n"
				+ "    :B                        vB     \r\n"
				+ "    iB                        rB     \r\n"
				+ "    :BsUU52IU52IU525IIIIUI251jBB     \r\n"
				+ "                                        ");
		System.out.println("=================================================");
		System.out.println("           문화 생활 기록 관리 시스템");
		System.out.println("=================================================");
	}
	
	public static void Login() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 계정 찾기");
		System.out.println("4. 비회원 로그인");
		System.out.println("5. 프로그램 종료");
		System.out.print("입력 : ");
	}
	
	
	public static void idInput() {
		System.out.print("아이디 입력 : ");
	}
	
	public static void passInput() {
		System.out.print("비밀번호 입력 : ");
	}
	
	
	
	public static void communityMenu() {
		
		System.out.println("1. 영화");
		System.out.println("2. 연극");
		System.out.println("3. 뮤지컬");
		System.out.println("4. 도서");
		System.out.println("5. [메인 메뉴] 돌아가기");
		System.out.print("입력 : ");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

































