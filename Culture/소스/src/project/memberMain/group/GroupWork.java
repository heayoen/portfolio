package project.memberMain.group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import project.PrintList;
import project.login.userLogin;
import project.memberMain.memOutput;

public class GroupWork {

	private Scanner scan;
	private int num;
	private int input;
	private String com = "";

	public GroupWork() {
		this.scan = new Scanner(System.in);
	}

	public void groupSelectMenu() {

		memOutput.line(1);
		System.out.println("1. 게시물 보기");
		System.out.println("2. 새 게시물 작성");
		System.out.println("3. 게시물 수정/삭제");
		System.out.println("4. [가입된 모임 리스트]돌아가기");
		memOutput.line(1);

		System.out.print("입력: ");
		String num = scan.next();

		if (num.equals("1")) {
			readPost();
		} else if (num.equals("2")) {
			createPost();
		} else if (num.equals("3")) {
			System.out.print("");

			deleteOrAlterPost();
		} else {

			GroupConnect.groupConnect(); // GroupMain() 메소드

		}

	}


	public void groupExitConfirm() {

		File dir = new File("data\\모임");
		File[] files = dir.listFiles();

		System.out.print("모임 선택: ");
		int p = scan.nextInt();
		System.out.print("탈퇴하시겠습니까?(y/n): ");
		String confirm = scan.next();

		if (confirm.equals("y")) {
			String group = files[p - 1].getName();

			String path = String.format("data\\마이페이지\\%s\\info.txt",userLogin.id);  // id
//			String path = "data\\마이페이지\\user01\\info.txt";
																										// 받기

			File userData = new File(path);

			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String data = reader.readLine();
				reader.close();

				String delete = String.format("■%s", group);
				data = data.replace(delete, "");

				path = userData.getAbsolutePath();


				File a = new File(path);

				BufferedWriter writer = new BufferedWriter(new FileWriter(a));

				writer.write(data);

				writer.close();


				GroupExit.groupExit();

			} catch (Exception e) {
				System.out.println("GroupWork.groupExitConfirm");
				e.printStackTrace();
			}


		} else { // 'n' 입력시
			GroupMain.groupMain();
		}


	}


	public void groupJoinConfirm() {

		File dir = new File("data\\모임");
		File[] files = dir.listFiles();

		System.out.print("모임 선택: ");
		int p = scan.nextInt();
		System.out.print("가입하시겠습니까?(y/n): ");
		String confirm = scan.next();

		if (confirm.equals("y")) {
			String group = files[p - 1].getName();

			String path = String.format("data\\마이페이지\\%s\\info.txt", userLogin.id);  // id
//			String path = "data\\마이페이지\\user01\\info.txt";

			File userData = new File(path);


			try {

				BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

				writer.write("■");
				writer.write(group);

				writer.close();

			} catch (Exception e) {
				System.out.println("GroupWork.groupJoinConfirm");
				e.printStackTrace();
			}

			GroupJoin.groupJoin();
		} else {
			GroupJoin.groupJoin();
		}



	}

	public void groupSelect() {

		System.out.print("모임 선택(번호): ");

		this.num = scan.nextInt();

		PrintList.postList(this.num); // 게시물 목록 출력

		groupSelectMenu(); // 게시물 선택지

	}

	private void alterPost(int c) {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			if (c > 0 && c < groups.length + 1) {
				BufferedReader reader = new BufferedReader(new FileReader(groups[c - 1]));
				if (reader.readLine().contains(userLogin.id)) { // id 받기
					reader.close();

					String line = null;
					int count = 0;
					int i = 0;
					String point = "-------------------------------------------------";

					int[] checkPoint = new int[2];

					reader = new BufferedReader(new FileReader(groups[c - 1]));

					while ((line = reader.readLine()) != null) {
						if (line.equals(point)) {
							checkPoint[i] = count;
							i++;
						}

						System.out.println("[" + count + "] " + line);
						count++;
					}
					reader.close();

					memOutput.line(1); // 게시물 수정
					System.out.print("수정할 줄 번호를 입력해 주세요:");
					int alter = scan.nextInt();
					memOutput.line(1);

					System.out.print("수정할 내용 입력:");
					String alterContents = scan.nextLine();
						   alterContents = scan.nextLine();

					if (alter <= checkPoint[0] || alter >= checkPoint[1]) { // 글의 내용만 수정 가능
						System.out.println("글의 댓글,제목 부분은 수정 할 수 없습니다.");
						memOutput.line(1);
						memOutput.pause();
						memOutput.line(1);
						PrintList.postList(this.num);
						memOutput.line(1);
						groupSelectMenu();
					}

					System.out.println(groups[c - 1].getName());

					String path1 = String.format(
							"data\\게시물 수정\\%s",
							groups[c - 1].getName());

					File alterFile = new File(path1);
					alterFile.createNewFile();

					BufferedWriter writer = new BufferedWriter(new FileWriter(alterFile));
					reader = new BufferedReader(new FileReader(groups[c - 1]));

					line = null;
					count = 0;

					while ((line = reader.readLine()) != null) {

						if (count == alter) {
							writer.write(alterContents);
							writer.newLine();
							count++;
							continue;
						}

						writer.write(line);
						writer.newLine();
						count++;
					}
					reader.close();
					writer.close();

					String path = groups[c - 1].getAbsolutePath();
					groups[c - 1].delete();

					alterFile.renameTo(new File(path));

					System.out.println("게시물 수정을 완료했습니다.");

					memOutput.line(1);
					PrintList.postList(this.num);
					memOutput.line(1);
					groupSelectMenu();



				} else {
					reader.close();
					memOutput.line(1);
					System.out.println("작성자만 글을 수정 할 수 있습니다.");
					memOutput.line(1);
					memOutput.pause();
					memOutput.line(1);
					PrintList.postList(this.num);
					memOutput.line(1);
					groupSelectMenu();
				}



			}

		} catch (Exception e) {
			System.out.println("GroupWork.alterPost");
			e.printStackTrace();
		}

	}
	private void adminAlterPost(int c) {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			if (c > 0 && c < groups.length + 1) {
				BufferedReader reader = new BufferedReader(new FileReader(groups[c - 1]));
				
					String line = null;
					int count = 0;
					int i = 0;

					while ((line = reader.readLine()) != null) {
						System.out.println("[" + count + "] " + line);
						count++;
					}
					reader.close();

					memOutput.line(1); // 게시물 수정
					System.out.print("수정할 줄 번호를 입력해 주세요:");
					int alter = scan.nextInt();
					memOutput.line(1);

					System.out.print("수정할 내용 입력:");
					String alterContents = scan.nextLine();
						   alterContents = scan.nextLine();

					System.out.println(groups[c - 1].getName());

					String path1 = String.format(
							"data\\게시물 수정\\%s",
							groups[c - 1].getName());

					File alterFile = new File(path1);
					alterFile.createNewFile();

					BufferedWriter writer = new BufferedWriter(new FileWriter(alterFile));
					reader = new BufferedReader(new FileReader(groups[c - 1]));

					line = null;
					count = 0;

					while ((line = reader.readLine()) != null) {

						if (count == alter) {
							writer.write(alterContents);
							writer.newLine();
							count++;
							continue;
						}

						writer.write(line);
						writer.newLine();
						count++;
					}
					reader.close();
					writer.close();

					String path = groups[c - 1].getAbsolutePath();
					groups[c - 1].delete();

					alterFile.renameTo(new File(path));

					System.out.println("게시물 수정을 완료했습니다.");

					memOutput.line(1);
					PrintList.postList(this.num);
					memOutput.line(1);
					groupSelectMenu();

			}

		} catch (Exception e) {
			System.out.println("GroupWork.alterPost");
			e.printStackTrace();
		}



	}
	
	

	private void deletePost(int c) {

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {

			if (c > 0 && c < groups.length + 1) {

				BufferedReader reader = new BufferedReader(new FileReader(groups[c - 1]));
				if (reader.readLine().contains(userLogin.id)) { // id 받기

					reader.close();

					memOutput.line(1);
					System.out.print("정말 삭제하시겠습니까?(y/n):"); // 게시물 삭제
					String f = scan.next();

					if (f.equals("y")) {

						groups[c - 1].delete();
						System.out.println("삭제 완료");
						memOutput.line(1);
						memOutput.pause();
						memOutput.line(1);
						PrintList.postList(this.num);
						memOutput.line(1);
						groupSelectMenu();

					} else {
						reader.close();
						memOutput.line(1);
						memOutput.pause();
						memOutput.line(1);
						PrintList.postList(this.num);
						memOutput.line(1);
						groupSelectMenu();
					}



				} else {
					reader.close();
					memOutput.line(1);
					System.out.println("작성자만 글을 삭제할 수 있습니다.");
					memOutput.line(1);
					memOutput.pause();
					memOutput.line(1);
					PrintList.postList(this.num);
					memOutput.line(1);
					groupSelectMenu();
				}
			}

		} catch (Exception e) {
			System.out.println("GroupWork.deletePost");
			e.printStackTrace();
		}

	}
	
	private void adminDeletePost(int c) {

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s", dirs[num - 1].getName()));

		File[] groups = group.listFiles();

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (c > 0 && c < groups.length + 1) {

			memOutput.line(1);
			System.out.print("정말 삭제하시겠습니까?(y/n):"); // 게시물 삭제
			String f = scan.next();

			if (f.equals("y")) {

				System.out.println(groups[c - 1].delete());
				System.out.println("삭제 완료");
				memOutput.line(1);
				memOutput.pause();
				memOutput.line(1);
				PrintList.postList(this.num);
				memOutput.line(1);
				groupSelectMenu();

			} else {
				memOutput.line(1);
				memOutput.pause();
				memOutput.line(1);
				PrintList.postList(this.num);
				memOutput.line(1);
				groupSelectMenu();
			}



		}
	}



	public void deleteOrAlterPost() {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.print("수정/삭제 할 게시물 선택: ");
		int c = scan.nextInt();
		memOutput.line(1);
		System.out.println("1. 삭제 ");
		System.out.println("2. 수정 ");
		memOutput.line(1);
		System.out.print("입력:");
		String d = scan.next();
		memOutput.line(1);

		if (d.equals("1")) {
			deletePost(c);
		} else {
			alterPost(c);
		}

	}

	private void createPost() {

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.print("게시물을 작성하시겠습니까?(y/n): ");
		String input = scan.next();
				


		if (input.equals("y")) {

			try {
				memOutput.line(1);
				System.out.print("제목: ");
				String title = scan.nextLine();	
					  title = scan.nextLine();	


				File post = new File(String.format(group + "\\%s.txt", title));

				post.createNewFile();

				BufferedWriter writer = new BufferedWriter(new FileWriter(post));

		
				writer.write(String.format("[제목]%-17s  -%s\n",
						post.getName().substring(0, post.getName().length() - 4), userLogin.id)); // 제목에 아이디 추가
				writer.write("-------------------------------------------------");
				writer.newLine();

				boolean flag = true;
				System.out.print("입력('exit'입력시 종료):");

				while (flag) {

					String write = scan.nextLine();
					write = memOutput.swearWord(write);

					if (write.contains("exit")) {
						break;
					}
					writer.write(write);
					writer.newLine();

				}

				writer.newLine();
				writer.write("-------------------------------------------------");
				writer.newLine();


				writer.close();
				PrintList.postList(num);
				groupSelectMenu();


			} catch (Exception e) {
				System.out.println("GroupWork.createPost");
				e.printStackTrace();
			}


		} else {
			PrintList.postList(num);
			groupSelectMenu();
		}
	}

	public void writeComment() {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();
		
		File group =
		new File(String.format("data\\모임\\%s",
		dirs[num - 1].getName()));
		
		File[] groups = group.listFiles();
		
		File post = new File(String.format(group + "\\%s" // 선택한 번호에 맞는 그룹 경로
		, groups[this.input - 1].getName()));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		memOutput.groupComment(dirs[num - 1].getName());
		
		System.out.print("입력: ");
		String input2 = scan.next();
			   

		if (input2.equals("1")) {
			//댓글작성
			try {
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(post, true));
				BufferedReader reader = new BufferedReader(new FileReader(post));

					System.out.print("댓글(입력): ");
					String comment = scan.nextLine();
						   comment = scan.nextLine();
						   comment = memOutput.swearWord(comment);

					writer.write(String.format("%s: %s\n",userLogin.id, comment)); // 아이디 부분에 id받기
					writer.close();
					reader.close();
					
					reader = new BufferedReader(new FileReader(post));
					String line = null;
					memOutput.line(1);
					
					while((line = reader.readLine()) != null) {
						System.out.println(line);
					}
					
					reader.close();
					
					writeComment();					
					
					
					

				} catch (Exception e) {
				System.out.println("GroupWork.writeComment");
				e.printStackTrace();
			}		
			

		} else {
			PrintList.postList(num);
			groupSelectMenu();

		}



	}


	public void readPost() {

		File dir = new File("data\\모임");
		File[] dirs = dir.listFiles();

		File group = new File(String.format("data\\모임\\%s",
				dirs[num - 1].getName()));

		File[] groups = group.listFiles();
		/////////////////////////////////////////////////////////////////////////////////////////////

		System.out.print("읽을 게시물 선택: ");

		this.input = scan.nextInt();
		memOutput.line(1);
		
		File post = new File(String.format(group + "\\%s" // 선택한 번호에 맞는 그룹 경로
				, groups[input - 1].getName()));



		try {
			BufferedReader reader = new BufferedReader(new FileReader(post));

			String line = null;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			reader.close();

		} catch (Exception e) {
			System.out.println("GroupWork.readPost");
			e.printStackTrace();
		}

		

		writeComment();

		

	}


}