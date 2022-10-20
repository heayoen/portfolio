package project.memberMain.MyPageCode;

/**
 * 대상의 review.txt파일의 리뷰들을 클래스로 받아옴
 * @author 황혜연
 *
 */

public class UserReview {
	private int year;
	private int month;
	private int date;
	private String type;
	private String title;
	private String review;
	
	/**
	 * 
	 * @param year   관람년도
	 * @param month  관람 월
	 * @param date   관람 일 
	 * @param type   문화생활의 종류 
	 * @param title  관람 작품의 제목
	 * @param review 한줄평
	 */
	public UserReview(String year, String month, String date, String type, String title, String review) {
		
		this.year = Integer.parseInt(year);
		this.month = Integer.parseInt(month);
		this.date = Integer.parseInt(date);
		this.type = type;
		this.title = title;
		this.review = review;
	}
	
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	
	
	
}
