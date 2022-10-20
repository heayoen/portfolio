package project.memberMain.group;

import java.util.Scanner;
import project.PrintList;
import project.memberMain.memOutput;

public class GroupJoin {
	
	public static void groupJoin() {
		
		GroupWork groupWork =  new GroupWork();

		Scanner scan = new Scanner(System.in);

		memOutput.subtitle("모임 리스트");
		PrintList.groupList();
		memOutput.line(1);
		memOutput.groupJoinMain();
		memOutput.line(1);
		
		System.out.print("입력: ");
		String input = scan.next();
		
		if(input.equals("1")) {
			  groupWork.groupJoinConfirm();         //가입여부 확인
		}else {
			GroupMain.groupMain();		// groupMain(); [모임] 돌아가기
		}
		
		
		
		
	}

}