package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tkbaru.model.Customer;
import com.tkbaru.model.Function;

public class FunctionDAOImpl implements FunctionDAO {
	private static final Logger logger = LoggerFactory.getLogger(FunctionDAOImpl.class);

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Function> getAllFunctions() {
		logger.info("[getAllFunctions] " + "");

		List<Function> result = new ArrayList<Function>();
		String sqlquery = "SELECT function_id,     	" + "		function_code,   	" + "		module,          	"
				+ "		module_icon,     	" + "		menu_name,       	" + "		menu_icon,       	"
				+ "		url,             	" + "		order_num,       	" + "		parent_function_id	"
				+ "FROM tb_function        	";

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
			res.setParentFunctionId(Integer.valueOf(String.valueOf(row.get("parent_function_id"))));

			result.add(res);
		}

		logger.info("Function size: " + result.size());

		return result;
	}

	@Override
	public Function getFunctionById(int selectedId) {
		logger.info("[getFunctionById] " + "");

		Function result = new Function();

		String sqlquery = "SELECT function_id,     	" + "		function_code,   	" + "		module,          	"
				+ "		module_icon,     	" + "		menu_name,       	" + "		menu_icon,       	"
				+ "		url,             	" + "		order_num,       	" + "		parent_function_id	"
				+ "FROM tb_function        	" + "WHERE function_id = ?   	";

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
				f.setParentFunctionId(rs.getInt("parent_function_id"));

				return f;
			}
		});

		return result;
	}

	@Override
	public void addFunction(Function func) {
		logger.info("[addFunction] " + "");

		String sql = "INSERT INTO tb_function (function_code, module, module_icon, menu_name, menu_icon, url, order_num, parent_function_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate
					.update(sql,
							new Object[] { func.getFunctionCode(), func.getModule(), func.getModuleIcon(),
									func.getMenuName(), func.getMenuIcon(), func.getUrlLink(), func.getOrderNum(),
									func.getParentFunctionId() });
		} catch (Exception err) {
			logger.info("Error : " + err.getMessage());
		}
	}

	@Override
	public void editFunction(Function func) {
		logger.info("[editFunction] " + "");

		String query = "UPDATE tb_function SET function_code = ?, module = ?, module_icon = ?, menu_name = ?, menu_icon = ?, url = ?, order_num = ?, parent_function_id = ? "
				+ "WHERE function_id = ? ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int out = 0;

		try {
			Object[] args = new Object[] { func.getFunctionCode(), func.getModule(), func.getModuleIcon(),
					func.getMenuName(), func.getMenuIcon(), func.getUrlLink(), func.getOrderNum(),
					func.getParentFunctionId(), func.getFunctionId() };

			out = jdbcTemplate.update(query, args);
		} catch (Exception err) {
			logger.info("Error : " + err.getMessage());
		}

		logger.info("Function updated successfully, row updated : " + out);
	}

	@Override
	public void deleteFunction(int selectedId) {
		logger.info("[deleteFunction] " + "");

		String query = "DELETE FROM tb_function WHERE function_id = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int out = 0;

		try {
			out = jdbcTemplate.update(query, selectedId);
		} catch (Exception err) {
			logger.info("Error : " + err.getMessage());
		}

		logger.info("Function deleted successfully, row deleted : " + out);
	}

	@Override
	public List<Function> getFunctionById(String selectedIds) {
		logger.info("[getFunctionById] " + "selectedIds: " + selectedIds);

		List<Function> result = new ArrayList<Function>();
		String sqlquery = "SELECT function_id,     	" + "		function_code,   	" + "		module,          	"
				+ "		module_icon,     	" + "		menu_name,       	" + "		menu_icon,       	"
				+ "		url,             	" + "		order_num,       	" + "		parent_function_id	"
				+ "FROM tb_function        	" + "WHERE function_id IN    	" + selectedIds;

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
			res.setParentFunctionId(Integer.valueOf(String.valueOf(row.get("parent_function_id"))));

			result.add(res);
		}

		logger.info("Function size: " + result.size());

		return result;
	}

	@Override
	public List<Function> getChildFunctions(int selectedId) {
		logger.info("[getAllFunctions] " + "");

		List<Function> result = new ArrayList<Function>();
		String sqlquery = "SELECT function_id,     	" + "		function_code,   	" + "		module,          	"
				+ "		module_icon,     	" + "		menu_name,       	" + "		menu_icon,       	"
				+ "		url,             	" + "		order_num,       	" + "		parent_function_id	"
				+ "FROM tb_function where parent_function_id="+selectedId;

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
			res.setParentFunctionId(Integer.valueOf(String.valueOf(row.get("parent_function_id"))));

			result.add(res);
		}

		logger.info("Function size: " + result.size());

		return result;
	}

}
