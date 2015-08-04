package com.tkbaru.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tkbaru.model.User;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	SetupService setupManager;
	
	@Autowired
	UserService userManager;

	@Autowired
	PersonService personManager;
	
	@Autowired
	RoleService roleManager;
	
	@Autowired
	StoreService storeManager;
	
	private BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager;
	public void setCryptoBCryptPasswordEncoderManager(BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager) {
		this.cryptoBCryptPasswordEncoderManager = cryptoBCryptPasswordEncoderManager;
	}

	@Override
	public boolean successLogin(String userName, String userPswd) {
		logger.info("[successLogin] " + "userName: " + userName + ", userPswd: " + userPswd);
		
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
		logger.info("[createUserContext] " + "userName: " + userName);
		
		User userdata = userManager.getUserByUserName(userName);
		
		userdata.setPersonEntity(personManager.getPersonById(userdata.getPersonId()));
		userdata.setRoleEntity(roleManager.getRoleById(userdata.getRoleId()));
		userdata.setStoreEntity(storeManager.getStoreById(userdata.getStoreId()));
		
		return userdata;
	}

	@Override
	public boolean checkDB() {
		
		return setupManager.checkDBConnection();
	}

}
