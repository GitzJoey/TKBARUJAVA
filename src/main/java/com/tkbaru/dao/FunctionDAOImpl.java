package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		String sqlquery = 	"SELECT function_id,     	" + 
							"		function_code,   	" + 
							"		menu_name,       	" + 
							"		menu_icon,       	" + 
							"		url,             	" + 
							"		order_num,       	" + 
							"		parent_function_id	" + 
							"FROM tb_function        	";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);

		for (Map<String, Object> row : rows) {
			Function res = new Function();

			res.setFunctionId(Integer.valueOf(String.valueOf(row.get("function_id"))));
			res.setFunctionCode(String.valueOf(row.get("function_code")));
			res.setMenuName(String.valueOf(row.get("menu_name")));
			res.setMenuIcon(String.valueOf(row.get("menu_icon")));
			res.setUrlLink(String.valueOf(row.get("url")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			if (row.get("parent_function_id") != null)  
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

		String sqlquery = 	"SELECT function_id,     	" + 
							"		function_code,   	" + 
							"		menu_name,       	" + 
							"		menu_icon,       	" + 
							"		url,             	" + 
							"		order_num,       	" + 
							"		parent_function_id	" + 
							"FROM tb_function        	" + 
							"WHERE function_id = ?   	";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { selectedId }, new RowMapper<Function>() {
			@Override
			public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
				Function f = new Function();

				f.setFunctionId(rs.getInt("function_id"));
				f.setFunctionCode(rs.getString("function_code"));
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

		String sql = "INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate
					.update(sql,
							new Object[] { func.getFunctionCode(), func.getMenuName(), func.getMenuIcon(), 
									func.getUrlLink(), func.getOrderNum(), func.getParentFunctionId() });
		} catch (Exception err) {
			logger.info("Error : " + err.getMessage());
		}
	}

	@Override
	public void editFunction(Function func) {
		logger.info("[editFunction] " + "");

		String query = "UPDATE tb_function SET function_code = ?, menu_name = ?, menu_icon = ?, url = ?, order_num = ?, parent_function_id = ? "
				+ "WHERE function_id = ? ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int out = 0;

		try {
			Object[] args = new Object[] { func.getFunctionCode(), func.getMenuName(), func.getMenuIcon(), 
					func.getUrlLink(), func.getOrderNum(), func.getParentFunctionId(), func.getFunctionId() };

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
		String sqlquery = 	"SELECT function_id,     	" + 
							"		function_code,   	" + 
							"		menu_name,       	" + 
							"		menu_icon,       	" + 
							"		url,             	" + 
							"		order_num,       	" + 
							"		parent_function_id	" + 
							"FROM tb_function        	" + 
							"WHERE function_id IN    	" + selectedIds;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);

		for (Map<String, Object> row : rows) {
			Function res = new Function();

			res.setFunctionId(Integer.valueOf(String.valueOf(row.get("function_id"))));
			res.setFunctionCode(String.valueOf(row.get("function_code")));
			res.setMenuName(String.valueOf(row.get("menu_name")));
			res.setMenuIcon(String.valueOf(row.get("menu_icon")));
			res.setUrlLink(String.valueOf(row.get("url")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			if (row.get("parent_function_id") != null)
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
		String sqlquery = 	"SELECT function_id,     	" + 
							"		function_code,   	" + 
							"		menu_name,       	" + 
							"		menu_icon,       	" + 
							"		url,             	" + 
							"		order_num,       	" + 
							"		parent_function_id	" + 
							"FROM tb_function where parent_function_id = " + selectedId;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);

		for (Map<String, Object> row : rows) {
			Function res = new Function();

			res.setFunctionId(Integer.valueOf(String.valueOf(row.get("function_id"))));
			res.setFunctionCode(String.valueOf(row.get("function_code")));
			res.setMenuName(String.valueOf(row.get("menu_name")));
			res.setMenuIcon(String.valueOf(row.get("menu_icon")));
			res.setUrlLink(String.valueOf(row.get("url")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			if (row.get("parent_function_id") != null)
				res.setParentFunctionId(Integer.valueOf(String.valueOf(row.get("parent_function_id"))));

			result.add(res);
		}

		logger.info("Function size: " + result.size());

		return result;
	}

	@Override
	public void generateDefaultFunctions() {
		logger.info("[generateDefaultFunctions] " + "");
		String[] sql = {
				/*Purchase Order*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_PO', 'Purchase Order','fa fa-truck fa-fw', '#', 100100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_PO_PURCHASEORDER', 'Purchase Order','fa fa-truck fa-fw', '/po/add', 100200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_PO';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_PO_REVISE', 'Revise PO','fa fa-code-fork fa-rotate-180 fa-fw', '/po/revise', 100300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_PO';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_PO_PAYMENT', 'PO Payment', 'fa fa-calculator fa-fw', '/po/revise', 100400, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_PO';",

				/*Sales Order*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_SO', 'Sales Order', 'fa fa-cart-arrow-down fa-fw', '#', 200100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_SO_SALESORDER', 'Sales Order', 'fa fa-cart-arrow-down fa-fw', '/sales/add', 200200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_SO';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_SO_REVISE', 'Revise SO', 'fa fa fa-code-fork fa-fw', '/sales/revise', 200300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_SO';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_SO_PAYMENT', 'SO Payment', 'fa fa-calculator fa-fw', '/sales/payment', 200400, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_SO';",

				/*Price*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_PRICE', 'Today Price','fa  fa-barcode fa-fw', '#', 300100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_PRICE_TODAYPRICE', 'Today Price', 'fa fa-barcode fa-fw', '/price/todayprice', 300200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_PRICE';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_PRICE_PRICELEVEL', 'Price Level','fa  fa-table fa-fw', '/price/pricelevel', 300300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_PRICE';",

				/*Warehouse*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_WH', 'Warehouse', 'fa fa-wrench fa-fw', '#', 400100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_WH_WAREHOUSE', 'Dashboard', 'fa fa-wrench fa-fw', '/warehouse/dashboard', 400200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_WH';",

				/*Bank*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_BANK', 'Bank', 'fa fa-bank fa-fw', '#', 500100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_BANK_UPLOAD', 'Upload', 'fa fa-cloud-upload fa-fw', '/bank/upload', 500200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_BANK';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_BANK_CONSOLIDATE', 'Consolidate', 'fa fa-compress fa-fw', '/bank/consolidate', 500300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_BANK';",

				/*Reports*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_RPT', 'Reports', 'fa fa-bar-chart-o fa-fw', '#', 600100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_RPT_RPTTRX', 'Transactions', 'fa fa-connectdevelop fa-fw', '/report/id/rpttrx', 600200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_RPT';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_RPT_RPTMNTR', 'Monitoring', 'fa fa-eye fa-fw', '/report/id/rptmntr', 600300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_RPT';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_RPT_RPT3', 'Report 3', 'fa fa-plus fa-fw', '/report/id/rpt3', 600300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_RPT';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_RPT_RPTMASTER', 'Master Data', 'fa fa-file-text-o fa-fw', '/report/id/rptmaster', 698100, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_RPT';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_RPT_RPTADMIN', 'Admin Data', 'glyphicon glyphicon-cog', '/report/id/rptadmin', 699100, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_RPT';",

				/*Master Data*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_MASTER', 'Master Data', 'fa fa-file-text-o fa-fw', '#', 998100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_MASTER_CUSTOMER', 'Customer', 'fa fa-smile-o fa-fw', '/customer', 998100, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_MASTER';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_MASTER_SUPPLIER', 'Supplier', 'fa fa-building-o fa-fw', '/supplier', 998200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_MASTER';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_MASTER_PRODUCT', 'Product', 'fa fa-cubes fa-fw', '/product', 998300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_MASTER';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_MASTER_WAREHOUSE', 'Warehouse', 'fa fa-wrench fa-fw', '/warehouse', 998400, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_MASTER';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_MASTER_TRUCK', 'Truck', 'fa fa-truck fa-flip-horizontal fa-fw', '/truck', 998500, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_MASTER';",

				/*Admin Menu*/
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"VALUES ('F_ADM', 'Admin Menu', 'glyphicon glyphicon-cog', '#', 999100, NULL, 0, (SELECT CURRENT_TIMESTAMP()));",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_USER', 'User', 'fa fa-user fa-fw', '/admin/user', 999100, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_STORE', 'Store', 'fa fa-umbrella fa-fw', '/admin/store', 999200, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_ROLE', 'Role', 'fa fa-tree fa-fw', '/admin/role', 999300, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_FUNCTION', 'Function', 'fa fa-minus-square fa-fw', '/admin/function', 999400, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_LOOKUP', 'Lookup', 'fa fa-hand-o-up fa-fw', '/admin/lookup', 999500, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_SMS', 'SMS Service', 'fa fa-cog fa-fw', '#', 999600, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_SMS_SERV', 'Service Status', 'fa fa-cog fa-fw', '/admin/sms', 999601, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM_SMS';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_SMS_IN', 'SMS Inbox', 'fa fa-envelope fa-fw', '/admin/smsin', 999602, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM_SMS';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_SMS_OUT', 'SMS Out', 'fa fa-paper-plane fa-fw', '/admin/smsout', 999603, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM_SMS';",
				"INSERT INTO tb_function (function_code, menu_name, menu_icon, url, order_num, parent_function_id, created_by, created_date) " +
				"SELECT 'F_ADM_MODEM', 'Modem', 'fa fa-cog fa-fw', '/admin/modem', 999604, function_id, 0, (SELECT CURRENT_TIMESTAMP()) FROM tb_function WHERE function_code = 'F_ADM_SMS';"
		};
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.batchUpdate(sql);
	}

	@Override
	public Function getFunctionByCode(String functionCode) {
		logger.info("[getFunctionByCode] " + "functionCode: " + functionCode);

		Function result = new Function();

		String sqlquery = 	"SELECT function_id,     	" + 
							"		function_code,   	" + 
							"		menu_name,       	" + 
							"		menu_icon,       	" + 
							"		url,             	" + 
							"		order_num,       	" + 
							"		parent_function_id	" + 
							"FROM tb_function        	" + 
							"WHERE function_code = ?   	";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { functionCode }, new RowMapper<Function>() {
			@Override
			public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
				Function f = new Function();

				f.setFunctionId(rs.getInt("function_id"));
				f.setFunctionCode(rs.getString("function_code"));
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

}