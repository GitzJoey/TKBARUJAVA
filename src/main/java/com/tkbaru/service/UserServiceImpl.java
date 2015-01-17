package com.tkbaru.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tkbaru.common.CommonHelper;
import com.tkbaru.common.RandomProvider;
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
	ServletContext servletContext;
	
	@Autowired
	RoleService roleManager;

	private BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager;
	public void setCryptoBCryptPasswordEncoderManager(BCryptPasswordEncoder cryptoBCryptPasswordEncoderManager) {
		this.cryptoBCryptPasswordEncoderManager = cryptoBCryptPasswordEncoderManager;
	}
	
	@Override
	public List<User> getAllUser() {
		List<User> userlist = userDAO.getAllUser();
		
		for(User u:userlist) {
			u.setRoleEntity(roleManager.getRoleById(u.getRoleId()));
			u.setPersonEntity(personManager.getPersonById(u.getPersonId()));
		}
		
		return userlist;
	}

	@Override
	@Transactional
	public User getUserById(int selectedId) {
		logger.info("[getUserById] " + "");
		User result = userDAO.getUserById(selectedId);
		
		result.setRoleEntity(roleManager.getRoleById(result.getRoleId()));		
		result.setPersonEntity(personManager.getPersonById(result.getPersonId()));
		
		return result;
	}

	@Override
	@Transactional
	public void addNewUser(User usr) {
		logger.info("[addNewUser] " + "");

		try {
			String path = servletContext.getRealPath("/") + "resources\\images\\user\\";
			RandomProvider rndm = new RandomProvider();			
			String fileName = Integer.toString(usr.getUserId()) + "-" + usr.getUserName() + "-" + CommonHelper.todayDateToString() + "-" + rndm.generateRandomInString() + ".jpg"; 			
			usr.getPersonEntity().getImageBinary().transferTo(new File(path + fileName).getAbsoluteFile());
			
			usr.getPersonEntity().setPhotoPath(fileName);
			
			usr.setUserPassword(cryptoBCryptPasswordEncoderManager.encode(usr.getUserPassword()));
			
		} catch (IllegalStateException e) {
			
		} catch (IOException e) {
			
		}

		usr.setPersonId(personManager.addPerson(usr.getPersonEntity()));
		
		userDAO.addUser(usr);
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

	@Override
	public void deleteUser(int selectedId) {
		logger.info("[deleteUser] " + "");
		
		userDAO.deleteUser(selectedId);		
	}

}
