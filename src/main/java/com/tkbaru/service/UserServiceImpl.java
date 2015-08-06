package com.tkbaru.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tkbaru.common.Converter;
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
	RoleService roleManager;
	
	@Autowired
	StoreService storeManager;

	@Autowired
	LookupService lookupManager;
	
	@Autowired
	ServletContext servletContext;

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
			u.setStoreEntity(storeManager.getStoreById(u.getStoreId()));
			u.setStatusLookup(lookupManager.getLookupByKey(u.getUserStatus()));
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
		result.setStoreEntity(storeManager.getStoreById(result.getStoreId()));
		result.setStatusLookup(lookupManager.getLookupByKey(result.getUserStatus()));
		return result;
	}

	@Override
	@Transactional
	public void addNewUser(User usr) {
		logger.info("[addNewUser] " + "");

		try {
			String path = servletContext.getRealPath("/") + "resources\\images\\user\\";
			RandomProvider rndm = new RandomProvider();			
			String fileName = Integer.toString(usr.getUserId()) + "-" + usr.getUserName() + "-" + Converter.todayDateToString() + "-" + rndm.generateRandomInString() + ".jpg"; 			
			usr.getPersonEntity().getImageBinary().transferTo(new File(path + fileName).getAbsoluteFile());
			
			usr.getPersonEntity().setPhotoPath(fileName);
			
			usr.setUserPassword(cryptoBCryptPasswordEncoderManager.encode(usr.getUserPassword()));
			
		} catch (Exception e) {
			
		} 
		
		usr.setPersonId(personManager.addPerson(usr.getPersonEntity()));
		
		userDAO.addUser(usr);
	}

	@Override
	@Transactional
	public void editUser(User usr) {
		logger.info("[editUser] " + "");

		try {
			String path = servletContext.getRealPath("/") + "resources\\images\\user\\";
			RandomProvider rndm = new RandomProvider();			
			String fileName = Integer.toString(usr.getUserId()) + "-" + usr.getUserName() + "-" + Converter.todayDateToString() + "-" + rndm.generateRandomInString() + ".jpg"; 			
			usr.getPersonEntity().getImageBinary().transferTo(new File(path + fileName).getAbsoluteFile());
			
			usr.getPersonEntity().setPhotoPath(fileName);
			
			usr.setUserPassword(cryptoBCryptPasswordEncoderManager.encode(usr.getUserPassword()));
			
		} catch (Exception e) {
			
		}
				
		personManager.editPerson(usr.getPersonEntity());
		userDAO.editUser(usr);		
	}

	@Override
	public User getUserByUserName(String userName) {
		logger.info("[getUserByUserName] " + "");
		
		return userDAO.getUser(userName);
	}

	@Override
	public void deleteUser(int selectedId) {
		logger.info("[deleteUser] " + "selectedId:" + selectedId);
		
		User usr = userDAO.getUserById(selectedId);
		usr.setPersonEntity(personManager.getPersonById(usr.getPersonId()));
		
		personManager.deletePerson(usr.getPersonEntity().getPersonId());			
		userDAO.deleteUser(selectedId);		
	}

	@Override
	public List<User> getAllUserByType(String userType) {
		logger.info("[getAllUserByType] " + "userType:" + userType);
		
		List<User> userList = userDAO.getAllUserByType(userType);
		
		for(User u:userList) {
			u.setRoleEntity(roleManager.getRoleById(u.getRoleId()));
			u.setPersonEntity(personManager.getPersonById(u.getPersonId()));
			u.setStoreEntity(storeManager.getStoreById(u.getStoreId()));
			u.setStatusLookup(lookupManager.getLookupByKey(u.getUserStatus()));
		}

		return userList;
	}

	@Override
	public void generateDefaultUser() {

		
	}

	@Override
	public boolean checkUserTableHasData() {
		
		return userDAO.checkUserTableHasData();
	}

}
