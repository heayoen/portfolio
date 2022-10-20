package project.managereMode;

import java.util.Scanner;
import project.memberMain.event.EventWork;

public class Admin_Main {


	public static void adminMain() {

		project.login.Data.load();
		BlacklistData.loadBlacklist();

		boolean loop = true;

		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("               관리자 메인 메뉴");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 커뮤니티");
			System.out.println("2. 모임");
			System.out.println("3. 이벤트");
			System.out.println("4. 회원계정");
			System.out.println("5. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");



			Scanner scan = new Scanner(System.in);
			System.out.print("번호 입력 : ");
			String input = scan.nextLine();
			System.out.println();
			System.out.println();

			if (input.equals("1")) {
				adminSel1();
				// 커뮤니티

			} else if (input.equals("2")) {
				adminSel2();
				// 모임

			} else if (input.equals("3")) {
				// 이벤트
				adminSel3();
			} else if (input.equals("4")) {
				// 회원계정
				adminSel4();

			} else {
				// 로그아웃
				project.login.main.loginMenu();
			}

		}

	}// main


	private static void adminSel1() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(              "관리자 카테고리 관리");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 영화");
		System.out.println("2. 연극");
		System.out.println("3. 뮤지컬");
		System.out.println("4. 도서");
		System.out.println("6. [메인메뉴] 돌아가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		Scanner scan = new Scanner(System.in);
		System.out.print("번호 입력 : ");
		String input = scan.nextLine();
		System.out.println();
		System.out.println();

		String moviePath = "data\\영화\\영화.txt";
		String playPath = "data\\연극\\연극.txt";
		String musicalPath = "data\\뮤지컬\\뮤지컬.txt";
		String bookPath = "data\\도서\\도서.txt";
		String displayPath = "";

		if (input.equals("1")) {
			// 영화
			adminSel1_1(moviePath);

		} else if (input.equals("2")) {
			// 연극
			adminSel1_1(playPath);

		} else if (input.equals("3")) {
			// 뮤지컬
			adminSel1_1(musicalPath);

		} else if (input.equals("4")) {
			// 도서
			adminSel1_1(bookPath);

		} else {
			// [메인메뉴] 돌아가기
		}
	} // adminSel1()



	public static void adminSel1_1(String path) {

		AdminCommunity adminCommu = new AdminCommunity();

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 작품 리스트 보기");
		System.out.println("2. 새로운 작품 추가");
		System.out.println("3. 작품 삭제");
		System.out.println("4. 비속어 추가");
		System.out.println("5. 비속어 제거");
		System.out.println("6. [메인 메뉴] 돌아가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		Scanner scan = new Scanner(System.in);
		System.out.print("번호 입력 : ");
		String input = scan.nextLine();
		System.out.println();
		System.out.println();

		
		String banList = "data\\비속어\\비속어.txt";


		if (input.equals("1")) {
			// 작품 리스트 보기
			adminCommu.read(path);

		} else if (input.equals("2")) {
			// 새로운 작품 추가
			adminCommu.add(path);

		} else if (input.equals("3")) {
			// 작품 삭제
			adminCommu.delete(path);

		} else if (input.equals("4")) {
			// 비속어 추가
			adminCommu.addBan(banList);

		} else if (input.equals("5")) {
			// 비속어 제거
			adminCommu.deleteBan(banList);

		} else {
			// [커뮤니티 관리] 돌아가기
			adminSel1();
		}


	} // adminSel1_1()



	public static void adminSel2() {

		String moimPath = "data\\모임\\모임리스트.txt";
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               관리자 모임 관리");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 모임 댓글 / 게시물 관리");
		System.out.println("2. 모임 창설 / 삭제");
		System.out.println("3. [메인 메뉴] 돌아가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		Scanner scan = new Scanner(System.in);
		System.out.print("번호 입력 : ");
		String input = scan.nextLine();
		System.out.println();
		System.out.println();


		if (input.equals("1")) {
			// 모임 댓글 / 게시물 관리
			AdminMoim.readMoim();

		} else if (input.equals("2")) {
			// 모임 창설 / 삭제
			AdminMoim.addDelManu();

		} else if (input.equals("3")) {
			// [메인메뉴] 돌아가기
			adminMain();
			
		} else {
			System.out.println("1~3 중에 숫자를 입력해주세요.");
			adminSel2();
		}
	} // adminSel2()



	public static void adminSel3() {


		AdminEvent adminEv = new AdminEvent();


		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               관리자 이벤트 관리");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 이벤트 목록 보기");
		System.out.println("2. 이벤트 추가하기");
		System.out.println("3. 이벤트 삭제/수정 하기");
		System.out.println("4. [메인 메뉴] 돌아가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		Scanner scan = new Scanner(System.in);
		System.out.print("번호 입력 : ");
		String input = scan.nextLine();
		System.out.println();
		System.out.println();



		if (input.equals("1")) {
			// 이벤트 목록 보기
			AdminEvent.adminEventSelect();

		} else if (input.equals("2")) {
			// 이벤트 추가하기
			AdminEvent.createEvent();

		} else if (input.equals("3")) {
			// 이벤트 삭제하기
			AdminEvent.deleteOrAlterEvent();

		} else if (input.equals("4")) {
			// [메인메뉴] 돌아가기
			
		} else {
			System.out.println("1~4 중에 숫자를 입력해주세요.");
		}
	} // adminSel3()



	public static void adminSel4() {


		AdminMember adminMem = new AdminMember();


		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               관리자 회원계정 관리");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 회원 검색");
		System.out.println("2. 블랙 리스트");
		System.out.println("3. 로그인 기록 통계");
		System.out.println("4. [메인 메뉴] 돌아가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		Scanner scan = new Scanner(System.in);
		System.out.print("번호 입력 : ");
		String input = scan.nextLine();
		System.out.println();
		System.out.println();



		if (input.equals("1")) {
			// 회원 검색
			adminMem.findMem();

		} else if (input.equals("2")) {
			// 블랙리스트
			adminMem.blackList();
			BlacklistData.saveBalcklist();

		} else if (input.equals("3")) {
			// 로그인 통계 기록 -> 일단 회원인 사람들중에서 로그인 한 횟수별로 count해서 출력
			adminMem.logGraph();

		} else if (input.equals("4")) {
			// [메인메뉴] 돌아가기

		} else {
			System.out.println("1~4 중에 숫자를 입력해주세요.");
			adminSel4();
		}


	} // adminSel4()

	
	


}// Admin_main
