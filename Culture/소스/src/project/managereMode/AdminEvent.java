package project.managereMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import project.PrintList;
import project.login.Output;
import project.memberMain.MemberMain;
import project.memberMain.memOutput;

public class AdminEvent {

	private static Scanner scan = new Scanner(System.in);

	// 1. 이벤트 읽기
	public boolean read(String path) {

		try {

			// 파일 경로 추가
			File file = new File(path);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				int n = 1;

				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					n++;
				}
				reader.close();
				
				System.out.println();
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				Admin_Main.adminSel3();
				
			} else {
				System.out.println("이벤트 출력 완료");
			}


		} catch (Exception e) {
			System.out.println("adminevent.read() : " + e.toString());
			e.printStackTrace();
		}

		return true;

	} // read()

	public static void adminEventSelect() {		// 이벤트 고르기
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		PrintList.eventList();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 이벤트 보기");
		System.out.println("2. [메인메뉴] 돌아가기");
		Scanner scan1 = new Scanner(System.in);
		
		System.out.print("입력: ");
		
		String input = scan1.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		if(input.equals("1")) {
			adminEventView();      //이벤트 고르기 이동
		    memOutput.pause();
		    adminEventSelect();
		} else  {
			 		//회원 로그인 메인메뉴 이동 
			Admin_Main.adminSel3();
		}
		
		
	}

	private static void adminEventView() {
		File dir = new File("data\\이벤트");			
		File[] files = dir.listFiles();
		
		System.out.print("이벤트 선택(번호): ");						
		int num = scan.nextInt();


		if (num >= 1 && num < files.length+1) {

			File event = new File(String.format("data\\이벤트\\%s" 		// 선택한 번호에 맞는 이벤트 경로 찾기
					, files[num - 1].getName()));																// 선택 번호는 1부터 시작하기에 num+1

			try {
				BufferedReader reader = new BufferedReader(new FileReader(event));
				String line = null;
				while ((line = reader.readLine()) != null) {											// 이벤트 내용 출력
					System.out.println(line);
				}
				reader.close();

			} catch (Exception e) {
				System.out.println("EventWork.eventSelect");
				e.printStackTrace();
			}
			
		}
	}
	


	// 2. 이벤트 추가하기
	public boolean add(String path) {

		String titleName = ""; // 제목
		String eventContent = ""; // 내용

		

		System.out.print("제목 : ");
		titleName = scan.nextLine();

		System.out.print("내용 : ");
		eventContent = scan.nextLine();




		System.out.println();
		System.out.print("추가 하시겠습니까? (y/n) : ");


		switch (scan.nextLine()) {
			case "y":
			case "Y":
				break; // y를누르면 빠져나가서 try문 실행하기
			default:
				return false;
		}


		try {


			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			writer.write(titleName + "\n");
			writer.write("==============================\n");
			writer.write(eventContent);
			writer.write("==============================\n\n");
			writer.write("------------------------------\n\n");
			System.out.println("추가 완료되었습니다.");
			writer.close();


		} catch (Exception e) {
			System.out.println("adminevent.add() : " + e.toString());
		}

		return true;

	} // add()



	// 3. 이벤트 삭제/수정 하기
	public static void deleteOrAlterEvent() {
	      
	      
      PrintList.eventList();
      System.out.print("수정/삭제 할 이벤트 선택: ");
      int c = scan.nextInt();
      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
      System.out.println("1. 삭제 ");
      System.out.println("2. 수정 ");
      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
      System.out.print("입력:");
      String d = scan.next();
      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

      if (d.equals("1")) {
         adminDeleteEvent(c);
      } else if (d.equals("2")) {
         adminAlterEvent(c);
      } else {
    	  System.out.println("잘못된 입력입니다.");
    	  Admin_Main.adminSel3();
      }

	}
	   
	private static void adminDeleteEvent(int c) {

	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      File dir = new File("data\\이벤트");
	      File[] dirs = dir.listFiles();

	      File group = new File(String.format("data\\이벤트\\%s", dirs[c - 1].getName()));

	      File[] groups = dir.listFiles();

	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      if (c > 0 && c < groups.length + 1) {

	    	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         System.out.print("정말 삭제하시겠습니까?(y/n) :"); // 게시물 삭제
	         String f = scan.next();

	         if (f.toLowerCase().equals("y")) {

	            System.out.println(group.delete());
	            System.out.println("삭제 완료");
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            System.out.println("계속하시려면 [엔터]를 입력하세요.");
	    		
	    		//프로그램 일시 정지
	    		Scanner scan = new Scanner(System.in);
	    		
	    		scan.nextLine(); //블럭.. == 일시 정지
	    		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            PrintList.eventList();
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            
	            Admin_Main.adminSel3();
	            
	         } else {
	        	 System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            System.out.println("계속하시려면 [엔터]를 입력하세요.");
	    		
	    		//프로그램 일시 정지
	    		Scanner scan = new Scanner(System.in);
	    		
	    		scan.nextLine(); //블럭.. == 일시 정지
	    		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            PrintList.eventList();
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            Admin_Main.adminSel3();
	         }

	      }
	}
	   
	
	private static void adminAlterEvent(int c) {
	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      File dir = new File("data\\이벤트");
	      File[] dirs = dir.listFiles();

	      File group = new File(String.format("data\\이벤트\\%s",
	            dirs[c - 1].getName()));

	      File[] groups = dir.listFiles();

	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	      try {
	         if (c > 0 && c < groups.length + 1) {
	            BufferedReader reader = new BufferedReader(new FileReader(groups[c - 1]));
	            
	               String line = null;
	               int count = 0;
	               int i = 0;

	               while ((line = reader.readLine()) != null) {
	                  System.out.println("[" + count + "] " + line);
	                  count++;
	               }
	               reader.close();

	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); // 게시물 수정
	               System.out.print("수정할 줄 번호를 입력해 주세요:");
	               int alter = scan.nextInt();
	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	               System.out.print("수정할 내용 입력:");
	               String alterContents = scan.nextLine();
	                     alterContents = scan.nextLine();

	               System.out.println(groups[c - 1].getName());

	               String path1 = String.format(
	                     "data\\게시물 수정\\%s",
	                     groups[c - 1].getName());

	               File alterFile = new File(path1);
	               alterFile.createNewFile();

	               BufferedWriter writer = new BufferedWriter(new FileWriter(alterFile));
	               reader = new BufferedReader(new FileReader(groups[c - 1]));

	               line = null;
	               count = 0;

	               while ((line = reader.readLine()) != null) {

	                  if (count == alter) {
	                     writer.write(alterContents);
	                     writer.newLine();
	                     count++;
	                     continue;
	                  }

	                  writer.write(line);
	                  writer.newLine();
	                  count++;
	               }
	               reader.close();
	               writer.close();

	               String path = groups[c - 1].getAbsolutePath();
	               groups[c - 1].delete();

	               alterFile.renameTo(new File(path));

	               System.out.println("이벤트 수정을 완료했습니다.");
	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	               PrintList.eventList();
	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	               
	               Admin_Main.adminSel3();

	         }

	      } catch (Exception e) {
	      
	         e.printStackTrace();
	      }
    }
	
	public boolean delete(String path) {


		String line = "";
		String input = "";
		int index = 0;
		int firstNum = 0;
		int secondNum = 0;
		ArrayList<String> titleArray = new ArrayList<String>();
		ArrayList<String> arrayAll = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);



		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				System.out.printf("%d. %s\r\n", index, line);
				arrayAll.add(line);
				index++;
			}
			reader.close();

		} catch (Exception e) {
			System.out.println("admincommu.delete() : " + e.toString());
		}

		while (true) {
			System.out.print("삭제할 구간 숫자 2개 입력해주세요. \n 첫번째 숫자: ");
			firstNum = Integer.parseInt(scan.nextLine());
			System.out.print("두번째 숫자: ");
			secondNum = Integer.parseInt(scan.nextLine());


			if (firstNum < 0 || firstNum > index || secondNum < 0 || secondNum > index
					|| secondNum < firstNum) {
				System.out.println("올바른 번호를 입력해주세요");
			} else {
				System.out.print("해당 구간을 삭제 하시겠습니까? (y/n) : ");
				input = scan.nextLine();
				if (input.equalsIgnoreCase("Y")) {
					break;
				}
			}
		}


		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (int i = 0; i < firstNum; i++) {
				writer.write(arrayAll.get(i) + "\r\n");
			}

			for (int i = secondNum; i < index; i++) {
				writer.write(arrayAll.get(i) + "\r\n");
			}

			System.out.println("삭제 완료되었습니다.");
			writer.close();

		} catch (Exception e) {
			System.out.println("adminevent.delete() : " + e.toString());
		}

		return true;

	} // delete()

	public static void createEvent() {
	      
	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      File dir = new File("data\\이벤트");
	      File[] dirs = dir.listFiles();

//	      File group = new File(String.format("data\\이벤트\\%s",
//	            dirs[num - 1].getName()));
	//
//	      File[] groups = group.listFiles();
	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	      System.out.print("이벤트를 추가하시겠습니까?(y/n): ");
	      Scanner scan = new Scanner(System.in);
	      String input = scan.next();
	            


	      if (input.equals("y")) {

	         try {
	        	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        	System.out.print("제목: ");
	            String title = scan.nextLine();   
	                 title = scan.nextLine();   


	            File post = new File(String.format(dir + "\\%s.txt", title));

	            post.createNewFile();

	            BufferedWriter writer = new BufferedWriter(new FileWriter(post));

	            writer.write("======================================================");
	            writer.newLine();
	            writer.write(String.format("[제목]%-17s  \n",
	                  post.getName().substring(0, post.getName().length() - 4))); // 제목에 아이디 추가
	            writer.write("======================================================");
	            writer.newLine();

	            boolean flag = true;
	            System.out.print("입력('exit'입력시 종료):");

	            while (flag) {

	               String write = scan.nextLine();
	               write = slang(write);

	               if (write.contains("exit")) {
	                  break;
	               }
	               writer.write(write);
	               writer.newLine();

	            }

	            writer.newLine();
	            writer.write("======================================================");
	            writer.newLine();

	            
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	               System.out.println("             추가 완료되었습니다.");
	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	              
	            
	            

	            writer.close();
	          
	            
	      

	         } catch (Exception e) {
	            System.out.println("GroupWork.createPost");
	            e.printStackTrace();
	         }


	      } else {
	         System.out.println("메뉴로 돌아가는 메소드 추가");
	      }
	   }
	
	public static String slang(String input) {				// 욕설 
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("data\\비속어\\비속어.txt"));
			
			String line = null;
			
			while((line = reader.readLine())!= null) {
				
				if(input.contains(line)) {
					
					String star = "";
					
					for(int i = 0 ; i<line.length(); i++) {
						star += "*";
					}
					
					input = input.replace(line,star);
				}
				
				
			}
			reader.close();			
			
		} catch (Exception e) {
			System.out.println("Output.swearWord");
			e.printStackTrace();
		}
		return input;
			
	}

} // AdminEvent
