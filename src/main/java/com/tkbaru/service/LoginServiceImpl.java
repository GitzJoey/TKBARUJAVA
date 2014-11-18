package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

public class LoginServiceImpl implements LoginService {
	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleService roleManager;
	
	@Override
	public boolean successLogin(String userName) {
		boolean result = false;
		
		User userdata = userDAO.getUser(userName);
		
		if (userdata == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public User createUserContext(String userName) {
		
		User userdata = userDAO.getUser(userName);
		
		userdata.setRoleFunctionEntity(roleManager.getRoleFunctionByUserId(userdata.getUserId()));
		
		return userdata;
	}

}
