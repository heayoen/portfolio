package com.test.spring.service;

import com.test.spring.data.UserDTO;

public interface UserService {
	
	UserDTO userInfo(String auth);

	int userUpdate(UserDTO udto);

	int chkId(String id);

	int chkNick(String nick);

	int regist(UserDTO udto);


}
