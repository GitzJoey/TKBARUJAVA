package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tkbaru.model.Function;

public class FunctionDAOImpl implements FunctionDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Function> getAllFunctions() {
		List<Function> result = new ArrayList<Function>();
		String sqlquery =  
				"SELECT function_id,     " +
				"		function_code,   " +
				"		module,          " +
				"		module_icon,     " +
				"		menu_name,       " +
				"		menu_icon,       " +
				"		url,             " +
				"		order_num,       " +
				"		deep_level       " +
				"FROM tb_function        " ;
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
	
		for (Map<String, Object> row : rows) {
			Function res = new Function();
			
			res.setFunctionId(Integer.valueOf(String.valueOf(row.get("function_id"))));
			res.setFunctionCode(String.valueOf(row.get("function_code")));
			res.setModule(String.valueOf(row.get("module")));
			res.setModuleIcon(String.valueOf(row.get("module_icon")));
			res.setMenuName(String.valueOf(row.get("menu_name")));
			res.setMenuIcon(String.valueOf(row.get("menu_icon")));
			res.setUrlLink(String.valueOf(row.get("url")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			res.setDeepLevel(Integer.valueOf(String.valueOf(row.get("deep_level"))));
			
			result.add(res);
		}
		
		return result;
	}

	@Override
	public Function getFunctionById(int selectedId) {
		Function result = new Function();
		
		String sqlquery = 
				"SELECT function_id,     " +
				"		function_code,   " +
				"		module,          " +
				"		module_icon,     " +
				"		menu_name,       " +
				"		menu_icon,       " +
				"		url,             " +
				"		order_num,       " +
				"		deep_level       " +
				"FROM tb_function        " +
				"WHERE function_id = ?   " ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { selectedId }, new RowMapper<Function>() {
			@Override
			public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
				Function f = new Function();

				f.setFunctionId(rs.getInt("function_id"));
				f.setFunctionCode(rs.getString("function_code"));
				f.setModule(rs.getString("module"));
				f.setModuleIcon(rs.getString("module_icon"));
				f.setMenuName(rs.getString("menu_name"));
				f.setMenuIcon(rs.getString("menu_icon"));
				f.setUrlLink(rs.getString("url"));
				f.setOrderNum(rs.getInt("order_num"));
				f.setDeepLevel(rs.getInt("deep_level"));

				return f;
			}
		});
				
		return result;
	}

}
