package com.tkbaru.service;

import com.tkbaru.model.User;

public interface LoginService {
	public boolean checkDB();
	public boolean successLogin(String userName);
	public User createUserContext(String userName);
}
