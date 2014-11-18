package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
		
	@Override
	public List<User> getAllUser() {
		List<User> userlist = userDAO.getAllUser();
		
		return userlist;
	}

}
