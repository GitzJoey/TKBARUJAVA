package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.User;

public interface UserService {
	public List<User> getAllUser();
	public List<User> getAllUserByType(String userType);
	public User getUserById(int selectedId);
	public User getUserByUserName(String userName);
	public void addNewUser(User usr);
	public void editUser(User usr);
	public void deleteUser(int selectedId);
}
