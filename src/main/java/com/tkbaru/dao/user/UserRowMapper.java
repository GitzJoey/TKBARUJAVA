package com.tkbaru.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tkbaru.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		UserExtractor userExtractor = new UserExtractor();
		return userExtractor.extractData(arg0);
	}
	
}
