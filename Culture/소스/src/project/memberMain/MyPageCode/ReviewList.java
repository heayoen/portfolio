package project.memberMain.MyPageCode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 전체 리뷰 목록을 화면을 출력하는 클래스
 * @author 황혜연
 *
 */
public class ReviewList { // userId랑 password 클래스 멤버 변수로 받아와야함.
	
	private String userId;
	private int year;
	private int month;
	private Scanner scan;
	
	/**
	 * 
	 * @param userId 리뷰목록을 불러올 대상의 아이디
	 */
	public ReviewList(String userId) {
		super();
		this.userId = userId;
		this.scan = new Scanner(System.in);
	}

	
	/**
	 *  대상의 전체 리뷰 목록을 출력함.
	 */
	public void allReviewlist () { 
		
		Title.reviewTitle();
				
		ArrayList<UserReview> list = new ArrayList<UserReview>();		

		
		Calendar c = Calendar.getInstance();
		this.year = c.get(Calendar.YEAR);
		this.month = c.MONTH+2;
		
		MakeList makeList = new MakeList(userId);
		makeList.makeReviewArray(list, userId);
		
		
		
		int i = 1;
		for(UserReview r : list) {
			String title = r.getTitle().length() <=2 ? r.getTitle() + "  " : r.getTitle();
			
			String reviewDay = String.format("%d-%02d-%02d"
								, r.getYear()
								, r.getMonth()
								, r.getDate());
			
			
			System.out.printf("%2d  %s\t%s\t%s   %s\n"
								, i
								, title
								, r.getType()
								, reviewDay
								, r.getReview());
			i++;
			
		}
		System.out.println();
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		//call();
		
		
	} //allReviewRist
	
	
	/**
	 * 전체 목록 보기의 선택지 출력(내 관람기록)
	 */
	public void call() {
		
		System.out.println("----------------------------------------------------");
		System.out.println("  1. 새 글 작성하기");
		System.out.println("  2. 글 삭제하기");
		System.out.println("  3. 캘린더로 돌아가기");
		System.out.print  ("  ⦿ 선택 : ");
		String input = scan.nextLine();
		
		
		ReviewEdit edit = new ReviewEdit(userId);

		
		if(input.equals("1")) {
			
			System.out.println();
			System.out.println("  **새 글 작성하기로 이동합니다**");
			scan.nextLine();
			edit.add();
			
		} else if(input.equals("2")) {
			
			System.out.println();
			System.out.println("  **글 삭제하기로 이동합니다**");
			scan.nextLine();
			edit.delete();	
			
		} else if(input.equals("3")) {
			
			MyReviewCalendar calendar = new MyReviewCalendar(year, month, userId);
			System.out.println();
			System.out.println("  **캘린더 보기로 이동합니다**");
			scan.nextLine();
			
			calendar.monthlyCalendar();
			calendar.calendarMenu();
			//scan.close();
		}
		
		
		
	}
	
	
	

}
