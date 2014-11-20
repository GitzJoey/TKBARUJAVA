package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.User;

public interface UserService {
	public List<User> getAllUser();
	public User getUserById(int selectedId);
	public void addNewUser(User usr);
	public void editUser(User usr);
}
