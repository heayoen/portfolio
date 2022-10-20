package project.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import project.PrintList;
import project.memberMain.MemberMain;
import project.memberMain.memOutput;
import project.memberMain.community.Community;
import project.memberMain.community.communityUI;
import project.memberMain.event.EventWork;

public class nonUserLogin {
	public static int nonUserCount = 0;
	static Scanner scan = new Scanner(System.in);
	private static Community commu = new Community();

	public static void nonUserLogin() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               [비회원]으로 입장하였습니다");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		nonUserCount++;

		nonUserMain();

	}

	public static void nonUserMain() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("               [비회원]으로 메인 메뉴입니다");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 커뮤니티");
		System.out.println("2. 이벤트");
		System.out.println("3. [로그인] 돌아가기");
		System.out.print("입력 : ");
		String answer = scan.nextLine();


		if (answer.equals("1")) {
			nonUserCategory();
		} else if (answer.equals("2")) {
			noneventMain(); // 이벤트
		} else if (answer.equals("3")) {
			main.loginMenu(); // 메인 메뉴로 돌아가기
		}


	}

	public static void nonUserCategory() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                  커뮤니티");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		communityUI.communityMenu();
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		switch (input) {
			
			case "1" :
				commu.movie(input);
				break;
			case "2" :
				commu.play(input);
				break;
			case "3" :
				commu.musical(input);
				break;
			case "4" :
				commu.book(input);
				break;
			case "5" :
				nonUserMain();
				
		}
	}

	public static void noneventMain() {

		EventWork eventWork = new EventWork();
		boolean loop = true;

		while(loop) {
			memOutput.subtitle("이벤트");
			PrintList.eventList();
			memOutput.line(1);
			System.out.println("1. 이벤트 보기");
			System.out.println("2. [메인메뉴] 돌아가기");
			memOutput.line(1);
			
			Scanner scan1 = new Scanner(System.in);
			
		
			System.out.print("입력: ");
			String input = scan1.nextLine();
			
			if(input.equals("1")) {
				noneventSelect();      //이벤트 고르기 이동
			    memOutput.pause();
			} else  {
				 		//회원 로그인 메인메뉴 이동 
				nonUserMain();
			}
			
		}
	
	}
	
	public static void noneventSelect() {		// 이벤트 고르기
		

		File dir = new File("data\\이벤트");			
		File[] files = dir.listFiles();
		
		System.out.print("이벤트 선택(번호): ");						
		int num = scan.nextInt();


		if (num >= 1 && num < files.length+1) {

			File event = new File(String.format("data\\이벤트\\%s" 		// 선택한 번호에 맞는 이벤트 경로 찾기
					, files[num - 1].getName()));																// 선택 번호는 1부터 시작하기에 num+1

			try {
				BufferedReader reader = new BufferedReader(new FileReader(event));
				String line = null;
				while ((line = reader.readLine()) != null) {											// 이벤트 내용 출력
					System.out.println(line);
				}
				reader.close();

			} catch (Exception e) {
				System.out.println("EventWork.eventSelect");
				e.printStackTrace();
			}
			
		}
	}

}
