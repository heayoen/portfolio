package project.memberMain.community;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;
import project.login.userLogin;

public class Community {

	private static Scanner scan;
	private Select select = new Select();
	
	
	
	public Community() {
		this.scan = new Scanner(System.in);
	}
	
	// 영화 카테고리 선택
	public void movie(String input) {
		
		String category = "영화";
		String communityInput = categorieOutput(category);
		select.genreSelect(category, communityInput, input);
		
	}

	public void play(String input) {
		String category = "연극";
		String communityInput = categorieOutput(category);
		select.genreSelect(category, communityInput, input);
		
	}
	
	public void musical(String input) {
		String category = "뮤지컬";
		String communityInput = categorieOutput(category);
		select.genreSelect(category, communityInput, input);
	}
	
	public void book(String input) {
		String category = "도서";
		String communityInput = categorieOutput(category);
		select.genreSelect(category, communityInput, input);
	}
	
	
	public static String categorieOutput(String category) {
		if (userLogin.id.length() >= 4) {
			if (category.equals("영화")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.moviePath);
				CommunityCategorieOutput.CommunityCategorieMenu(category, list);
			} else if (category.equals("연극")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.playPath);
				CommunityCategorieOutput.CommunityCategorieMenu(category, list);
			} else if (category.equals("뮤지컬")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.musicalPath);
				CommunityCategorieOutput.CommunityCategorieMenu(category, list);
			} else if (category.equals("도서")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.bookPath);
				CommunityCategorieOutput.CommunityCategorieMenu(category, list);
			}
			
			System.out.print("입력 : ");
			return scan.nextLine();
		} else {
			if (category.equals("영화")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.moviePath);
				CommunityCategorieOutput.nonCommunityCategorieMenu(category, list);
			} else if (category.equals("연극")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.playPath);
				CommunityCategorieOutput.nonCommunityCategorieMenu(category, list);
			} else if (category.equals("뮤지컬")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.musicalPath);
				CommunityCategorieOutput.nonCommunityCategorieMenu(category, list);
			} else if (category.equals("도서")) {
				ArrayList<CategorySetClass> list = fileRead(CategorySetClass.bookPath);
				CommunityCategorieOutput.nonCommunityCategorieMenu(category, list);
			}
			System.out.print("입력 : ");
			return scan.nextLine();
		}
	}
	
	
	public static ArrayList<CategorySetClass> fileRead(String path) {
		
		ArrayList<CategorySetClass> list = new ArrayList<CategorySetClass>();
		Calendar movieListTime = Calendar.getInstance();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (!line.equals("")) {
					String[] splist = (line.split("■"));
					String[] dateset = splist[3].split("/");

					movieListTime.set(Integer.parseInt(dateset[0])
							, (Integer.parseInt(dateset[1]) -1)
							, Integer.parseInt(dateset[2]));
					CategorySetClass ms = new CategorySetClass(splist[0]
							, splist[1]
							, splist[2]
							, movieListTime
							, Double.parseDouble(splist[4])
							, splist[5]);
					list.add(ms);
				}
			}
			
			
			list.sort(new Comparator<CategorySetClass>() {
				@Override
				public int compare(CategorySetClass o1, CategorySetClass o2) {
					return (int) (o2.getGPA() - o1.getGPA());
				}
			});
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Community.movie");
			e.printStackTrace();
		}
		return null;
	}

}





















