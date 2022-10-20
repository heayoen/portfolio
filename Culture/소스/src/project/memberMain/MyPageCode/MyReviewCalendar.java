package project.memberMain.MyPageCode;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import project.login.userLogin;

/**
 * 관람기록 화면을 콘솔에 출력
 * @author 황혜연
 *
 */

public class MyReviewCalendar {
	
	private int year;
	private int month;
	private String userId;
	private Scanner scan;
	private ArrayList<UserReview> reviewArray;
	private Calendar thisMonth = Calendar.getInstance();
	private ReviewList reviewList;
	private File reviewFile;
	
	/**
	 * 
	 * @param year 달력의 연도 
	 * @param month  달력의 월
	 * @param userId 리뷰정보를 가져올 사용자 아이디
	 */
	public MyReviewCalendar(int year, int month, String userId) { // 현재 월 받아옴 4월 -> 4월 
		super();
		this.year = year;
		this.month = month;
		this.userId = userId;
		this.scan = new Scanner(System.in);
		this.reviewArray = new ArrayList<UserReview>();
		this.thisMonth.set(year, month-1, 1); //따라서 월은 -1 해야함...
		this.reviewList = new ReviewList(this.userId);
		this.reviewFile = new File("data\\마이페이지\\" + this.userId + "\\reviews.txt");
	}
	


	/**
	 * 입력한 날짜가 포함된 월의 달력 출력
	 */
	public void monthlyCalendar () {
		 
		new MakeList(userId).makeReviewArray(reviewArray, userId); //영화리뷰목록을 ArrayList로 받아옴
		
		
		ArrayList<Integer> reviewDate = new ArrayList<Integer>();
		
		for(UserReview r : reviewArray) {
			if(r.getMonth() == thisMonth.get(Calendar.MONTH)+1 && r.getYear() == thisMonth.get(Calendar.YEAR)) { //TODO 고친거 공유필요!!
				reviewDate.add(r.getDate());
			}
		}//목록에서 해당 달의 날짜를 가져옴
		
		
		String [][] monthly = new String[6][7];	 
		
		int n = start(thisMonth.get(Calendar.DAY_OF_WEEK));
		
		for(int i = 0; i<6 ; i++) {
			for(int j = 0 ; j<7 ; j++) {
				
				if(n > lastDate(thisMonth.get(Calendar.MONTH) + 1) || n <= 0 ) {
					monthly[i][j] = "";
					
				} else {
					
					if(reviewDate.contains(n)  ) {  
					
						monthly[i][j] = n + " #";
					}else {
						monthly[i][j] = n + "";
					}
				}	
				n++;
			}
		}//for i 
		
		createCalendar(monthly, thisMonth);
		 
	 }//claendarMain
	

	private int lastDate(int month) {
		
		if (month == 1 
			|| month == 3
			|| month == 5
			|| month == 7
			|| month == 8
			|| month == 10
			|| month == 12) {
		
			return 31;
		} else if (month == 2) {
			return 28;
		} else {
			return 30;
		}
		
		
		
	}

	
	private int start(int dayNum) {
		//(일1 월2 화3 수4 목5 금6 토7)
		
		if (dayNum == 1) {
			return 1;
		} else if (dayNum == 2) {
			return -0;
		} else if (dayNum == 3) {
			return -1;
		} else if (dayNum == 4) {
			return -2;
		} else if (dayNum == 5) {
			return -3;
		} else if (dayNum == 6) {
			return -4;
		} else  {
			return -5;
		} 
		
	}

	
	private void createCalendar(String[][] monthly, Calendar thisMonth) {
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf(" 		    %d월 캘린더\n", thisMonth.get(Calendar.MONTH) + 1);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		for(int i = 0; i<6 ; i++) {
			for(int j = 0 ; j<7 ; j++) {
				
				System.out.printf("%s\t ", monthly[i][j]);
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		//select();
		
		
	} //createCalendar

	/**
	 * 리뷰보기 선택지 화면 
	 */
	public void calendarMenu () {
		
		System.out.println(" 1. 날짜별 기록 검색하기");
		System.out.println(" 2. 기록 추가하기");
		System.out.println(" 3. 기록 삭제하기");
		System.out.println(" 4. 전체 목록 보기");
		System.out.println(" 5. 마이페이지로 돌아가기");
		System.out.print  (" ⦿ 선택: ");
		
		ReviewEdit edit = new ReviewEdit(userId);
		
		String input = scan.nextLine();
		
		if(input.equals("1")) {
			System.out.println(" 엔터를 입력하면 날짜 검색 화면으로 이동합니다.");
			scan.nextLine();
			
			if(reviewFile.length() > 0) {
				new FindReview(userId).find();
				
			} else {
				new FindReview(userId).noReviews();
			}
			
			
		
		} else if(input.equals("2")) {
			System.out.println(" 엔터를 누르시면 기록 추가 화면으로 이동합니다.");
			scan.nextLine();
			edit.add();
			
			
		} else if(input.equals("3")) {
			System.out.println(" 엔터를 누르시면 기록 삭제 화면으로 이동합니다.");
			scan.nextLine();
			edit.delete();
			
			
		} else if(input.equals("4")) {
			System.out.println();
			System.out.println(" 엔터를 누르시면 전체목록으로 이동합니다.");
			scan.nextLine();
			reviewList.allReviewlist();
			reviewList.call();
			
			
		} else if(input.equals("5")) {
			System.out.println();
			System.out.println(" 엔터를 누르시면 이전 화면으로 이동합니다.");
			scan.nextLine(); 
			new userLogin().LoginCategory(userId);
			
		} else {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" **정확한 번호를 입력하세요**");
			scan.nextLine();
			this.calendarMenu ();
			
		}
	}

	

}
