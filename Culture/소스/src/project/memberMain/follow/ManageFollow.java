package project.memberMain.follow;

import java.util.Scanner;
import project.PrintList;
import project.memberMain.memOutput;

public class ManageFollow {
	
	public static void manageFollow(){
		
		
		
		Scanner scan = new Scanner(System.in);
		
		PrintList.FollowingList();
		
		System.out.println("1. 삭제할 유저 선택");
		System.out.println("2. [Follow] 돌아가기");
		memOutput.line(1);
		
		System.out.print("입력: ");
		String input = scan.next();
		memOutput.line(1);
		
		if(input.equals("1")) {
			
			DeleteFollow.deleteFollow();
			
		}else {
			
			FollowMain.followMain(); // [Follow]로 돌아가기
			
		}
		
		
		
		
		
	}

}
