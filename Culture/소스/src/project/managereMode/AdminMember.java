package project.managereMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import project.login.User;
import project.login.nonUserLogin;
import project.login.userLogin;

public class AdminMember {

	public static Scanner scan = new Scanner(System.in);


	// 1. 회원 검색
	public void findMem() {
		Scanner scan = new Scanner(System.in);

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("             아이디 검색하기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");


		// 검색 조건을 입력
		System.out.println("검색할 아이디를 입력하세요:  ");
		String input = scan.nextLine();
		User result = null;


		for (project.login.User u : project.login.Data.list) {
			if (u.getId().equals(input)) {
				result = u;
				break;
			}
		}


		if (result != null) { // result, 즉 Data클래스의 list와 input값이 동일한 값이 존재한다면 상세정보를 출력

			System.out.println("[아이디] : " + result.getId());
			System.out.println("[비밀번호] : " + result.getPassword());
			System.out.println("[이름] : " + result.getName());
			System.out.println("[생년월일] : " + result.getBirth());
			System.out.println("[성별] : " + result.getGender());
			System.out.println("[전화번호] : " + result.getTel());
			System.out.println("[팔로우] : " + result.getFollow());
			System.out.println("[장르] : " + result.getGenre());
			System.out.println("[출신학교] : " + result.getSchool());

		}
	}



	// 2. 블랙 리스트 -> 일단 임의로 추가하고 출력하는것까지만 하기
	public void blackList() {


		try {
			BufferedReader reader =
					new BufferedReader(new FileReader("data\\블랙리스트\\블랙리스트.txt"));

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("               블랙리스트 리스트 출력");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");


			for (BlackList b : BlacklistData.blist) {
				System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", b.getBid(), b.getBpassword(),
						b.getBname(), b.getBbirth(), b.getBgender(), b.getBtel(), b.getBfollow(),
						b.getBgenre(), b.getBschool());

			}


		} catch (Exception e) {
			e.printStackTrace();
		}



		System.out.println();
		System.out.println();

		System.out.println("[Q]블랙리스트를 추가하시겠습니까?[Y/N]");
		String answer = scan.nextLine();


		if (answer.equals("Y") || answer.equals("y")) {
			addBlackList();
		} else {
			System.out.println("[!]블랙리스트 리스트 추가를 실행하지 않습니다.");
		}

	} // blackList()


	// 블랙리스트 추가하는 메소드
	public void addBlackList() {
		System.out.println("[Q]블랙리스트로 추가 할 유저의 아이디를 입력하세요");
		String blackId = scan.nextLine();

		User result = null;

		for (User u : project.login.Data.list) {
			if (blackId.equals(u.getId())) {
				result = u;
			}
		}


		BlackList b = new BlackList(result.getId(), result.getPassword(), result.getName(),
				result.getBirth(), result.getGender(), result.getTel(), result.getFollow(),
				result.getGenre(), result.getSchool());

		BlacklistData.blist.add(b);


		System.out.println();
		System.out.println("[!]블랙리스트에 추가 되었습니다.");
		System.out.println();
		System.out.println();

	}



	// 3. 로그인 통계
	public void logGraph() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                   로그인 통계");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("금일  회원의 로그인 수는" + userLogin.userLoginCount + "입니다");
		System.out.println("금일 비회원의 로그인 수는" + nonUserLogin.nonUserCount + "입니다");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}



	} // logGraph()



}
