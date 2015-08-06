package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.User;

public interface UserDAO {
	public User getUser(String userName);
	public List<User> getAllUser();
	public List<User> getAllUserByType(String userType);
	public User getUserById(int selectedId);
	public void addUser(User usr);
	public void editUser(User usr);
	public void deleteUser(int selectedId);
	public boolean checkUserTableHasData();
}
