package project.memberMain.group;

import java.util.Scanner;
import project.PrintList;
import project.memberMain.memOutput;

public class GroupConnect {

	public static void groupConnect() {

		GroupWork groupWork =  new GroupWork();

		Scanner scan = new Scanner(System.in);

		
		memOutput.subtitle("가입된 모임 리스트");

		PrintList.memberGroupList();
		memOutput.line(1);

		System.out.println("1. 모임 선택하기");
		System.out.println("2. [모임] 돌아가기");
		memOutput.line(1);

		System.out.print("입력: ");
		String input = scan.nextLine();

		if (input.equals("1")) {
			groupWork.groupSelect();// 모임 고르기 이동
			memOutput.line(0);

		} else {
			GroupMain.groupMain();
		}
	}

}