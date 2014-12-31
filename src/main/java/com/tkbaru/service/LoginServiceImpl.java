package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tkbaru.model.User;

public class LoginServiceImpl implements LoginService {
	@Autowired
	UserService userManager;

	@Autowired
	RoleService roleManager;
	
	private BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager;
	public void setCryptoBCryptPasswordEncoderManager(BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager) {
		this.cryptoBCryptPasswordEncoderManager = cryptoBCryptPasswordEncoderManager;
	}

	@Override
	public boolean successLogin(String userName, String userPswd) {
		
		User userdata = userManager.getUserByUserName(userName);
			
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
		
		User userdata = userManager.getUserByUserName(userName);
		
		userdata.setRoleEntity(roleManager.getRoleById(userdata.getRoleId()));
		
		return userdata;
	}

	@Override
	public boolean checkDB() {
		
		return true;
	}

}
