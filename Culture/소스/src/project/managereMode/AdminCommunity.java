package project.managereMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminCommunity {


	// 1. 읽기
	public boolean read(String path) {

		try {
			String[] category = path.split("\\\\");
			// 파일 경로 추가
			File file = new File(path);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				int n = 1;
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("                [%s] 리스트\n", category[1]);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				while ((line = reader.readLine()) != null) {
					String[] name = line.split("■");
					System.out.println(name[0]);
					n++;
				}
				reader.close();
				Admin_Main.adminSel1_1(path);
				
				
			} else {
				System.out.println("게시물 리스트 출력 완료");
			}


		} catch (Exception e) {
			System.out.println("admincommu.read() : " + e.toString());
			e.printStackTrace();
		}

		return true;

	} // read()



	// 2. 추가
	public boolean add(String path) {

		String titleName = ""; // 제목
		String dirName = ""; // 감독
		String actName = ""; // 배우
		String playTime = ""; // 상영일자
		String evaluate = ""; // 평점
		String genreVal = "";

		Scanner scan = new Scanner(System.in);

		System.out.print("제목 : ");
		titleName = scan.nextLine();

		System.out.print("감독 : ");
		dirName = scan.nextLine();

		System.out.print("배우 : ");
		actName = scan.nextLine();

		System.out.print("상영일자 : ");
		playTime = scan.nextLine();

		System.out.print("평점 : ");
		evaluate = scan.nextLine();

		System.out.print("장르 : ");
		genreVal = scan.nextLine();

		/*
		
		각각 유효성 검사 추가하기
		
		while(true) {
		System.out.print("상영 시간 : ");
		runtime = scan.nextLine();
		if (Integer.parseInt(runtime) > 400 || Integer.parseInt(runtime) < 10) {
			System.out.println("올바른 상영 시간을 입력하세요.");
		} else {break;}
		
		*/


		String temp = String.format("%s■%s■%s■%s■%s■%s\r\n", titleName, dirName, actName, playTime,
				evaluate, genreVal);



		System.out.printf("제목 : %s\r\n감독 : %s\r\n배우 : %s\r\n상영 일정 : %s\r\n평점 : %s\r\n장르 : %s\r\n", titleName,
				dirName, actName, playTime, evaluate, genreVal);
		System.out.println();
		System.out.print("추가 하시겠습니까? (y/n) : ");


		switch (scan.nextLine()) {
			case "y":
			case "Y":
				break;
			default:
				return false;
		}


		try {


			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			writer.write(temp);
			System.out.println("추가 완료되었습니다.");
			writer.close();
			
			Admin_Main.adminSel1_1(path);

		} catch (Exception e) {
			System.out.println("admincommu.add() : " + e.toString());
		}

		return true;

	} // add()


	// 3. 삭제
	public boolean delete(String path) {


		String line = "";
		String temp = "";
		String input = "";
		int index = 1;
		int sel = 0;
		ArrayList<String> titleArray = new ArrayList<String>();
		ArrayList<String> arrayAll = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);



		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				temp = line.split("■")[0];
				System.out.printf("%d. %s\r\n", index, temp);
				arrayAll.add(line);
				titleArray.add(temp);
				index++;
			}
			reader.close();

		} catch (Exception e) {
			System.out.println("admincommu.delete() : " + e.toString());
		}



		while (true) {
			System.out.print("삭제할 번호를 입력해주세요 : ");
			sel = Integer.parseInt(scan.nextLine());
			if (sel > titleArray.size() || sel < 0) {
				System.out.println("올바른 번호를 입력해주세요");
			} else {
				System.out.printf("\"%s\"을(를) 삭제 하시겠습니까? (y/n) : ", titleArray.get(sel - 1));
				input = scan.nextLine();
				if (input.equalsIgnoreCase("Y")) {
					break;
				}
			}
		}


		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (int i = 0; i < arrayAll.size(); i++) {
				if (i == sel - 1) {
					continue;
				}
				writer.write(arrayAll.get(i) + "\r\n");
			}

			System.out.println("삭제 완료되었습니다.");
			writer.close();
			Admin_Main.adminSel1_1(path);
		} catch (Exception e) {
			System.out.println("admincommu.delete() : " + e.toString());
		}

		return true;

	} // delete()



	// 4. 비속어 리스트 출력
    
    public boolean addBan(String path) {
       


       Scanner scan = new Scanner(System.in);

       
       
       String line = "";
       String input = "";
       int index = 0;
       
       ArrayList<String> titleArray = new ArrayList<String>();
       ArrayList<String> arrayAll = new ArrayList<String>();
       
       

       try {

          BufferedReader reader = new BufferedReader(new FileReader(path));
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          System.out.println("                  금지어 목록");
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          while ((line = reader.readLine()) != null) {
             System.out.printf("%d. %s\r\n", index+1, line);
             arrayAll.add(line);
             index++;
          }
          reader.close();
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          System.out.println();
          System.out.println();
          
          
          System.out.print("추가할 금지어 입력 : ");
          String addWord = scan.nextLine();


          System.out.println();
          System.out.print("추가 하시겠습니까? (y/n) : ");


          switch (scan.nextLine()) {
             case "y":
             case "Y":
                break;
             default:
                return false;
          }
          
          

          try {
             
             BufferedWriter writer = new BufferedWriter(new FileWriter(path));

             for (int i = 0; i < index; i++) {
                writer.write(arrayAll.get(i) + "\r\n");
             }
             
             writer.write(addWord);
             System.out.println("금지어 추가 완료되었습니다.");
             writer.close();
             Admin_Main.adminSel1_1(path);
             
          } catch (Exception e) {
             System.out.println("admincommu.addBan() : " + e.toString());
          }
          
          
       } catch (Exception e) {
          System.out.println("admincommu.addBan() : " + e.toString());
       }

       return true;
    
    } //addBan()
    
    
    
    
    
    public boolean deleteBan(String path) {

       Scanner scan = new Scanner(System.in);

       
       String line = "";
       String input = "";
       int index = 0;
       
       ArrayList<String> titleArray = new ArrayList<String>();
       ArrayList<String> arrayAll = new ArrayList<String>();
       
       

       try {

          BufferedReader reader = new BufferedReader(new FileReader(path));
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          System.out.println("                  금지어 목록");
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          while ((line = reader.readLine()) != null) {
             System.out.printf("%d. %s\r\n", index+1, line);
             arrayAll.add(line);
             index++;
          }
          reader.close();
          System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
          System.out.println();
          System.out.println();
          
          
          System.out.print("삭제할 금지어 번호 입력 : ");
          int deleteWord = Integer.parseInt(scan.nextLine());


          System.out.println();
          System.out.print("삭제 하시겠습니까? (y/n) : ");


          switch (scan.nextLine()) {
             case "y":
             case "Y":
                break;
             default:
                return false;
          }
          
          

          try {
             
             BufferedWriter writer = new BufferedWriter(new FileWriter(path));

             
             if (deleteWord==index) {
                
                for (int i = 0; i < index-1; i++) {
                   writer.write(arrayAll.get(i) + "\r\n");
                }
                
             }
             else {
                
                for (int i=0; i<deleteWord-1; i++) {
                   writer.write(arrayAll.get(i) + "\r\n");
                }
                
                for (int i=deleteWord; i<index; i++) {
                   writer.write(arrayAll.get(i) + "\r\n");
                }
                
             }
             
             
             System.out.println("삭제 완료되었습니다.");
             writer.close();
             
             Admin_Main.adminSel1_1(path);
             
          } catch (Exception e) {
             System.out.println("admincommu.deleteBan() : " + e.toString());
          }
          
          
       } catch (Exception e) {
          System.out.println("admincommu.deleteBan() : " + e.toString());
       }

       return true;
       
    }



} // AdminCommunity
