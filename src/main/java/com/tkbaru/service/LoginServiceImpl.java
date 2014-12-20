package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

public class LoginServiceImpl implements LoginService {
	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleService roleManager;

	private BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager;
	public void setCryptoBCryptPasswordEncoderManager(BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager) {
		this.cryptoBCryptPasswordEncoderManager = cryptoBCryptPasswordEncoderManager;
	}

	@Override
	public boolean successLogin(String userName, String userPswd) {
		
		User userdata = userDAO.getUser(userName);
			
		if (userdata == null) {
			return false;
		} else if (cryptoBCryptPasswordEncoderManager.matches(userPswd, userdata.getUserPassword())) {
			return false;
		} else {
			return true;	
		}
	}

	@Override
	public User createUserContext(String userName) {
		
		User userdata = userDAO.getUser(userName);
		
		userdata.setRoleFunctionEntity(roleManager.getRoleFunctionByUserId(userdata.getUserId()));
		
		return userdata;
	}

	@Override
	public boolean checkDB() {
		
		return true;
	}

}
