package project.memberMain.MyPageCode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 날짜를 입력하면 해당 월의 달력을 출력한 뒤 해당하는 날의 리뷰를 출력하는 클래스
 * @author 황혜연
 *
 */
public class FindReview {
	
	private String userId;
	private Scanner scan;
	private MakeList makeList;

	/**
	 * 
	 * @param userId 리뷰 기록을 찾을 대상의 아이디 
	 */
	public FindReview(String userId) {
		super();
		this.userId = userId;
		this.scan =  new Scanner(System.in);
		this.makeList = new MakeList(userId);
	}
	
	/**
	 * 형식에 맞추어 날짜를 입력받고, 해당 날짜의 기록을 반환.
	 */
	public void find() { 
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("*날짜 형식은 반드시 yyyy-mm-dd 로 입력하셔야 합니다*" );
		
		
		//유효성 검사
		String input;
		
		while (true) {// 생년월일
			System.out.print  ("  ⦿ 검색하실 날짜를 입력하세요 : ");
			input = scan.nextLine();
			if (check(input)) {
				continue;
			}
			break;
		}
		
		
		
		String[] ymd = input.split("-");
		
		System.out.println();
		System.out.println();
		MyReviewCalendar calendar = new MyReviewCalendar(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), userId);
		calendar.monthlyCalendar(); // 검색달 캘린더 소환
		
		int index = 0;
		
		ArrayList<UserReview> review = new ArrayList<UserReview>();	
		
		makeList.makeReviewArray(review, userId);
		
		for(int i = 0; i < review.size() ; i++) {
			
			if(review.get(i).getYear() == Integer.parseInt(ymd[0])
					&& review.get(i).getMonth() == Integer.parseInt(ymd[1])
					&& review.get(i).getDate() == Integer.parseInt(ymd[2])) {
				
				index++;
				System.out.printf(" ⦿ %s) %s: %s\n", review.get(i).getType(), review.get(i).getTitle(), review.get(i).getReview());
		
			} 
		}// for
		
		if(index == 0) {
			System.out.println(" ** 해당 날짜에는 기록이 없습니다 **");
		}
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	
		calendar.calendarMenu(); //캘린더메뉴 소환
		
	}//find
	
	
	
	/**
	 * 작성한 리뷰가 하나도 없을 경우 메세지 출력
	 */
	public void noReviews(){
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **입력된 기록이 없습니다. 기록을 작성해주세요**" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
		Calendar c = Calendar.getInstance();
		
		new MyReviewCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH) +1, userId).calendarMenu(); //캘린더메뉴 소환//캘린더메뉴 소환
			
		
	}
	
	
	private boolean check(String input) {
		
		if (!(input.charAt(4) == '-' && input.charAt(7) == '-')) {
			System.out.println(" **날짜는 yyyy-mm-dd형식으로 입력해주세요 **");
			System.out.println();
			return true;
		}
		return false;
		
		
	}


	

	

}
