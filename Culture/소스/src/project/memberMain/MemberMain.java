package project.memberMain;

import java.util.Scanner;
import project.login.userLogin;
import project.memberMain.MyPageCode.MyGenre;
import project.memberMain.MyPageCode.MyInfomation;
import project.memberMain.community.communityMain;
import project.memberMain.event.EventMain;
import project.memberMain.follow.FollowMain;
import project.memberMain.group.GroupMain;

public class MemberMain {
	
	
	public static void main() { 		//추후 메인 삭제 후 'memberMain()' 메소드로 변경 예정{
		
		
		memberMain();
		
	}

	public static void memberMain() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		MyGenre myGenre = new MyGenre(userLogin.id);
		MyInfomation myInfomation = new MyInfomation(userLogin.id);
//		while(loop) {
			
			
			memOutput.subtitle("회원 로그인 메인메뉴");
			memOutput.memberMain();
			memOutput.line(1);
			System.out.print("입력: ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
							//MyPage 이동
				myInfomation.myInfo();
			}else if(input.equals("2")) {		
							//관람 기록 이동
				myGenre.favGenre();
			}else if(input.equals("3")) {		
							// 커뮤니티 이동
				communityMain.commuMain();
				
			}else if(input.equals("4")) {
						
				GroupMain.groupMain();// 모임 이동
				
			}else if(input.equals("5")) {
						
				FollowMain.followMain();		  // Follow이동
				
			}else if(input.equals("6")){
				
				EventMain.eventMain();	// 이벤트 이동
					
			}else if(input.equals("7")) {
							// 로그아웃
			}
				
//		}
	}

}
