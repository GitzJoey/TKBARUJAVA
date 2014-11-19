package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.Function;

public class FunctionDAOImpl implements FunctionDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Function> getAllFunctions() {
		List<Function> result = new ArrayList<Function>();
		String sqlquery = ""; 
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
	
		for (Map<String, Object> row : rows) {
			Function res = new Function();

			result.add(res);
		}
		
		return result;
	}

}
