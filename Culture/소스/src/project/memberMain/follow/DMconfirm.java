package project.memberMain.follow;

import java.util.Scanner;
import project.PrintList;
import project.memberMain.memOutput;

public class DMconfirm {
	
	public static void dmConfirm() {
		
		Scanner scan = new Scanner(System.in);
		
		PrintList.myDMList();
		memOutput.line(1);
		System.out.println("1. DM 확인하기");
		System.out.println("2. [Follow] 돌아가기");
		memOutput.line(1);
		System.out.print("입력: ");
		String input = scan.next();
		
		if(input.equals("1")) {
				
			System.out.print("대화할 상대(ID) 입력: ");
			String input1 = scan.next();	
			
			UserChat.userChat(input1);	 //대화창 이동
		}else {
			FollowMain.followMain(); // [팔로우] 이동
		}

		
		
		
		
		
		
		
	}	

}
