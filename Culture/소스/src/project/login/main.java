package project.login;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Data.load();

		loginMenu();
	}

	public static void loginMenu() {
		
		userLogin login = new userLogin();
		SignUp signup = new SignUp();
		boolean loop = true;

		Output.title();

		while (true) {
			System.out.println();
			System.out.println();


			Output.subtitle();
			Scanner scan = new Scanner(System.in);
			System.out.print("입력 : ");
			String input = scan.nextLine();



			if (input.equals("1")) { // 로그인
				login.Login();

			} else if (input.equals("2")) {// 회원가입
				signup.addMember();
				Data.Save();

			} else if (input.equals("3")) { // 계정찾기
				login.findMyId();

			} else if (input.equals("4")) { // 비회원 계정으로 이동하기
				nonUserLogin.nonUserLogin();

			} else if (input.equals("5")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("           프로그램을 종료합니다.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.exit(0);
			} else {
				loop = false;
			}
		}
	}

}


