package com.test.spring.data;

import lombok.Data;

@Data
public class BoardDTO {
	private String contentseq;
	private String userseq;
	private String nick;
	private String categoryseq;
	private String type;	
	private String subject;
	private String regdate;
	private String text;

}
