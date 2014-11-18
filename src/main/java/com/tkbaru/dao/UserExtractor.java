package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tkbaru.model.User;

public class UserExtractor implements ResultSetExtractor<User> {
	public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		User user = new User();
		
		user.setUserId(Integer.valueOf(resultSet.getString("user_id")));
		user.setUserName(resultSet.getString("user_name"));
		
		return user;
	}
}
