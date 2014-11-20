package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.PersonDAO;
import com.tkbaru.dao.RoleDAO;
import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PersonDAO personDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public List<User> getAllUser() {
		List<User> userlist = userDAO.getAllUser();
		
		return userlist;
	}

	@Override
	public User getUserById(int selectedId) {
		User result = userDAO.getUserById(selectedId);
		
		result.setRoleFunctionEntity(roleDAO.getRoleFunctionById(result.getUserId()));
		result.setPersonEntity(personDAO.getPersonEntityById(result.getPersonId()));
		result.getPersonEntity().setPhoneList(personDAO.getPhoneListById(result.getPersonId()));
		
		return result;
	}

	@Override
	public void addNewUser(User usr) {
		userDAO.addUser(usr);
		personDAO.addPerson(usr.getPersonEntity());			
	}

	@Override
	public void editUser(User usr) {
		// TODO Auto-generated method stub
		
	}

}
