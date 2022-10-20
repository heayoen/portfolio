package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import project.login.userLogin;
import project.memberMain.memOutput;

public class PrintList {

	private static Scanner scan;

	public PrintList() {
		this.scan = new Scanner(System.in);
	}
	
	public static void FollowingList() {
		try {
			
			String path = String.format("data\\마이페이지\\%s\\following.txt", userLogin.id);  // id 추가
//			String path = "data\\마이페이지\\user01\\following.txt";
		
			File dir = new File(path);
			
			memOutput.subtitle1("내가 팔로우한 사람들");
			
			String line = null;
			int count = 1;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
		
			while((line = reader.readLine()) != null) {
				
				System.out.printf("%d) %s\n",count,line);		
				count++;
			}
			reader.close();
			
			if (count == 1) {
				System.out.println("      아직 [Follow]한 유저가 없습니다.");
			}
			memOutput.line(1);
			
		} catch (Exception e) {
			System.out.println("PrintList.FollowingList");
			e.printStackTrace();
		}
		

	}
	

	public static void postList(int num) {

		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		if (num >= 1 && num <= dirs.length + 1) {

			File group =
					new File(String.format("data\\모임\\%s", dirs[num - 1].getName()));

			File[] groups = group.listFiles();

			memOutput.subtitle(String.format("%s 모임의 게시물", dirs[num - 1].getName()));

			for (int i = 0; i < groups.length; i++) {

				System.out.printf("%d) %s\n", i + 1,
						groups[i].getName().substring(0, groups[i].getName().length() - 4));

			}
		}
	}


	public static void eventList() { // 이벤트 리스트 출력

		File dir = new File("data\\이벤트");
		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			System.out.printf("[%d] %s\n", i + 1,
					files[i].getName().substring(0, files[i].getName().length() - 4)); // 파일 이름만 출력
		}
	}
	
	public static void memberGroupList() {

		File dir = new File("data\\모임");
		File[] files = dir.listFiles();
		
		String path = String.format("data\\마이페이지\\%s\\info.txt", userLogin.id);  // id
//		String path = "data\\마이페이지\\user01\\info.txt";

		File userDir = new File(path);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String Data = reader.readLine();
			int count = 0;
			
			for (int i = 0; i < files.length; i++) {

				if (Data.contains(files[i].getName())) {

					System.out.printf("[%d] %s\n", i + 1, files[i].getName()); // 파일 이름만 출력
					count++;
				}
			}
			
			if(count == 0) {
				System.out.println("가입된 모임이 없습니다.");
			}
			
			

		} catch (Exception e) {
			System.out.println("PrintList.memberGroupList");
			e.printStackTrace();
		}

	}


	public static void groupList() {

		File dir = new File("data\\모임");
		File[] files = dir.listFiles();
		
		String path = String.format("data\\마이페이지\\%s\\info.txt", userLogin.id);  // id
//		String path = "data\\마이페이지\\user01\\info.txt";

		File userDir = new File(path);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(userDir));
			
			String Data = reader.readLine();
			int count = 1;
			
			for (int i = 0; i < files.length; i++) {
				
				if(files[i].isFile()) {
					continue;
				}
				
				if (!Data.contains(files[i].getName())) {

					System.out.printf("[%d] %s\n", i+1, files[i].getName()); // 파일 이름만 출력
					count++;
				}
			}
			
			if(count == 1) {
				System.out.println("더 가입할 수 있는 모임이 없습니다.");
			}
			
			

		} catch (Exception e) {
			System.out.println("PrintList.memberGroupList");
			e.printStackTrace();
		}

	}
	
	public static void myDMList() {
		
		memOutput.subtitle("메신저(DM) 확인");
			
		try {
			
			String path = String.format("data\\마이페이지\\%s\\following.txt", userLogin.id);  // id 추가
//			String path = "data\\마이페이지\\user01\\following.txt";
		
			File dir = new File(path);
			
			String line = null;
			int count = 1;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			while((line = reader.readLine()) != null) {
				
				System.out.printf("%d) %s\n",count,line);		
				count++;
			}
			reader.close();
			memOutput.line(1);
			
						
		} catch (Exception e) {
			System.out.println("PrintList.myDMList");
			e.printStackTrace();
		}		
		
	}

}












