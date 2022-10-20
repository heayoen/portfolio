package project.managereMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BlacklistData {
	public static ArrayList<BlackList> blist = new ArrayList<BlackList>();

	public static void loadBlacklist() {


		try {// 파일 -> 컬렉션에 옮김
			BufferedReader reader =
					new BufferedReader(new FileReader("data\\블랙리스트\\블랙리스트.txt"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String temp[] = line.split("■");
				BlackList b = new BlackList(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],
						temp[6], temp[7], temp[8]);


				blist.add(b);

			}
			reader.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void saveBalcklist() {


		try {
			BufferedWriter writer =
					new BufferedWriter(new FileWriter("data\\블랙리스트\\블랙리스트.txt"));


			for (BlackList b : blist) {
				String str = String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■\n", b.getBid(),
						b.getBpassword(), b.getBname(), b.getBbirth(), b.getBgender(), b.getBtel(),
						b.getBfollow(), b.getBgenre(), b.getBschool());

				writer.write(str);

			}
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
}


