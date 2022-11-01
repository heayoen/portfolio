package com.test.spring.data;

import lombok.Data;

@Data
public class UserDTO {
	private String userseq;
	private String name;	
	private String id;
	private String pw;
	private String birth;
	private String nick;
	private String regdate;
	private String pic;
}
