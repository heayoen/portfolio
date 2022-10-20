package project.managereMode;

public class BlackList {

	private String bid;
	private String bpassword;
	private String bname;
	private String bbirth;
	private String bgender;
	private String btel;
	private String bfollow;
	private String bgenre;
	private String bschool;


	public BlackList(String bid, String bpassword, String bname, String bbirth, String bgender,
			String btel, String bfollow, String bgenre, String bschool) {
		super();
		this.bid = bid;
		this.bpassword = bpassword;
		this.bname = bname;
		this.bbirth = bbirth;
		this.bgender = bgender;
		this.btel = btel;
		this.bfollow = bfollow;
		this.bgenre = bgenre;
		this.bschool = bschool;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBpassword() {
		return bpassword;
	}

	public void setBpassword(String bpassword) {
		this.bpassword = bpassword;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBbirth() {
		return bbirth;
	}

	public void setBbirth(String bbirth) {
		this.bbirth = bbirth;
	}

	public String getBgender() {
		return bgender;
	}

	public void setBgender(String bgender) {
		this.bgender = bgender;
	}

	public String getBtel() {
		return btel;
	}

	public void setBtel(String btel) {
		this.btel = btel;
	}

	public String getBfollow() {
		return bfollow;
	}

	public void setBfollow(String bfollow) {
		this.bfollow = bfollow;
	}

	public String getBgenre() {
		return bgenre;
	}

	public void setBgenre(String bgenre) {
		this.bgenre = bgenre;
	}

	public String getBschool() {
		return bschool;
	}

	public void setBschool(String bschool) {
		this.bschool = bschool;
	}



}
