package project.memberMain.community;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import project.login.nonUserLogin;
import project.login.userLogin;
import project.memberMain.MemberMain;

public class Select {
	
	private String userPath = "";
	private String reviewPath = "";
	private String slangPath = "data\\비속어\\비속어.txt";
	private Scanner scan = new Scanner(System.in);
	
	private ArrayList<String> slangList = new ArrayList<String>();
	
	private ArrayList<CategorySetClass> movList = Community.fileRead(CategorySetClass.moviePath);
	private ArrayList<CategorySetClass> plaList = Community.fileRead(CategorySetClass.playPath);
	private ArrayList<CategorySetClass> musList = Community.fileRead(CategorySetClass.musicalPath);
	private ArrayList<CategorySetClass> booList = Community.fileRead(CategorySetClass.bookPath);
	private ArrayList<CategorySetClass> nowCategoryList;
	
	private String genreSelectInput;
	private String communityInput;
	private Calendar now;
	
	
	public void genreSelect(String category, String communityInput, String input) {
		String methodName = "genreSelect";
		this.genreSelectInput = input;
		this.communityInput = communityInput;
		
		
		if (category.equals("영화")) {
			categoryCheck(category, communityInput, input, methodName);
		} else if (category.equals("연극")) {
			categoryCheck(category, communityInput, input, methodName);
		} else if (category.equals("뮤지컬")) {
			categoryCheck(category, communityInput, input, methodName);
		} else if (category.equals("도서")) {
			categoryCheck(category, communityInput, input, methodName);
		}
		
		
	}



	private void categoryCheck(String category, String communityInput, String input, String methodName) {
		if (userLogin.id.length() >= 4) {
			switch (communityInput) {
				case "1" :
					// 장르별 보기
					CommunityCategorieOutput.CommunityGenreMenu();
					genreView(scan.nextLine(), methodName, input, category);
				case "2" :
					// 현재 상영작 보기
					now = Calendar.getInstance();
					ArrayList<String> nowCategoryList = new ArrayList<String>();
					nowCategoryListCheck(now, nowCategoryList, category);
					CommunityCategorieOutput.nowMovieMenu(nowCategoryList);
					
					workSelect(scan.nextLine(), nowCategoryList, methodName, category);
					
				case "3" :
					// 비슷한 취향 작품 보기
					ArrayList<String> userTasteList = new ArrayList<String>();
					userTasteList = UserTasteList(userTasteList);
					CommunityCategorieOutput.CommunityTasteMenu(userTasteList);
					tasteSelect(scan.nextLine(), userTasteList, category);
					
				case "4" :
					// 작품 검색 보기
					CommunityCategorieOutput.tasteSeach();
					
					String tasteSeachInput = scan.nextLine();
					
					seach(tasteSeachInput, category);
					
					break;
				case "5" :
					// [커뮤니티] 돌아가기
					communityMain.commuMain();
				default : 
					System.out.println("잘못된 입력입니다.");
					categoryCheck(category, communityInput, input, methodName);
			} 
		} else {
			switch (communityInput) {
				case "1" :
					// 장르별 보기
					CommunityCategorieOutput.CommunityGenreMenu();
					genreView(scan.nextLine(), methodName, input, category);
				case "2" :
					// 현재 상영작 보기
					now = Calendar.getInstance();
					ArrayList<String> nowCategoryList = new ArrayList<String>();
					nowCategoryListCheck(now, nowCategoryList, category);
					CommunityCategorieOutput.nowMovieMenu(nowCategoryList);
					
					workSelect(scan.nextLine(), nowCategoryList, methodName, category);
					
				case "3" :
					// 작품 검색 보기
					CommunityCategorieOutput.tasteSeach();
					
					String tasteSeachInput = scan.nextLine();
					
					seach(tasteSeachInput, category);
					
					break;
				case "4" :
					// [커뮤니티] 돌아가기
					nonUserLogin.nonUserMain();
				default : 
					System.out.println("잘못된 입력입니다.");
					categoryCheck(category, communityInput, input, methodName);
			}
		}
	}

	

	private void seach(String input, String category) {
		String nameInput = null;
		switch(input) {
			case "1":
				System.out.print("작품명 검색 : ");
				nameInput = scan.nextLine();
				workSeach(nameInput, category, input);
				
			case "2":
				System.out.print("연출자 검색 : ");
				nameInput = scan.nextLine();
				workSeach(nameInput, category, input);
				
			case "3":
				System.out.print("배우 검색 : ");
				nameInput = scan.nextLine();
				workSeach(nameInput, category, input);
				
			case "4":
				System.out.println("\n");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("             [카테고리]로 돌아갑니다.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				backToTheCategory(category);
			default : 
				System.out.println("잘못된 입력입니다.");
				seach(input, category);
		}
		
	}



	private void backToTheCategory(String category) {
		if (category.equals("영화")) {
			new Community().movie(category);
		} else if (category.equals("연극")) {
			new Community().play(category);
		} else if (category.equals("뮤지컬")) {
			new Community().musical(category);
		} else if (category.equals("도서")) {
			new Community().book(category);
		}
	}



	private void workSeach(String nameInput, String category, String input) {
		
		String methodName = "workSeach";
		
		try {
			ArrayList<CategorySetClass> categorySetList = new ArrayList<CategorySetClass>();
			
			BufferedReader reader = null;
			
			if (category.equals("영화")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.moviePath));
			} else if (category.equals("연극")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.playPath));
			} else if (category.equals("뮤지컬")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.musicalPath));
			} else if (category.equals("도서")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.bookPath));
			}
			
			
			
			String line = null;
			int lineNum = 0;
			
			while ((line = reader.readLine()) != null) {
				String[] movieset = line.split("■");
				String[] director = movieset[1].split(",");
				String[] actor = movieset[2].split(",");
				
				String actorCheck = "";
				String directorCheck = "";
				// 감독 존재여부 확인
				for (int i = 0; i < director.length; i++) {
					if (director[i].lastIndexOf(nameInput) > -1) {
						directorCheck = nameInput;
					}
				}
				
				// 배우 존재여부 확인
				for (int i = 0; i < actor.length; i++) {
					if (actor[i].lastIndexOf(nameInput) > -1) {
						actorCheck = nameInput;
					}
				}
				
				if (movieset[0].equals(nameInput) || directorCheck.equals(nameInput) || actorCheck.equals(nameInput)) {							// 작품명 찾기
					String[] dateset = movieset[3].split("/");
					
					Calendar movieListTime = Calendar.getInstance();
					movieListTime.set(Integer.parseInt(dateset[0])
							, (Integer.parseInt(dateset[1]) -1)
							, Integer.parseInt(dateset[2]));
					
					
					CategorySetClass categorysetclass = new CategorySetClass(movieset[0]
											, movieset[1]
											, movieset[2]
											, movieListTime
											, Double.parseDouble(movieset[4])
											, movieset[5]);
					
					categorySetList.add(categorysetclass);
					
					lineNum++;
				
				
				}
				
			}
			reader.close();
			
			CommunityCategorieOutput.workInformation(categorySetList.get(0), methodName);
			
			review(categorySetList.get(0).getName(), scan.nextLine(), null, methodName, category);
			
		} catch (Exception e) {
			System.out.println("Select.workSeach");
			e.printStackTrace();
		}
	}

	
	private void tasteSelect(String input, ArrayList<String> userTasteList, String category) {
		String methodName = "tasteSelect";
		
		if (category.equals("영화")) {
			nowCategoryList = movList;
		} else if (category.equals("연극")) {
			nowCategoryList = plaList;
		} else if (category.equals("뮤지컬")) {
			nowCategoryList = musList;
		} else if (category.equals("도서")) {
			nowCategoryList = booList;
		}
		
		switch (input) {
			case "1" :			// 장르 선택
				CommunityCategorieOutput.tasteSelectprint();
				String tasteInput = scan.nextLine();
				
				for (int i = 0; i < userTasteList.size(); i++) {
					if (tasteInput.equals(userTasteList.get(i))) {
						userTasteSelect(userTasteList.get(i), nowCategoryList, userTasteList, methodName);
						
						workSelect(scan.nextLine(), userTasteList, methodName, category);
					}
				}
				
			case "2" :			// 새로운 장르 추천
				HashSet<String> newGenre = new HashSet<String>();
				newGenre = newGenreView(userTasteList, newGenre);
				CommunityCategorieOutput.CommunitynewGenreMenu(newGenre);
				
				ArrayList<String> newGenreList = new ArrayList<String>();
				for (String n : newGenre) {
					newGenreList.add(n);
				}
				
				String genreInput = scan.nextLine();
				
				if (genreInput.equals("1")) {
					tasteSelect(genreInput, newGenreList, category);
				} else if (genreInput.equals("2")) {
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("             [관심 장르]로 돌아갑니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					genreSelect(category, communityInput, input);
				}
				break;
			case "3" :			// [카테고리] 돌아가기
				backToTheCategory(category);
			default : 
				System.out.println("잘못된 입력입니다.");
				tasteSelect(input, userTasteList, category);
		}
		
	}
	
	
	private void nowCategoryListCheck(Calendar now, ArrayList<String> nowCategoryList, String category) {
		
		
		try {
			
			ArrayList<CategorySetClass> categorySetList = new ArrayList<CategorySetClass>();
			
			BufferedReader reader = null;
			
			if (category.equals("영화")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.moviePath));
			} else if (category.equals("연극")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.playPath));
			} else if (category.equals("뮤지컬")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.musicalPath));
			} else if (category.equals("도서")) {
				reader = new BufferedReader(new FileReader(CategorySetClass.bookPath));
			}
			
			String line = null;
			int lineNum = 0;
			
			while ((line = reader.readLine()) != null) {
				String[] movieset = line.split("■");
				String[] dateset = movieset[3].split("/");

				Calendar movieListTime = Calendar.getInstance();
				movieListTime.set(Integer.parseInt(dateset[0])
						, (Integer.parseInt(dateset[1]) -1)
						, Integer.parseInt(dateset[2]));
				
				
				CategorySetClass categorySetClass = new CategorySetClass(movieset[0]
													, movieset[1]
													, movieset[2]
													, movieListTime
													, Double.parseDouble(movieset[4])
													, movieset[5]);
				
				categorySetList.add(categorySetClass);
				
				lineNum++;
				
			}

			reader.close();
			
			
			for (int i = 0; i < lineNum; i++) {
				
				// 개봉일 차 구하기
				int betweenYear = categorySetList.get(i).getReleaseDate().get(Calendar.YEAR) - now.get(Calendar.YEAR);
				int betweenMonth = (categorySetList.get(i).getReleaseDate().get(Calendar.MONTH) + 2) - (now.get(Calendar.MONTH));
				if (betweenYear == 0 && betweenMonth > 0) {
					
					nowCategoryList.add(categorySetList.get(i).getName());
					
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Select.nowMovieList");
			e.printStackTrace();
		}
		
	}
	
	
	private void genreView(String input, String methodName, String genreSelectInput, String category) {
		String fatherMethodName = methodName;
		methodName = "genreView";
		ArrayList<String> userTasteList = new ArrayList<String>();
		
		
		ArrayList<CategorySetClass> nowCategoryList = categorySelectList(category);
		
		switch(input) {
			case "1" :
				userTasteList.add("아니야");
				userTasteList.add("액션");
				userTasteSelect("액션", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "2" :
				userTasteList.add("아니야");
				userTasteList.add("드라마");
				userTasteSelect("드라마", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "3" :
				userTasteList.add("아니야");
				userTasteList.add("멜로");
				userTasteSelect("멜로", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "4" :
				userTasteList.add("아니야");
				userTasteList.add("스릴러");
				userTasteSelect("스릴러", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "5" :
				userTasteList.add("아니야");
				userTasteList.add("코믹");
				userTasteSelect("코믹", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "6" :
				userTasteList.add("아니야");
				userTasteList.add("다큐멘터리");
				userTasteSelect("다큐멘터리", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "7" :
				userTasteList.add("아니야");
				userTasteList.add("애니메이션");
				userTasteSelect("애니메이션", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "8" :
				userTasteList.add("아니야");
				userTasteList.add("공포");
				userTasteSelect("공포", nowCategoryList, userTasteList, methodName);
				workSelect(scan.nextLine(), userTasteList, methodName, category);
			case "9" :
				System.out.println("\n");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("           [카테고리 선택]으로 돌아갑니다.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				backToTheCategory(category);
			default : 
				System.out.println("잘못된 입력입니다.");
				genreView(input, methodName, genreSelectInput, category);
		}
		
		
	}



	private ArrayList<CategorySetClass> categorySelectList(String category) {
		if (category.equals("영화")) {
			return movList;
		} else if (category.equals("연극")) {
			return plaList;
		} else if (category.equals("뮤지컬")) {
			return musList;
		} else if (category.equals("도서")) {
			return booList;
		}
		return null;
	}

	
	private HashSet<String> newGenreView(ArrayList<String> userTasteList, HashSet<String> newGenre) {
		String[] genre = {"액션", "드라마", "멜로", "스릴러", "코믹", "다큐멘터리", "애니메이션", "공포"};
		
		
		for (int i = 0; i < userTasteList.size(); i++) {
			
			for (int j = 0; j < genre.length; j++) {
				
				if (!userTasteList.get(i).equals(genre[j])) {
					if (newGenre.size() > 5) {
						break;
					} else {
						newGenre.add(genre[j]);
					}
				}
			}
			
		}
		return newGenre;
	}

	
	
	
	private void workSelect(String input, ArrayList<String> userTasteList, String methodName, String category) {

		if (methodName.equals("tasteSelect")) {
			switch (input) {
				case "1" :			// 작품 선택
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("                작품을 입력해주세요");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.print("입력 : ");
					workSearch(scan.nextLine(), userTasteList, methodName, category);
				case "2" :			// 돌아가기
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("           [관심 장르]로 돌아갑니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					genreSelect(category, communityInput, input);
				default : 
					System.out.println("잘못된 입력입니다.");
					workSelect("2", userTasteList, methodName, category);
					
			}
			
		} else if (methodName.equals("genreView")){
			switch (input) {
				case "1" :			// 작품 선택
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("                 작품을 입력해주세요");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.print("입력 : ");
					workSearch(scan.nextLine(), userTasteList, methodName, category);
				case "2" :			// 돌아가기
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("           [장르별 보기]으로 돌아갑니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					genreSelect(category, "1", this.genreSelectInput);
				default : 
					System.out.println("잘못된 입력입니다.");
					workSelect("2", userTasteList, methodName, category);
			}
		} else if (methodName.equals("genreSelect")) {
			switch (input) {
				case "1" :			// 작품 선택
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("                 작품을 입력해주세요");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.print("입력 : ");
					workSearch(scan.nextLine(), userTasteList, methodName, category);
				case "2" :			// 돌아가기
					System.out.println("\n");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("           [카테고리]로 돌아갑니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					backToTheCategory(category);
				default : 
					System.out.println("잘못된 입력입니다.");
					workSelect("2", userTasteList, methodName, category);
			}
		}

	}
	

	private void workSearch(String name, ArrayList<String> userTasteList, String methodName, String category) {
		if (category.equals("영화")) {
			nowCategoryList = movList;
		} else if (category.equals("연극")) {
			nowCategoryList = plaList;
		} else if (category.equals("뮤지컬")) {
			nowCategoryList = musList;
		} else if (category.equals("도서")) {
			nowCategoryList = booList;
		}
		
		for (CategorySetClass n : nowCategoryList) {
			if (name.equals(n.getName())) {
				CommunityCategorieOutput.workInformation(n, methodName);
				
				review(name, scan.nextLine(), userTasteList, methodName, category);
			} 
		}
		
	}


	private void review(String name, String input, ArrayList<String> userTasteList, String methodName, String category) {

		switch(input) {
			case "1":
				// 한줄평 보기
				viewReview(name, userTasteList, input, methodName, category);
				
			case "2":
				// 한줄평 작성(30자 이내)
				viewReview(name, userTasteList, input, methodName, category);
				
				
			case "3":
				System.out.println("\n");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				
				if (methodName.equals("tasteSelect")) {
					System.out.println("         [비슷한 취향 작품 추천]으로 돌아갑니다.");
				} else if (methodName.equals("genreView")) {
					System.out.println("         [카테고리 선택]으로 돌아갑니다.");
				} else if (methodName.equals("genreSelect")) {
					System.out.println("         [현재 상영작]으로 돌아갑니다.");
				} else if (methodName.equals("workSeach")) {
					System.out.println("         [작품 검색]으로 돌아갑니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					genreSelect(category, communityInput, input);
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				tasteSelect(input, userTasteList, category);
			default : 
				System.out.println("잘못된 입력입니다.");
				review(name, "3", userTasteList, methodName, category);
		}
		
	}

	
	
	
	
	private void viewReview(String name, ArrayList<String> userTasteList, String input, String methodName, String category) {
		System.out.println("\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		File dir = null;
		if (category.equals("영화")) {
			dir = new File(CategorySetClass.movieReview);
		} else if (category.equals("연극")) {
			dir = new File(CategorySetClass.playReview);
		} else if (category.equals("뮤지컬")) {
			dir = new File(CategorySetClass.playReview);
		} else if (category.equals("도서")) {
			dir = new File(CategorySetClass.bookReview);
		}
		
		
		
		File[] list = dir.listFiles();
		boolean fileCheck = false;
		
		for (File d : list) {
			if (d.getName().equals(name + ".txt")) {
				fileCheck = true;
			}
		}
		
		if (fileCheck == false) {
			this.reviewPath = dir + "\\" + (name + ".txt");
			File reDir = new File(this.reviewPath);
			
			try {
				reDir.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (File d : list) {
			if (d.getName().equals(name + ".txt")) {
				this.reviewPath = dir + "\\" + d.getName();
				break;
			}
		}
		
		slangInput();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.reviewPath));
			String line = null;
			
			System.out.printf("                  %s\n", name);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			while ((line = reader.readLine()) != null) {
				
				System.out.println(line);
				
			}
			reader.close();
			
			
			if (input.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("아무 키나 입력 : ");
				scan.nextLine();
				workSearch(name, userTasteList, methodName, category);
			} else {
				
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(this.reviewPath, true));
					
					System.out.println("                       (                  30자 가이드                )");
					System.out.print("한줄평 입력(최대 30자 이내) : ");
					String review = scan.nextLine();
					
					
					for (String n : slangList) {
						
						if (review.lastIndexOf(n) > -1) {
							
							int starLength = n.length();
							String star = "";
							
							for (int i = 0; i < starLength; i++) {
								star = (star + "*");
							}
							
							review = review.replace(n, star);
							
						}
						
					}
					
					
					if (review.length() <= 30 && review.length() > 0) {
						writer.newLine();
						writer.write(userLogin.id + " : " + review);
						writer.close();
						viewReview(name, userTasteList, "1", methodName, category);
					} else {
						System.out.println("최대 글자수를 초과했습니다.");
						viewReview(name, userTasteList, input, methodName, category);
					}
					writer.close();
					
				} catch (Exception e) {
					System.out.println("Select.viewReview");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println("Select.review");
			e.printStackTrace();
		}
	}



	private void slangInput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.slangPath));
			String line = null;

			while ((line = reader.readLine()) != null) {
				slangList.add(line);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("Select.viewReview");
			e.printStackTrace();
		}
	}
	
	
	private void userTasteSelect(String string, ArrayList<CategorySetClass> list, ArrayList<String> userTasteList, String methodName) {
		ArrayList<String> userTasetSelectList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).getGenre().lastIndexOf(string) > -1) {
				userTasetSelectList.add(list.get(i).getName());
			}
		}
		CommunityCategorieOutput.CommunityUserTasteMenu(string, userTasetSelectList, methodName);
		
	}

	private ArrayList<String> UserTasteList(ArrayList<String> userTasteList) {
		
		File dir = new File("data\\마이페이지");
		File[] list = dir.listFiles();
		
		for (File d : list) {
			if (d.isDirectory()) {
				if (d.getName().equals(userLogin.id)) {
					this.userPath = dir + "\\" + d.getName() + "\\info.txt";
				}
			}
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.userPath));
			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] cup = line.split("■");
				cup = cup[7].split(",");
				
				for (int i = 0; i < cup.length; i++) {
					userTasteList.add(cup[i]);
				}
				
			}

			reader.close();
			return userTasteList;
			
		} catch (Exception e) {
			System.out.println("Select.TasteList");
			e.printStackTrace();
		}
		return userTasteList;
		
	}
	
	
}


