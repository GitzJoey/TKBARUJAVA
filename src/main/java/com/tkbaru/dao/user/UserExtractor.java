package com.tkbaru.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tkbaru.model.User;

public class UserExtractor implements ResultSetExtractor<User> {
	public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		User user = new User();
		
		user.setUserName(resultSet.getString("user_name"));
		
		return user;
	}
}
