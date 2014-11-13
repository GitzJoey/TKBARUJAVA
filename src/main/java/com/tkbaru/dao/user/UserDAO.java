package com.tkbaru.dao.user;

import java.util.List;

import com.tkbaru.model.User;

public interface UserDAO {
	public User getUser(String userName);
	public List<User> getAllUser();
}
