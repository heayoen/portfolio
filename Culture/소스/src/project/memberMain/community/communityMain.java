package project.memberMain.community;

import java.util.Scanner;
import project.login.userLogin;
import project.memberMain.MemberMain;
import project.memberMain.group.GroupMain;

public class communityMain {

	public static void commuMain() {
		Community commu = new Community();
		
		boolean loop = true;
		
		while (loop) {
			System.out.println();
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("                 커뮤니티");
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
					userLogin.LoginCategory(userLogin.id);
					
			}
		}
	}
}
