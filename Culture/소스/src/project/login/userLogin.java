package project.login;

import java.util.Calendar;
import java.util.Scanner;
import project.managereMode.Admin_Main;
import project.memberMain.memOutput;
import project.memberMain.MyPageCode.MyGenre;
import project.memberMain.MyPageCode.MyInfomation;
import project.memberMain.MyPageCode.MyPageMain;
import project.memberMain.MyPageCode.MyReviewCalendar;
import project.memberMain.MyPageCode.Title;
import project.memberMain.community.communityMain;
import project.memberMain.event.EventMain;
import project.memberMain.follow.FollowMain;
import project.memberMain.group.GroupMain;

// 메인메뉴 1을 눌렀을때 로그인 클래스의 메소드들로 접근
public class userLogin {

	static Scanner scan = new Scanner(System.in);
	public static String id = "";
	private static String password = "";
	public static int userLoginCount = 0;
	private static Calendar now = Calendar.getInstance();



	// 첫 로그인 화면
	public static void Login() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                    [로그인]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");



		System.out.print("아이디: ");
		id = scan.nextLine();


		System.out.print("비밀번호: ");
		password = scan.nextLine();



		// 회원이나 관리자인 경우
		if (findUser() == true) {

		} else {
			// 등록된 회원이 아닌경우 -> 아이디,비밀번호 찾기로 넘어감
			whoareu();
		}



	}


	private static boolean findUser() {

		for (User u : Data.list) {
			if (u.getId().equals(id) && u.getPassword().equals(password)) {
				if (u.getId().equals(id) && u.getPassword().equals(password)) {
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("           [회원]으로 로그인이 완료되었습니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					userLoginCount++;

					LoginCategory(id);
					// 로그인이 된 다음 화면은 이부분에 추가하면됨.

					return true;
				}

			}
		}

		if (id.equals("admin") && password.equals("1234")) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("           [관리자]로 로그인이 완료되었습니다.");
			System.out.println("            관리자 메인메뉴로 이동합니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");



			Admin_Main.adminMain(); // 관리자 메인메뉴로 이동



			return true;

		}
		return false;
	}



	// 로그인 하고 난 다음 화면
	public static void LoginCategory(String id) { // 로그인 하고 나면 그 다음 카테고리로 들어감
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("             [  " + id + "님 환영합니다 ] ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		memOutput.memberMain();
		memOutput.line(1);
		System.out.print("입력: ");
		String answer = scan.nextLine();
		
		MyReviewCalendar myCalendar = new MyReviewCalendar(now.get(Calendar.YEAR), (now.MONTH) + 2, id);
		
		
		if (answer.equals("1")) {
			MyPageMain.myPageMain(id);
		} else if(answer.equals("2")) {		
					//관람 기록 이동
			myCalendar.monthlyCalendar();
			myCalendar.calendarMenu();
		} else if(answer.equals("3")) {		
					// 커뮤니티 이동
			communityMain.commuMain();
		
		} else if(answer.equals("4")) {
				
			GroupMain.groupMain();// 모임 이동
		
		} else if(answer.equals("5")) {
				
			FollowMain.followMain();		  // Follow이동
		
		} else if(answer.equals("6")){
		
			EventMain.eventMain();	// 이벤트 이동
			
		} else if(answer.equals("7")) {
					// 로그아웃 ?? 
			main.loginMenu();
		} else {
			System.out.println("잘못된 입력입니다.");
			LoginCategory(id);
		}

	}



	public static void whoareu() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               가입이 되어있지 않습니다");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		System.out.println("[Q]1 .아이디를 찾으시겠습니까?");
		System.out.println("[Q]2 .회원가입을 하시겠습니까?");
		System.out.println("숫자를 입력하세요 : ");
		String answer = scan.nextLine();
		if (answer.equals("1")) {
			if (findMyId() == true) { // 해당하는 아이디가 있다면, 비밀번호를 찾을 수 있도록
				findMyPassword();
			} else {
				System.out.println("[!]현재 가입된 계정이 없습니다.");
			}
		}
		if (answer.equals("2")) {
			SignUp.addMember();
			Data.Save();

		}

		else {
			System.out.println("프로그램을 종료합니다.");
			System.out.println();
		}

	}



	// 아이디 찾기 - 이름 전화번호 입력
	public static boolean findMyId() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                  아이디 찾기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[Q]이름을 입력하세요 :  ");
		String name = scan.nextLine();
		System.out.println("[Q]전화번호를 입력하세요 :  ");
		String telnumber = scan.nextLine();



		for (User u : Data.list) { // 등록된 아이디가 있다면,
			if (u.getName().equals(name) && u.getTel().equals(telnumber)) {
				System.out.println(u.getName() + "의 아이디는" + u.getId() + "입니다.");
				findMyPassword();
				return true;


			}
		}
		System.out.println("[!]등록된 아이디가 없습니다. ");
		return false;
	}


	public static void findMyPassword() {
		System.out.println("[Q]비밀번호를 찾으시겠습니까?[Y/N]");
		String answer2 = scan.nextLine();

		if (answer2.toLowerCase().equals("y")) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("                 비밀번호 찾기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[Q]아이디를 입력하세요 ");
			String userid = scan.nextLine();
			System.out.println("[Q]전화번호를 입력하세요");
			String usertell = scan.nextLine();
			System.out.println("[Q]본인의 출신 초등학교를 입력하세요");
			String userschool = scan.nextLine();



			for (User u : Data.list) {
				if (u.getId().equals(userid) && u.getTel().equals(usertell)
						&& u.getSchool().equals(userschool)) {
					System.out.println(u.getName() + "님의 비밀번호는" + u.getPassword() + "입니다");
					break;
				}
			} // for문

		}



		System.out.println("프로그램을 종료합니다");

	}

}
