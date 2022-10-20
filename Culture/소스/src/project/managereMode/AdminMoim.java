package project.managereMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMoim {

	private static Scanner scan = new Scanner(System.in);
	private static String moimFolderPath = "data\\모임";
	private static String moimListPath = "data\\모임\\모임리스트.txt";

	   // 모임 리스트 txt 읽기
	   public static void readMoim() {

		  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	      System.out.println("                 모임 리스트");
	      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");



	      try {

	         BufferedReader reader = new BufferedReader(new FileReader(moimListPath));
	         String line = null;
	         int index = 1;

	         while ((line = reader.readLine()) != null) {
	            System.out.println(index + ". " + line);
	            index++;
	         }

	         reader.close();



	      } catch (Exception e) {
	         System.out.println("AdminMoim.readList() +" + e.toString());
	         e.printStackTrace();
	      }


	      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	      System.out.println("1. 댓글 / 게시물 관리");
	      System.out.println("2. [관리자 모임 관리] 돌아가기");
	      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	      System.out.print("입력 : ");
	      
	      String input = scan.nextLine();
	      
	      if (input.equals("1")) {
	    	  manage(moimListPath, moimFolderPath);
	      } else if (input.equals("2")) {
	    	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    	  System.out.println("         [관리자 모임 관리]로 돌아갑니다.");
	    	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    	  Admin_Main.adminSel2();
	      } else {
	    	  System.out.println("잘못된 입력입니다.");
	    	  readMoim();
	      }
	      
	      
	   }

	   
	   public static void addDelManu() {
		   
		   	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		      System.out.println("                 모임 리스트");
		      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		      try {

		         BufferedReader reader = new BufferedReader(new FileReader(moimListPath));
		         String line = null;
		         int index = 1;

		         while ((line = reader.readLine()) != null) {
		            System.out.println(index + ". " + line);
		            index++;
		         }

		         reader.close();


		      } catch (Exception e) {
		         System.out.println("AdminMoim.readList() +" + e.toString());
		         e.printStackTrace();
		      }


		      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		      System.out.println("1. 모임 창설");
		      System.out.println("2. 모임 삭제");
		      System.out.println("3. [관리자 모임 관리] 돌아가기");
		      System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		      System.out.print("입력 : ");
		      String input = scan.nextLine();
		      
		      
		      if (input.equals("1")) {
		    	  addMoim(moimListPath, moimFolderPath);
		      } else if (input.equals("2")) {
		    	  deleteMoim(moimListPath, moimFolderPath);
		      } else if (input.equals("3")) {
		    	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		    	  System.out.println("         [관리자 모임 관리]로 돌아갑니다.");
		    	  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		    	  Admin_Main.adminSel2();
		      } else {
		    	  System.out.println("잘못된 입력입니다.");
		    	  readMoim();
		      }
		      
		   
	   }


	   // 1. 모임 만들기 > txt 파일 추가 > 폴더 추가
	   public static void addMoim(String listPath, String path) {

		 //moimFolderPath = "data\\모임"; > path
		//moimListPath = "data\\모임\\모임리스트.txt"; > listPath



	      ArrayList<String> arrayAll = new ArrayList<String>();
	      int index = 1;

	      try {

	         // txt 파일 추가
	    	 System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         System.out.print("모임 이름 : ");
	         String input = scan.nextLine();

	         // 경로
	         String newPath = path + "\\" + input;

	         File dir = new File(newPath);

	         if (!dir.exists()) {

	            boolean result = dir.mkdirs();



	            BufferedReader reader = new BufferedReader(new FileReader(listPath));
	            String line = null;


	            while ((line = reader.readLine()) != null) {
	               arrayAll.add(line);
	               index++;
	               //System.out.println(line);
	            }

	            reader.close();

	            try {
	               BufferedWriter writer = new BufferedWriter(new FileWriter(listPath));

	               for (int i = 0; i < index - 1; i++) {
	                  writer.write(arrayAll.get(i) + "\r\n");

	               }

	               writer.write(input);

	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	               System.out.println("             추가 완료되었습니다.");
	               System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	              
	               writer.close();
	               
	               addDelManu();
	               
	               
	            } catch (Exception e) {
	               System.out.println("AdminMoim.addMoin() : " + e.toString());
	            }

	         } else {
	        	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            System.out.println("             이미 존재하는 모임입니다.");
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            addDelManu();
	         }



	      } catch (Exception e) {
	         System.out.println("AdminMoim.addMoin() : " + e.toString());
	         e.printStackTrace();
	      }


	   } // addMoim()



	   // 2. 모임 삭제 > txt 파일 삭제 > 폴더 삭제(탐색해서 하나씩 삭제)
	   public static void deleteMoim(String listPath, String path) {

	      // listPath : 모임 리스트 txt
	      // path : 모임 폴더 만들기 경로

	      try {
	    	 
	    	 System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         System.out.print("삭제할 모임 이름 : ");
	         String input = scan.nextLine();
	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

	         // 폴더 안에 txt 몇개인지 확인
	         // for문 갯수만큼 txt파일 삭제
	         // 폴더 삭제하기


	         File dir = new File(path + "\\" + input);



	         if (dir.exists()) {


	            File[] list = dir.listFiles();


	            for (File f : list) {
	               // 파일삭제완료
	               if (f.isFile()) {
	                  System.out.println(f.getName());
	                  File file = new File(path + "\\" + input + "\\" + f.getName());
	                  file.delete();
	               }
	            }

	            // 폴더삭제완료
	            dir.delete();
	            

	         }


	         // 모임list.txt에서 삭제하기
	         ArrayList<String> arrayAll = new ArrayList<String>();
	         BufferedReader reader = new BufferedReader(new FileReader(listPath));
	         String line = null;
	         int index = 0;

	         while ((line = reader.readLine()) != null) {
	            if (line.indexOf(input) == -1) {
	               arrayAll.add(line);
	               index++;
	            } else {
	               continue;
	            }
	            // 모임 찾았을 경우 > continue로 저장 안함

	         }

	         reader.close();
	         

	         try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(listPath));

	            for (int i = 0; i < index; i++) {
	               writer.write(arrayAll.get(i) + "\r\n");
	            }

	            writer.close();

	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            System.out.println("               모임 삭제 완료");
	            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            addDelManu();
	         } catch (Exception e) {
	            System.out.println("AdminMoim.deleteMoim() : " + e.toString());
	         }



	      } catch (Exception e) {
	         System.out.println("AdminMoim.deleteMoim() : " + e.toString());
	         e.printStackTrace();
	      }



	   } // deleteMoim()



	   // 3. 모임 게시물 / 댓글 관리
	   // 모임 선택 > 해당 모임 폴더에 txt파일 제목 불러오기, 선택 > 모임 게시물 / 댓글 삭제

	   // listPath = 모임 이름만 만들어놓은 txt파일 path
	   // path = 모임 폴더 경로
	   public static void manage(String listPath, String path) {

	      try {

	         // 모임 선택
	    	 System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         Scanner scan = new Scanner(System.in);
	         System.out.print("수정할 모임 이름 : ");
	         String input = scan.nextLine();
	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         
	         // 게시물 리스트 출력
	         File dir = new File(path + "\\" + input);

	         if (dir.exists()) {

	            File[] list = dir.listFiles();

	            for (File f : list) {
	               if (f.isFile()) {
	                  System.out.printf("%s\n", f.getName().replace(".txt", ""));
	               }
	            }
	         }



	         // 게시물 선택
	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         System.out.println("엔티 입력 시 이전으로 돌아갑니다.");
	         System.out.print("수정할 게시물 : ");
	         String secondInput = scan.nextLine();
	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         System.out.println();
	         System.out.println();

	         
	         String newPath = path + "\\" + input + "\\" + secondInput + ".txt";
	       
	         
	         ArrayList<String> arrayAll = new ArrayList<String>();
	         File file = new File(newPath);
	         File[] txtList = file.listFiles();
	         
	       
	         // 게시물 내용 숫자 라인으로 출력
	         BufferedReader reader = new BufferedReader(
	               new FileReader(newPath));
	         String line = null;
	         int index = 1;

	         while ((line = reader.readLine()) != null) {
	            System.out.printf("[%02d] %s\n", index, line);
	            arrayAll.add(line);
	            index++;
	         }

	         reader.close();

	         int firstNum = 0;
	         int secondNum = 0;


	         // 삭제할 구간 선택
	         while (true) {
	        	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	            System.out.print("삭제할 구간 숫자 2개 입력해주세요.\n첫번째 숫자 : ");
	            firstNum = Integer.parseInt(scan.nextLine());
	            System.out.print("두번째 숫자: ");
	            secondNum = Integer.parseInt(scan.nextLine());

	            if (firstNum < 0 || firstNum > index || secondNum < 0 || secondNum > index
	                 || secondNum < firstNum) {
	               System.out.println("올바른 번호를 입력해주세요");
	            } else {
	              System.out.print("해당 구간을 삭제 하시겠습니까? (y/n) : ");
	              String confirm = scan.nextLine();
	              if (confirm.equalsIgnoreCase("Y")) {
	                break;
	              }
	          }
	         } // while

	        
	         try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(newPath));

	            for (int i = 0; i < firstNum; i++) {
	               writer.write(arrayAll.get(i) + "\r\n");
	            }

	            for (int i = secondNum; i < arrayAll.size(); i++) {
	               writer.write(arrayAll.get(i) + "\r\n");
	            }

	            System.out.println("삭제 완료되었습니다.");
	            writer.close();
	            
	            readMoim();

	         } catch (Exception e) {
	            System.out.println("AdminMoim.manage() : " + e.toString());
	         }

	      } catch (Exception e) {
	         System.out.println("AdminMoim.manage():" + e.toString());
	         e.printStackTrace();
	      }

	   } // manage()



	} // AdminMoim
