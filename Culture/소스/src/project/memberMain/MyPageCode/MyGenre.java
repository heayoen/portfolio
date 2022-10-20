package project.memberMain.MyPageCode;
import java.util.HashSet;
import java.util.Scanner;
import project.login.main;
import project.memberMain.MemberMain;

/**
 * 내 장르보기 화면을 나타내기 위한 클래스
 * @author 황혜연
 *
 */

public class MyGenre {
	
	private String userId;
	private Scanner scan;
	private MakeList makeList;
	
	/**
	 * 
	 * @param userId 사용자 아이디
	 */
	public MyGenre(String userId) {
		super();
		this.userId = userId;
		this.scan = new Scanner(System.in);
		this.makeList = new MakeList(userId); // 이름변경필요
	}
	
	/**
	 * info.txt파일로부터 관심장르 받아와 출력
	 */
	public void favGenre() {
		String path = String.format("%s\\info.txt",userId);	
		
		String[]temp = makeList.infoList();
		
		Title.genreTitle();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("  ⦿ " + temp[2] + " 님의 관심장르 : " + temp[7].replace(",", ", ") );
		System.out.println();
		System.out.println("  1. 관심장르 수정하기");
		System.out.println("  2. 이전 화면으로 돌아가기");
		System.out.print  ("  ⦿ 선택 : ");
		String input = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		if(input.equals("1")) {
			System.out.println();
			System.out.println(" 엔터를 누르시면 관심장르 수정하기로 이동합니다.");
			scan.nextLine();
			newGenre(); ///고치기
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} else if(input.equals("2")){
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("   마이페이지로 돌아갑니다. 엔터를 눌러주세요");
			scan.nextLine(); 
			
			MyPageMain.myPageMain(userId);
			
		} else {
			System.out.println("   정확한 번호를 입력해 주세요.");
			scan.nextLine();
			this.favGenre();
		}
		
	}//favList

    /**
     * 새로운 관심장르 입력받아 파일 수정              
     */
	public void newGenre() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("*관심장르는 최소 1개, 최대 3개까지 설정 가능합니다.*");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" ⦿ 관심장르 설정을 취소하시려면 x를 입력해주세요.");
		System.out.println(" ⦿ 관심장르 설정을 완료하시려면 end 를 입력해주세요");
		
		getNewGenre();
		
	}//newGenre


	private void getNewGenre() {
		
		int i = 0;
		boolean chk = true;
		//String newGenre = "";
		String[] newGenre = new String[3];
		String[] Genres = {"액션", "드라마", "멜로", "스릴러", "코믹", "다큐", "애니메이션", "공포"};
		
	
		while(i<3 && chk == true) {
			
			System.out.print  (" ⦿ 관심장르의 번호를 입력하세요: ");
			
			String input = scan.nextLine();
			
			
			if(input.length()== 1 
					&& input.charAt(0) <= '8'
					&& input.charAt(0) >= '1') {
		
				//newGenre += input;
				newGenre[i] = Genres[Integer.parseInt(input)-1];
				i++;
				
			} else if (input.toLowerCase().equals("end") && i >0) {	
				
				System.out.println();
				System.out.println(" **관심장르 등록을 완료하셨습니다.**");
				chk = false;
				
			} else if(input.toLowerCase().equals("x")){ 
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println(" **관심장르 설정을 취소합니다.**");
				System.out.println(" **내 관심장르 페이지로 돌아갑니다**");
				scan.nextLine();
				this.favGenre();
				chk = false;
	
			} else {
				System.out.println("  ** 1 ~ 8 사이의 숫자만 입력 가능합니다 **");
				scan.nextLine();
			}
			
		}//while
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("변경환 관심장르가 저장되었습니다.");
		
		String[] temp = makeList.infoList(); 
		temp[7] = genreString(newGenre);
		makeList.saveList(temp);
		makeList.changeUserList(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s"
											,temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]));
		
		System.out.println("엔터키를 누르시면 내 관심장르 페이지로 돌아갑니다");
		scan.nextLine();
		this.favGenre();
		
	}//getNewGenre


	private String genreString(String[] newGenre) {
		
		String result = "";
		HashSet<String> genreSet = new HashSet<String>();
		
		for(String s : newGenre) {
			genreSet.add(s);
		}
		
		for(String str : genreSet) {
			result += str + ",";
		}
		
		result = result.replace("null,", "");
		
		if(result.charAt(result.length()-1) == ',') {
			result = result.substring(0, result.length()-1);
		}
		
		return result;
		
	}
	
	
	

}
