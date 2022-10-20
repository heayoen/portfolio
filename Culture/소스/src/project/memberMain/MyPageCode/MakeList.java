package project.memberMain.MyPageCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import project.login.User;

/**
 * 
 * 사용자의 정보가 담긴 텍스트 파일을 배열로 변환
 * @author 황혜연 
 *
 */
public class MakeList {
	
	private static String userId;
	private static ArrayList<User> userList;
	
	
	/**
	 * 
	 * @param userId 대상의 아이디
	 */
	public MakeList(String userId) {
		
		MakeList.userId = userId;
		MakeList.userList = new ArrayList<User>();
	}


	/**
	 * 
	 * @return info.txt파일 안의 사용자정보를 배열로 반환
	 */
	public String[] infoList() {
		
		String path = String.format("%s\\info.txt",userId);	
		
		String[] temp = new String[9];
		try {
			BufferedReader reader  
				= new BufferedReader(new FileReader("data\\마이페이지\\" + path));
			
			String info = reader.readLine();
			temp =  info.split("■");
			//System.out.println(Arrays.toString(temp));
			reader.close();
			
		} catch (Exception e) {
			System.out.println("UserInfo.main");
			e.printStackTrace();
		}
		return temp;
	}//infoList
	
	
	
	/**
	 * 사용자 정보를 배열로 입력받으면 info.txt파일 수정
	 * @param temp 변경된 사용자 정보가 입력된 배열
	 */
	public void saveList(String[] temp) { 
		String changedInfo = temp[0] + "■" 
							+ temp[1] + "■"
							+ temp[2] + "■"
							+ temp[3] + "■"
							+ temp[4] + "■"
							+ temp[5] + "■"
							+ temp[6] + "■"
							+ temp[7] + "■"
							+ temp[8] ;
		
		String path = String.format("%s\\info.txt",userId);	
		
		
		try {
		
			File file = new File("data\\마이페이지\\" + path);
			file.delete();
			
			BufferedWriter writer 
			= new BufferedWriter(new FileWriter("data\\마이페이지\\" + path));
			
			writer.write(changedInfo);
			writer.close();
			
			
			
		} catch (Exception e) {
			System.out.println("MyPage.saveInfo");
			e.printStackTrace();
		}
		
	
	} //save
	
	
	/**
	 * 
	 * @param list 대상의 reviews.txt파일의 내용을 ArrayList배열에 저장 
	 * @param userId 리뷰를 검색하고자 하는 대상의 아이디
	 */
	public static void makeReviewArray(ArrayList<UserReview> list, String userId) {
		
		String path = String.format("data\\마이페이지\\%s",userId);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + "\\reviews.txt"));
			
			String line = null;
			
			while((line = reader.readLine())!= null) {
				
				String[] temp = line.split("■");
				String[] ctemp = temp[0].split("-");
				
				UserReview reviews 
					= new UserReview(ctemp[0], ctemp[1], ctemp[2], temp[1], temp[2], temp[3]);
				
				list.add(reviews);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("MakeList.makeReviewArray");
			e.printStackTrace();
		}
		
		
	}//makeReviewArray
	
	
	/**
	 * 
	 * @param userlist  user.txt파일에 저장된 사용자의 정보들을 입력받는 배열
	 * @return 입력받은 배열을 반환
	 */
	public static ArrayList<User> makeUserList(ArrayList<User> userlist) {
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader("data\\마이페이지\\user.txt")); 		
			
			
			String line = null;
			
			while((line = reader.readLine()) != null){
								
				String temp[] = line.split("■"); 
				
				userList.add(new User(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]));
				
				
			}	
			reader.close();
			return userList;
			
			
		} catch (Exception e) {
			
			System.out.println("MakeList.makeUserList");
			e.printStackTrace();
		}
		return null;
		
	} //makeUserList

	
	
	/**
	 * user.txt파일을 수정하는 메서드
	 * @param changedInfo 바꿀 사용자 정보를 파일 입력형식에 맞추어 수정한 문자열
	 */
	public static void changeUserList(String changedInfo) {
		
		makeUserList(userList);
		
		
		StringBuilder loginInfo = new StringBuilder("");
		
		for(User u : userList) {
			
			if(u.getId().equals(userId)){
				
				loginInfo.append(changedInfo + "\n");
				
			} else {
				
				loginInfo.append(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s\n"
													, u.getId()
													, u.getPassword()
													, u.getName()
													, u.getBirth()
													, u.getGender()
													, u.getTel()
													, u.getFollow()
													, u.getGenre()
													, u.getSchool()));
											
			}
			
		}//for
		
		File userFile = new File("data\\마이페이지\\user.txt");
		userFile.delete();
		
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\마이페이지\\user.txt"));
			writer.write(loginInfo.toString());
			writer.close();		
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}//changeUserList
	
}//class
