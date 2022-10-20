package project.memberMain.event;

import java.util.Scanner;
import project.PrintList;
import project.memberMain.MemberMain;
import project.memberMain.memOutput;

public class EventMain {

		public static void eventMain() {

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
				   eventWork.eventSelect();      //이벤트 고르기 이동
				   memOutput.pause();
			} else  {
				 		//회원 로그인 메인메뉴 이동 
				MemberMain.memberMain();
			}
			
		}
		

	}

}
