package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.person.PersonDAO;
import com.tkbaru.dao.role.RoleDAO;
import com.tkbaru.dao.user.UserDAO;
import com.tkbaru.model.User;

public class LoginServiceImpl implements LoginService {
	@Autowired
	UserDAO userDAO;
	
	RoleDAO roleDAO;
	PersonDAO personDAO;
	
	@Override
	public boolean successLogin(String userName) {
		boolean result = false;
		
		User userdata = userDAO.getUser(userName);
		
		return true;
	}

}
