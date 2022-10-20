package project.memberMain.community;

import java.util.Calendar;

public class CalendarSet {

	private Calendar year;
	private Calendar month;
	private Calendar date;
	public CalendarSet(Calendar year, Calendar month, Calendar date) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
	}
	public Calendar getYear() {
		return year;
	}
	public void setYear(Calendar year) {
		this.year = year;
	}
	public Calendar getMonth() {
		return month;
	}
	public void setMonth(Calendar month) {
		this.month = month;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return String.format("CalendarSet [year=%s, month=%s, date=%s]", year, month, date);
	}
	
	
	
	
}
