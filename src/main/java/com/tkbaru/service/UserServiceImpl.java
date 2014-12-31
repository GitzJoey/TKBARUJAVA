package com.tkbaru.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PersonService personManager;

	@Autowired
	PhoneListService phoneListManager;

	@Autowired
	RoleService roleManager;
	
	@Override
	public List<User> getAllUser() {
		List<User> userlist = userDAO.getAllUser();
		
		return userlist;
	}

	@Override
	@Transactional
	public User getUserById(int selectedId) {
		logger.info("[getUserById] " + "");
		User result = userDAO.getUserById(selectedId);
		
		result.setRoleEntity(roleManager.getRoleById(result.getRoleId()));		
		result.setPersonEntity(personManager.getPersonEntityById(result.getPersonId()));
		result.getPersonEntity().setPhoneList(phoneListManager.getPhoneListById(result.getPersonId()));
		
		return result;
	}

	@Override
	@Transactional
	public void addNewUser(User usr) {
		logger.info("[addNewUser] " + "");
		
		userDAO.addUser(usr);
		personManager.addPerson(usr.getPersonEntity());			
	}

	@Override
	@Transactional
	public void editUser(User usr) {
		logger.info("[editUser] " + "");
		
	}

	@Override
	public User getUserByUserName(String userName) {
		logger.info("[getUserByUserName] " + "");
		
		return userDAO.getUser(userName);
	}

}
