package com.test.spring.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring.dao.BoardDAO;
import com.test.spring.data.BoardDTO;
import com.test.spring.data.UserDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	private SqlSessionTemplate template;
	
	@Autowired
	public BoardDAOImpl(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public String login(UserDTO dto) {
		
		return template.selectOne("board.login", dto);
	}
	
	@Override
	public List<BoardDTO> list() {

		return  template.selectList("board.list");
	}
	
	@Override
	public List<BoardDTO> category() {

		return template.selectList("board.category");
	}
	
	@Override
	public int add(BoardDTO bdto) {
		
		return template.insert("board.add", bdto);
	}
	
	@Override
	public String addcontentseq(String userseq) {

		return template.selectOne("board.addcontentseq", userseq);
	}
	
	@Override
	public BoardDTO view(String contentseq) {
		
		return template.selectOne("board.view", contentseq);
	}
	
	@Override
	public void edit(BoardDTO bdto) {

		template.update("board.edit", bdto);
	}
	
	@Override
	public void del(String contentseq) {
		
		template.delete("board.del", contentseq);
	}
	
}
