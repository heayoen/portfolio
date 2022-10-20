package project.memberMain.MyPageCode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 새로운 리뷰를 작성하거나 기존의 리뷰를 삭제
 * @author 황혜연
 *
 */

public class ReviewEdit {
	
	private String userId;
	private Scanner scan;
	private Calendar c = Calendar.getInstance();
	private int year;
	private int month;
		
	/**
	 * 
	 * @param userId 사용자 아이디
	 */
	public ReviewEdit(String userId) {
		this.userId = userId;
		this.scan = new Scanner(System.in);
		this.year = c.get(Calendar.YEAR);
		this.month = c.get(Calendar.MONTH)+1;
		
	}

	/**
	 * 새로운 리뷰 작성
	 */
	public void add() {
		
		String temp[] = new String[4]; 
				
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("                  리뷰 작성 화면");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		
		System.out.print  (" ⦿  제목 입력 : ");	
		temp[2] = scan.nextLine();
		
		System.out.print  (" ⦿  분류 입력 : ");
		temp[1] = scan.nextLine();

		System.out.println("  **감상일자는 yyyy-mm-dd형식으로 입력하세요");
		System.out.print  (" ⦿  감상일자 입력 : ");
		temp[0] = scan.nextLine();
		
		System.out.print  (" ⦿  한줄평 입력 : ");
		temp[3] = scan.nextLine();
		
		
		System.out.println();
		System.out.println("  리뷰 작성이 완료되었습니다. Y를 누르시면 리뷰가 저장됩니다.");
		System.out.println("  리뷰 작성을 원하지 않으실 경우 X를 입력해주세요");
		System.out.print  ("  ⦿ 선택 : ");
		
		String input = scan.nextLine();
		
		if(input.toUpperCase().equals("Y")) {
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("  ⦿ 리뷰작성이 완료되었습니다. 캘린더 보기로 돌아갑니다." );
			
			save(temp);
			
			scan.nextLine();
			MyReviewCalendar calendar =new MyReviewCalendar(year, month, userId);
			
			calendar.monthlyCalendar();
			calendar.calendarMenu();
		
		} else if(input.toUpperCase().equals("X")) {
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("  ⦿ 리뷰작성을 취소하였습니다. 캘린더 보기로 돌아갑니다." );
			
			scan.nextLine();
			MyReviewCalendar calendar = new MyReviewCalendar(year, month, userId);
			
			calendar.monthlyCalendar();
			calendar.calendarMenu();
			
		}
		
		
	}


	private void save(String[] temp) {
		String path 
			= String.format("data\\마이페이지\\%s\\reviews.txt",userId);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			File file = new File(path);

			StringBuilder newReview  
				= new StringBuilder(String.format("%s■%s■%s■%s", temp[0], temp[1], temp[2],temp[3]));
			
			String line = null;
			while((line = reader.readLine()) != null) {		
				newReview.append("\n" + line);
			}
			reader.close();			
			
			file.delete();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(newReview.toString());
			writer.close();		
			
		} catch (Exception e) {
			System.out.println("ReviewEdit.add");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 기존의 리뷰 삭제
	 */
	public void delete() {
		
		ReviewList reviewList = new ReviewList(userId);
		reviewList.allReviewlist();
		
		
		try {
			String path 
			= String.format("data\\마이페이지\\%s\\reviews.txt",userId);
		
			BufferedReader reader = new BufferedReader(new FileReader(path));
			File file = new File(path);	
		
			
			System.out.println();
			System.out.print  ("  ⦿ 삭제할 리뷰의 번호를 입력하세요 : ");
			
			int num = Integer.parseInt(scan.nextLine());
			int index = 1;
			String line = null;
			StringBuilder review = new StringBuilder("");
			
			while((line = reader.readLine()) != null) {
				
				if(index != num) {
					review.append(line + "\n");
					index ++;
					
				} else {
					review.append("");
					index++;
				
				}
				
			}
			reader.close();
			
			file.delete();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(review.toString());
			writer.close();		
			
		} catch (Exception e) {
			System.out.println("ReviewEdit.delete");
			e.printStackTrace();
		}
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("  ⦿ 리뷰삭제가 완료되었습니다. 캘린더 보기로 돌아갑니다." );
		scan.nextLine();
		MyReviewCalendar calendar = new MyReviewCalendar(year, month, userId);
		
		calendar.monthlyCalendar();
		calendar.calendarMenu();
		
	}//delete
	
	
	
}//class
