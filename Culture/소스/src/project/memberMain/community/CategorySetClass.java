package project.memberMain.community;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class CategorySetClass {
	public static String moviePath = "data\\영화\\영화.txt";
	public static String movieReview = "data\\영화\\영화 한줄평";
	public static String playPath = "data\\연극\\연극.txt";
	public static String playReview = "data\\연극\\연극 한줄평";
	public static String musicalPath = "data\\뮤지컬\\뮤지컬.txt";
	public static String musicalReview = "data\\뮤지컬\\뮤지컬 한줄평";
	public static String bookPath = "data\\도서\\도서.txt";
	public static String bookReview = "data\\도서\\도서 한줄평";
	private String name;
	private String director;
	private String acter;
	private Calendar releaseDate;
	private double GPA;
	private String Genre;
	public CategorySetClass(String name, String director, String acter, Calendar releaseDate, double gPA,
			String genre) {
		super();
		this.name = name;
		this.director = director;
		this.acter = acter;
		this.releaseDate = releaseDate;
		GPA = gPA;
		Genre = genre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActer() {
		return acter;
	}
	public void setActer(String acter) {
		this.acter = acter;
	}
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	public double getGPA() {
		return GPA;
	}
	public void setGPA(double gPA) {
		GPA = gPA;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	
	@Override
	public String toString() {
		return String.format(
				"MovieSet [name=%s, director=%s, acter=%s, releaseDate=%s, GPA=%s, Genre=%s]", name,
				director, acter, releaseDate, GPA, Genre);
	}
	
	
	
	
	
}














































