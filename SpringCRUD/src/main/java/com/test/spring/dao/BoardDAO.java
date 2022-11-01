package com.test.spring.dao;

import java.util.List;

import com.test.spring.data.BoardDTO;
import com.test.spring.data.UserDTO;

public interface BoardDAO {

	String login(UserDTO dto);

	List<BoardDTO> list();

	List<BoardDTO> category();

	int add(BoardDTO bdto);

	String addcontentseq(String userseq);

	BoardDTO view(String contentseq);

	void edit(BoardDTO bdto);

	void del(String contentseq);

}
