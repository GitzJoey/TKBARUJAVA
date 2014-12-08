package com.tkbaru.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.dao.PersonDAO;
import com.tkbaru.dao.RoleDAO;
import com.tkbaru.dao.UserDAO;
import com.tkbaru.model.User;

@Service
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
	@Transactional
	public User getUserById(int selectedId) {
		User result = userDAO.getUserById(selectedId);
		
		result.setRoleFunctionEntity(roleDAO.getRoleFunctionById(result.getUserId()));
		result.setPersonEntity(personDAO.getPersonEntityById(result.getPersonId()));
		result.getPersonEntity().setPhoneList(personDAO.getPhoneListById(result.getPersonId()));
		
		return result;
	}

	@Override
	@Transactional
	public void addNewUser(User usr) {
		userDAO.addUser(usr);
		personDAO.addPerson(usr.getPersonEntity());			
	}

	@Override
	@Transactional
	public void editUser(User usr) {
		// TODO Auto-generated method stub
		
	}

}
