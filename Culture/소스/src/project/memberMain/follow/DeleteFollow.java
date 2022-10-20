package project.memberMain.follow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import project.login.userLogin;
import project.memberMain.memOutput;

public class DeleteFollow {

   public static void deleteFollow() {
      try {
         
         String userId = String.format("%s", userLogin.id) ;
//         String path = String.format("data\\마이페이지\\%s\\following.txt", userId);      //id
         
//         String userId = "user01";
         String path = String.format("data\\마이페이지\\%s\\following.txt", userId);
      
         File user = new File(path);
         
         Scanner scan = new Scanner(System.in);

         System.out.print("삭제할 유저 ID입력: ");
         String user1 = scan.nextLine();
         
         
         
         BufferedReader reader = new BufferedReader(new FileReader(user));
         
         String line = null;
         int count = 0;
         int point = -1;
         
         while((line = reader.readLine())!= null) {
            
            if (user1.equals(line)) {
               point = count;
               break;
            }
            count++;            
         }
         reader.close();
         
         
         
         if(point == -1) {
            memOutput.line(1);
            System.out.println("팔로우 목록에 있는 아이디를 입력해주세요.");
            memOutput.line(1);
            memOutput.pause();
            memOutput.line(1);
            ManageFollow.manageFollow();
         }
         
         
         
         
         
         String alterPath = "data\\게시물 수정\\following.txt";
         File alterFile = new File(alterPath);
         alterFile.createNewFile();
         
         BufferedWriter writer = new BufferedWriter(new FileWriter(alterFile));
         reader = new BufferedReader(new FileReader(user));
         
         line = null;
         count = 0;
         
         
         while((line = reader.readLine()) != null) {
            if(point == count) {
               count++;
               continue;
            }
            writer.write(line);
            writer.newLine();
            count++;
         }
      
         reader.close();
         writer.close();
         
         user.delete();
         
         alterFile.renameTo(new File(path));
         
         //////////////////////////////////////////////////////////////////////////
         
         // 상대방 팔로우 목록에서도 삭제
         
         path = String.format("data\\마이페이지\\%s\\following.txt", user1);  //상대방 follwing.txt 경로
         File file = new File(path);
         
         
         
          reader = new BufferedReader(new FileReader(file));
         
         line = null;
         count = 0;
         point = -1;
         
         while((line = reader.readLine())!= null) {
            
            if (userId.equals(line)) {
               point = count;
               break;
            }
            count++;            
         }
         reader.close();
         
         alterPath = "data\\게시물 수정\\following.txt";
         alterFile = new File(alterPath);
         alterFile.createNewFile();
         
         writer = new BufferedWriter(new FileWriter(alterFile));
         reader = new BufferedReader(new FileReader(file));
         
         line = null;
         count = 0;
         
         
         while((line = reader.readLine()) != null) {
            if(point == count) {
               count++;
               continue;
            }
            writer.write(line);
            writer.newLine();
            count++;
         }
      
         reader.close();
         writer.close();
         
         file.delete();
         
         
         
         alterFile.renameTo(new File(path));
         
         

         
         memOutput.line(1);
         System.out.println("삭제 완료!");
         memOutput.line(1);
         memOutput.pause();
         memOutput.line(1);
         ManageFollow.manageFollow();
         
         
                  
         
      } catch (Exception e) {
         System.out.println("DeleteFollow.deleteFollow");
         e.printStackTrace();
      }

   }

}