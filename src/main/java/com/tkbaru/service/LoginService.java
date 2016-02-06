package com.tkbaru.service;

import com.tkbaru.model.User;

public interface LoginService {
	public boolean checkDB();
	public boolean checkPassword(String userName, String userPswd);
	public User createUserContext(String userName);
	public User changePassword(int userId, String newPassword);
}
