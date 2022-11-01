package com.test.spring.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring.dao.UserDAO;
import com.test.spring.data.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

private SqlSessionTemplate template;
	
	@Autowired
	public UserDAOImpl(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public UserDTO getuserinfo(String userseq) {
		
		return template.selectOne("user.getuserinfo",userseq);
	}
	
	@Override
	public int userUpdate(UserDTO udto) {
	
		return template.update("user.edit", udto);
	}
	
	@Override
	public int chkId(String id) {
	
		return template.selectOne("user.chkid", id);
	}
	
	@Override
	public int chkNick(String nick) {

		return template.selectOne("user.chknick", nick);
	}
	
	@Override
	public int regist(UserDTO udto) {

		return template.insert("user.regist", udto);
	}
}
