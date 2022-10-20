package project.memberMain.follow;

import java.io.File;
import java.util.Scanner;
import project.PrintList;
import project.login.userLogin;
import project.memberMain.MemberMain;
import project.memberMain.memOutput;

public class FollowMain {

		public static void followMain() {

		boolean loop = true;
		
		
//		follow 존재 여부 확인
		File file = new File("data\\마이페이지\\" + userLogin.id + "\\following.txt");
		
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.out.println("FollowMain.followMain");
				e.printStackTrace();
			}
		}
		
		while(loop) {
			memOutput.subtitle("   Follow");
			PrintList.FollowingList();
			memOutput.followMain();
			memOutput.line(1);
			Scanner scan1 = new Scanner(System.in);
			
		
			System.out.print("입력: ");
			String input = scan1.nextLine();
			
			if(input.equals("1")) {
				// Follow 추가
				FollowAdd.followAdd();
				
			}else if(input.equals("2"))  {
				// 메신저 (DM)확인
				DMconfirm.dmConfirm();
			}else if(input.equals("3"))  {
				// Follow 회원 캘린더 보기
		 		FollowCal.followCal();
			}else if(input.equals("4"))  {
					// Follow 관리
				ManageFollow.manageFollow();
		 		
			}else {
					// [멤버 메인메뉴 돌아가기]
				userLogin.LoginCategory(userLogin.id);
			}	
			
		}
		

	}

}

















