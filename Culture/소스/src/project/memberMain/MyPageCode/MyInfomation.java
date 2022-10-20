package project.memberMain.MyPageCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import project.memberMain.MemberMain;
/**
 * 내 정보보기 화면 콘솔에 출력
 * @author 황혜연
 *
 */
public class MyInfomation { 

	private String userId;
	private Scanner scan;
	private MakeList makeList;

	/**
	 * 
	 * @param userId 로그인한 사용자 아이디
	 */
	public MyInfomation(String userId) {
		super();
		this.userId = userId;
		this.scan = new Scanner(System.in);
		this.makeList = new MakeList(userId);
	}
	
	/**
	 * 수정하고자 하는 정보를 입력받고 저장
	 */
	public void newInfo() { //myInfo로부터 옴
		
		String[]temp = makeList.infoList();
		String userTXT = userinfo();
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **아무것도 입력하지 않으면 마이페이지로 돌아갑니다.**");
		System.out.println(" ⦿ 수정하실 정보의 번호를 입력해주세요.");
		System.out.print(" ⦿ 입력 : ");
		
		String input = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		if(input.equals("1")){
			
			System.out.println();
			System.out.println(" **아무것도 입력하지 않으면 정보수정 페이지로 돌아갑니다**");
			System.out.print(" 수정할 이름을 입력하세요. : ");
			input = scan.nextLine();
			if(input.length() > 0 && !input.contains("■")) {
				MakeList.changeUserList(userTXT.replace(temp[2], input));
				temp[2] = input;
				saveMessage(temp);
			} else {
				System.out.println("정보수정 페이지로 돌아갑니다.");
				
				this.newInfo();			
			}
			
			
			
		}else if(input.equals("2")){
			
			System.out.println();
			System.out.println(" 생일은 yyyy-mm-dd형식으로 입력하세요");
			
			
			//유효성 검사	
			
			while (true) {// 생년월일
				System.out.print(" 수정할 생일을 입력하세요. : ");				
				input = scan.nextLine();
				if (check(input)) {
					continue;
				}
				break;
			}

			
			MakeList.changeUserList(userTXT.replace(temp[3], input));
			temp[3]= input;
			saveMessage(temp);
			
			
		}else if(input.equals("3")){
			
			System.out.println();
			System.out.println(" **exit을 입력하면 <내 정보 보기>로 돌아갑니다**");
			chkGender(temp, userTXT);
			
			
		}else if(input.equals("4")){
			
			System.out.println();
			System.out.println(" **exit을 입력하면 <내 정보 보기>로 돌아갑니다**");
			System.out.println(" **핸드폰번호는 010-1234-5678형식으로 입력하세요**");
			chkPhone(temp, userTXT);
			
			
		} else if(input.equals("5")){
			
			System.out.println();
			System.out.print(" 리뷰공개여부를 설졍하세요. (공개 Y /비공개 N) : ");
			input = scan.nextLine().toUpperCase();
			
			while(!(input.equals("Y") || input.equals("N"))) {
				System.out.println();
				System.out.println(" 리뷰공개여부는 Y 또는 N으로 입력해야 합니다! ");
				System.out.print(" 리뷰공개여부를 설졍하세요. (공개 Y /비공개 N) : ");

				input = scan.nextLine().toUpperCase();
		
			}
			
			
			MakeList.changeUserList(userTXT.replace(temp[6], input));
			temp[6] = input;
			saveMessage(temp);
			
			
		}else if(input.equals("6")){
			
			System.out.println();
			System.out.print(" 수정할 본인확인대답을 입력하세요. : ");
			input = scan.nextLine();
			MakeList.changeUserList(userTXT.replace(temp[8], input));
			temp[8] = input;
			saveMessage(temp);
			
			
		}else if (input.equals("")) { 
			
			System.out.println("   마이페이지로 돌아갑니다. 엔터를 눌러주세요");
			scan.nextLine();
			MyPageMain.myPageMain(userId);
			//scan.close();//스캔종료확인용
			
		} else {
			System.out.println();
			System.out.print("      **** 번호를 정확히 입력해주세요 ****");
			scan.nextLine();
			this.newInfo();
			
		}
		
		
		
	}


	private String userinfo() {
		
		String path = String.format("%s\\info.txt",userId);	
		
		String[] temp = new String[9];
		try {
			BufferedReader reader  //TODO 상대경로로 바꿀 것!!
				= new BufferedReader(new FileReader("data\\마이페이지\\" + path));
			
			String info = reader.readLine();
			reader.close();
			return info;
		} catch (Exception e) {
			System.out.println("UserInfo.main");
			e.printStackTrace();
		}
		return null;
	}


	private void chkGender(String[] temp, String userTXT) {
		String input;
		System.out.print(" 수정할 성별을 입력하세요.(남자: 1 / 여자: 2) : ");
		input = scan.nextLine();
		
		if(input.equals("1") || input.equals("2")) {
			MakeList.changeUserList(userTXT.replace(temp[4], input));
			temp[4] = input;
			saveMessage(temp);
			
		} else if(input.equals("exit")) {
			System.out.println(" **내 정보 페이지로 돌아갑니다**");
			this.myInfo();
		} else {
			System.out.println();
			System.out.println(" 1 과 2 중에 입력해주세요!!");
			this.chkGender(temp, userTXT);
		}
	}


	private void chkPhone(String[] temp, String userTXT) {
		String input;
		System.out.print(" 수정할 핸드폰번호을 입력하세요. : ");
		input = scan.nextLine();
		
		
		if(input.charAt(3) == '-' && input.charAt(8) == '-') {
			String str = input.replace("-", "");
			for(int i = 0 ; i<str.length() ; i++) {
				if(str.charAt(i)<='9' && str.charAt(i)>='0') {
					MakeList.changeUserList(userTXT.replace(temp[5], input));
					temp[5] = input;
					saveMessage(temp);
				}
			}
			
		} else if(input.equals(null)){
			System.out.println(" 정보수정 페이지로 이동합니다.");
			scan.nextLine();
			this.newInfo();
			
		} else {
			System.out.println();
			System.out.println(" 번호형식을 다시 확인해주세요!!");
			this.chkPhone(temp, userTXT);
		}
	}

	
	private boolean check(String input) {
		
		if (!(input.charAt(4) == '-' && input.charAt(7) == '-')) {
			System.out.println(" **생일은 yyyy-mm-dd형식으로 입력해주세요 **");
			System.out.println();
			return true;
		}
		return false;
		
		
	}


	private void saveMessage(String[] temp) {
		System.out.println(" 변경 완료! 엔터키를 누르시면 내 정보 보기로 이동합니다!");
		makeList.saveList(temp);
		
		scan.nextLine();
		this.myInfo(); 
	}
		
	/**
	 * 현재 정보 출력
	 */
	public void myInfo() {

		String[]temp = makeList.infoList();
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                 내 정보 보기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		System.out.println(" ⦿  아이디   : " + temp[0]);
		System.out.println(" 1. 이름\t   : " + temp[2]);
		System.out.println(" 2. 생일\t   : " + temp[3]);
		System.out.println(" 3. 성별\t   : " + (temp[4].equals("1") ? "남자" : "여자"));
		System.out.println(" 4. 핸드폰번호 : " + temp[5]);
		System.out.println(" 5. 리뷰공개여부: " + (temp[6].equals("Y") ? "공개" : "비공개"));
		System.out.println(" 6. 본인확인질문: " + temp[8]);
		
		choiceMenu();
				
	}//myInfo()


	private void choiceMenu() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 1. 내 정보 수정하기");
		System.out.println(" 2. 이전 화면으로 돌아가기");
		System.out.println();
		System.out.print  (" ⦿ 번호 선택 : ");
		
		String input = scan.nextLine();
		
		if(input.equals("1")) {
			System.out.println("   정보 수정 화면으로 이동합니다. 엔터를 눌러주세요");
			scan.nextLine();
			this.newInfo();		
			
		} else if(input.equals("2")) {
			System.out.println("  마이페이지 화면으로 돌아갑니다. 엔터를 눌러주세요");
			MyPageMain.myPageMain(userId);
			this.myInfo();
		} else {
			System.out.println();
			System.out.println(" **정확한 숫자를 입력해주세요 **");
			this.choiceMenu();			
		}
	}
	
	
	
}
