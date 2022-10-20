package project.memberMain.follow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import project.login.userLogin;
import project.memberMain.memOutput;

public class UserChat {
	
	public static void userChat(String input) {
		
		Scanner scan = new Scanner(System.in);
		
		String path = "data\\DM";
		
		File file = new File(path);
		File[] files = file.listFiles();
		
		String id = String.format("%s", userLogin.id);    //id
//		String id = "user01";
		
		for(int i = 0; i < files.length; i++) {
			
			if(files[i].getName().contains(id) && files[i].getName().contains(input)) {
				
				
				memOutput.subtitle(String.format("%s 님과의 대화", input));
				
				try {
					
					BufferedReader reader;
					printChat(files, i);
					
					memOutput.line(1);
					System.out.println("'exit'입력시 [DM 확인]으로 돌아갑니다.");
					memOutput.line(1);
					System.out.print("메세지 입력: ");
					
					
					String message = scan.nextLine();
						   
					memOutput.line(1);
						   
					if(message.contains("exit")) {
					
						memOutput.pause();
						memOutput.line(1);
						DMconfirm.dmConfirm();
					}
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(files[i],true));
		
					
					writer.write(String.format("%s: %s\n", userLogin.id ,message));	 //id 받기				
//					writer.write(String.format("user01: %s\n",message));
					
					writer.close();
					
					userChat(input);

					
					
					
					
				} catch (Exception e) {
					System.out.println("UserChat.userChat");
					e.printStackTrace();
				}		
				
				
			}
			
			
		}
		
			memOutput.line(1);
			System.out.println("목록에 있는 ID를 입력해 주세요!");
			memOutput.line(1);
			memOutput.pause();
			memOutput.line(1);
			FollowMain.followMain();
		
		
		
		
	}

	private static void printChat(File[] files, int i) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(files[i]));
		String line = null;
		
		while((line = reader.readLine()) != null) {
			
			System.out.println(line);
			
		}		
		reader.close();
	}
	
}









