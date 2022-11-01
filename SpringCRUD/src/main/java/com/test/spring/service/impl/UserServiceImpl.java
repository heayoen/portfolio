package com.test.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.dao.BoardDAO;
import com.test.spring.dao.UserDAO;
import com.test.spring.data.UserDTO;
import com.test.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
private UserDAO dao;
	
	@Autowired
	public UserServiceImpl(UserDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public UserDTO userInfo(String auth) {
		
		return dao.getuserinfo(auth);
	}
	
	@Override
	public int userUpdate(UserDTO udto) {
		
		return dao.userUpdate(udto);
	}
	
	@Override
	public int chkId(String id) {
	
		return dao.chkId(id);
	}

	@Override
	public int chkNick(String nick) {
	
		return dao.chkNick(nick);
	}
	
	@Override
	public int regist(UserDTO udto) {
		// TODO Auto-generated method stub
		return dao.regist(udto);
	}
}
