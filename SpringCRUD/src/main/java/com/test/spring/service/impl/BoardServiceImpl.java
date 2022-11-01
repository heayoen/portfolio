package com.test.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.dao.BoardDAO;
import com.test.spring.data.BoardDTO;
import com.test.spring.data.UserDTO;
import com.test.spring.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO dao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public String login(UserDTO dto) {
		
		return dao.login(dto);
	}
	
	@Override
	public List<BoardDTO> list() {

		return dao.list();
	}
	
	@Override
	public List<BoardDTO> category() {
		
		return dao.category();
	}
	
	@Override
	public int add(BoardDTO bdto) {
		
		return dao.add(bdto);
	}
	
	@Override
	public String addcontentseq(String userseq) {
		
		return dao.addcontentseq(userseq);
	}
	
	@Override
	public BoardDTO view(String contentseq) {
		
		return dao.view(contentseq);
	}
	
	@Override
	public void edit(BoardDTO bdto) {
		dao.edit(bdto);
		
	}
	
	@Override
	public void del(String contentseq) {
		// TODO Auto-generated method stub
		 dao.del(contentseq);
	}
	
	
	
}

