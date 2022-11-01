package com.test.spring.dao;

import com.test.spring.data.UserDTO;

public interface UserDAO {
	

	UserDTO getuserinfo(String auth);

	int userUpdate(UserDTO udto);

	int chkId(String id);

	int chkNick(String nick);

	int regist(UserDTO udto);

}
