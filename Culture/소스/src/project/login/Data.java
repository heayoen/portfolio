package project.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {

	public static ArrayList<User> list = new ArrayList<User>();

	public static void load() {

		// 1. 먼저 파일로부터 기존유저의 아이디와 비밀번호를 받아와서 컬렉션에 옮겨줘야함


		try {// 파일에 있는 dummy데이터를 컬렉션으로 옮겨줌

			BufferedReader reader = new BufferedReader(new FileReader("data\\마이페이지\\user.txt"));
			String line = null;

			while ((line = reader.readLine()) != null) {

				// user03■pw0003■이름03■20010505■1■010-1122-3344■Y■코미디■다다초
				String temp[] = line.split("■");
				User u = new User(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6],
						temp[7], temp[8]);

				list.add(u);

			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	// 가입한 회원들을 다시 파일로 올려주는 메소드
	public static void Save() {

		/*
		 * userLogin.list -> 유저.txt
		 */

		// 1. 회원가입 한 유저별로 폴더 생성하고 info파일에다가 파일 출력
		try {
			for (User u : list) {

				String path = String.format("data\\마이페이지\\%s", u.getId());
				File dir = new File(path);
				dir.mkdir(); // *입력한 유저대로 폴더 생성


				String path2 =
						String.format("data\\마이페이지\\%s\\info.txt", u.getId());
				File file = new File(path2);

				file.createNewFile(); // *비밀번호로 파일 생성

				
				BufferedWriter writer = new BufferedWriter(new FileWriter(path2)); // 그 비밀번호 파일에다가
																					// 작성
				
				String path3 = String.format("data\\마이페이지\\%s\\reviews.txt", u.getId());
				
				File reviewFile = new File(path3);

				reviewFile.createNewFile();

				String line = String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s", u.getId(),
						u.getPassword(), u.getName(), u.getBirth(), u.getGender(), u.getTel(),
						u.getFollow(), u.getGenre(), u.getSchool());


				writer.write(line);
				writer.close();

			}

			// 2. 통합본에다가 파일 올려주기
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\마이페이지\\user.txt"));
			for (User u : list) {


				String line = String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s\n", u.getId(),
						u.getPassword(), u.getName(), u.getBirth(), u.getGender(), u.getTel(),
						u.getFollow(), u.getGenre(), u.getSchool());

				writer.write(line);



			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}


