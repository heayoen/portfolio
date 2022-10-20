package project.login;

import java.util.HashSet;
import java.util.Scanner;


// 메인 메뉴에서 2번을 눌렀을 경우 회원가입 클래스로 접근
public class SignUp {

	static Scanner scan = new Scanner(System.in);
	private static String id;
	private static String password;
	private static String name;
	private static String birth;
	private static String gender;
	private static String tel;
	private static String follow;
	private static String genre;
	private static String school;



	// 회원가입 하려는 정보를 사용자로부터 입력 받는 메소드
	public static void addMember() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                   [회원가입]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");



		while (true)

		{// 아이디 입력
			System.out.println("[Q]가입할 아이디를 입력하세요");
			id = scan.nextLine();
			if (IdCheck(id)) {
				continue; // return이 true일경우 메소드 실행
			}
			break; // 정상적일 경우는 break문을 빠져나가도록


		}

		while (true) {// 비밀번호 입력
			System.out.println("[Q]가입할 비밀번호를 입력하세요");
			password = scan.nextLine();
			if (passwordCheck(password)) {
				continue;
			}
			break;
		}

		while (true) {// 이름입력

			System.out.println("[Q]이름을 입력하세요");
			name = scan.nextLine();
			if (nameCheck(name)) {
				continue;
			}
			break;
		}

		while (true) {// 생년월일
			System.out.println("[Q]생년 월일을 입력하세요(예시:1998-08-29)");
			birth = scan.nextLine();
			if (birthCheck(birth)) {
				continue;
			}
			break;
		}


		while (true) {// 성별
			System.out.println("[Q]성별을 입력하세요(남자1,여자2)");
			gender = scan.nextLine();
			if (genderCheck(gender)) {
				continue;
			}
			break;
		}


		while (true) {// 전화번호
			System.out.println("[Q}전화번호를 입력하세요(예시:010-xxxx-xxxx)");
			tel = scan.nextLine();
			if (tellCheck(tel)) {
				continue;
			}
			break;
		}



		while (true) {// 팔로우
			System.out.println("[Q}팔로우 여부를 입력하세요[Y/N]");
			follow = scan.nextLine();
			if (followCheck(follow)) {
				continue;
			}
			break;
		}


		while (true) {// 장르
			System.out.println("[Q]좋아하는 장르의 이름을 입력하세요(3개까지 입력 가능)");
			System.out.println("1.액션 2.드라마 3.멜로 4.스릴러 5.코믹");
			System.out.println("6.다큐멘터리 7.애니메이션 8.공포");
			genre = scan.nextLine();
			if (genreCheck(genre)) { // 1.장르의외에 다른거 들어오는거 제한하는 유효성 검사
				continue;
			}
			if (genreCheck2(genre)) { // 2.장르의 중복성 검사
				continue;
			}
			break;
		}



		// 초등학교
		System.out.println("[Q] 출신 초등학교를 입력하세요");
		school = scan.nextLine();



		// 새로 받은 회원들의 정보들을 컬렉션으로 옮겨줘야함.
		//// user03■pw0003■이름03■20010505■1■010-1122-3344■Y■코미디■다다초
		User u = new User(id, password, name, birth, gender, tel, follow, genre, school);

		Data.list.add(u); // 새로 추가한 회원정보를 list컬렉션에 add해줌 ->나중에 파일로 따로 올려줘야함

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("   [!] 회원가입이 완료되었습니다.%s님 환영합니다\n", u.getName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}



	// 아이디 유효성 검사
	public static boolean IdCheck(String id) {
		// 1. 중복 아이디 검색
		for (User u : Data.list) {
			if (u.getId().equals(id)) {
				System.out.println("[!]중복된 아이디입니다.");
				System.out.println();
				return true;
			}


			// 2.아이디 길이 제한 두기 -4글자 이상, 6글자 이하
			if (!(id.length() <= 6 && id.length() >= 4)) {
				System.out.println("[!]아이디의 길이는 6글자 이하, 4글자 이상여야 합니다.");
				System.out.println();
				return true;
			}


			// 3. 아이디에 대문자,소문자,숫자가 오지 않을 경우 제한
			for (int i = 0; i < id.length(); i++) {
				int ch = id.charAt(i);
				if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')
						&& !(ch >= 'A' && ch <= 'Z')) {
					System.out.println("[!]영어 대문자, 소문자, 숫자만 비밀번호에 사용할 수 있습니다.");
					System.out.println();
				}

			}



		}
		return false;

	}



	// 비밀번호 유효성 체크
	public static boolean passwordCheck(String password) {
		// 1. 비밀번호 길이 제한 두기 -10 ~ 16자리로 입력
		if (password.length() < 4 || password.length() > 12) {
			System.out.println("[!]4 ~ 12 자리로 입력해주세요.");
			System.out.println();
			return true;
		}

		// 2. 비밀번호에 들어갈 언어 제한두기 -영어 대문자, 소문자
		for (int i = 0; i < password.length(); i++) {
			int ch = password.charAt(i);
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')
					&& !(ch >= 'A' && ch <= 'Z')) {
				System.out.println("[!]영어 대문자, 소문자, 숫자만 비밀번호에 사용할 수 있습니다.");
				System.out.println();
				return true;
			}
		}

		return false;
	}


	// 전화번호 유효성 체크
	// 010-5029-5334 처럼 앞에는 무조건 010, 4번째 9번째 - 들어가야함
	public static boolean tellCheck(String tel) {
		if (!((tel.charAt(3) == '-' && tel.charAt(8) == '-'
				&& (tel.substring(0, 3).equals("010"))))) {
			System.out.println("[!]잘못된 전화번호 형식입니다.");
			System.out.println();
			return true;
		}
		return false;
	}



	// 이름 유효성 검사
	// 한글만 들어올 수 있도록
	public static boolean nameCheck(String name) {
		if (!(name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") && (name.length() >= 2) && (name.length() <= 5))) { // 정규식
																									// 활용
			System.out.println("[!]닉네임에는 2글자 이상 5글자 이하의 한글만 들어갈 수 있습니다.");
			System.out.println();
			return true;
		}

		return false;

	}



	// 생년월일 유효성 체크
	// 글자수 1998-08-29로 제한두기
	public static boolean birthCheck(String birth) {
		if (!(birth.charAt(4) == '-' && birth.charAt(7) == '-')) {
			System.out.println("[!]생년월일은 예시:1998-08-29 형식으로 입력해야합니다.");
			System.out.println();
			return true;
		}
		return false;
	}



	// 성별유효성 체크
	// 숫자가 1,2 말고 다른게 들어왔을때 제한두기
	public static boolean genderCheck(String gender) {
		if (!((gender.equals("1")) || (gender.equals("2")))) {
			System.out.println("[!] 성별은 숫자 1,2로만 입력해야합니다.");
			System.out.println();
			return true;
		}
		return false;
	}



	// follow유효성 체크
	// Y,N y,n만 들어올 수 있도록 제한두기
	private static boolean followCheck(String follow) {
		if (!((follow.equals("Y")) || (follow.equals("N") || follow.equals("y"))
				|| (follow.equals("n")))) {
			System.out.println("[!] 팔로우 여부는 Y/N로만 입력해야 합니다.");
			System.out.println();
			return true;
		}
		return false;
	}



	// 1.장르 의외에 다른 단어가 들어올 경우
	public static boolean genreCheck(String genre) {
		int count = 0;
		String[] temp = genre.split(",");

		String[] Genres = {"드라마", "액션", "판타지", "애니메이션", "스릴러", "공포", "코믹", "다큐멘터리", "멜로"};

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < Genres.length; j++) {
				if (temp[i].equals(Genres[j])) {
					count++;
				}
			}
		}


		if (count < temp.length) {
			System.out.println(count);
			System.out.println("[!]장르의 이름만 입력해주세요");
			System.out.println();
			return true;
		}

		return false;



	}

	// 2.장르가 중복되어 들어올 경우
	public static boolean genreCheck2(String genre) {
		String[] temp = genre.split(",");

		HashSet<String> genreTemp = new HashSet<String>();

		for (String str : temp) {
			genreTemp.add(str); // 중복제거
		}

		if (temp.length != genreTemp.size()) {
			System.out.println("[!]장르가 중복되었습니다.");
			System.out.println();
			return true;
		}
		return false;


	}


}


