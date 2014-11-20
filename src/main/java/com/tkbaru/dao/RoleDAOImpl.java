package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.Function;
import com.tkbaru.model.RoleFunction;

public class RoleDAOImpl implements RoleDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public RoleFunction getRoleFunctionById(int userId) {
		RoleFunction result = new RoleFunction();
		String sqlquery = 
			"SELECT	role.role_id,                                                        " +
			"		role.name,                                                           " +
			"        func.function_id,                                                   " +
			"        func.function_code,                                                 " +
			"        func.module_icon,                                                   " +
			"        func.module,                                                        " +
			"        func.menu_icon,                                                     " +
			"        func.menu_name,                                                     " +
			"        func.url,                                                           " +
			"        func.order_num,                                                     " +	
			"        func.deep_level                                                     " +
			"FROM tb_user usr                                                            " +
			"INNER JOIN tb_role role ON usr.role_id = role.role_id                       " +
			"INNER JOIN tb_rolefunction rolefunc ON role.role_id = rolefunc.role_id      " +
			"INNER JOIN tb_function func ON rolefunc.function_id  = func.function_id     " +
			"WHERE usr.user_id = ?                                                       " +
			"ORDER BY func.order_num ASC                                                 " ;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery, new Object[] { userId });
	
		List<Function> funcList = new ArrayList<Function>();
		
		for (int x = 0; x < rows.size(); x++) {
			if (x == 0) {
				result.setRoleId(Integer.valueOf(String.valueOf(rows.get(x).get("role_id"))));
				result.setRoleName(String.valueOf(rows.get(x).get("name")));
			}
			
			Function f = new Function();
			f.setFunctionId(Integer.valueOf(String.valueOf(rows.get(x).get("function_id"))));
			f.setFunctionCode(String.valueOf(rows.get(x).get("function_code")));
			f.setModule(String.valueOf(rows.get(x).get("module")));
			f.setModuleIcon(String.valueOf(rows.get(x).get("module_icon")));
			f.setMenuName(String.valueOf(rows.get(x).get("menu_name")));
			f.setMenuIcon(String.valueOf(rows.get(x).get("menu_icon")));
			f.setUrlLink(String.valueOf(rows.get(x).get("url")));
			f.setOrderNum(Integer.valueOf(String.valueOf(rows.get(x).get("order_num"))));
			f.setDeepLevel(Integer.valueOf(String.valueOf(rows.get(x).get("deep_level"))));
			
			funcList.add(f);
		}
		
		result.setFunctionList(funcList);
		
		return result;
	}

	@Override
	public List<RoleFunction> getSummaryRoleList() {
		List<RoleFunction> result = new ArrayList<RoleFunction>();
		String sqlquery = 
			"SELECT	role.role_id,                                                        " +
			"		role.name                                                            " +
			"FROM tb_role role                                                           " +
			"WHERE role.status = 'A'                                                     " ;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
	
		for (int x = 0; x < rows.size(); x++) {
			RoleFunction rf = new RoleFunction();
			
			rf.setRoleId(Integer.valueOf(String.valueOf(rows.get(x).get("role_id"))));
			rf.setRoleName(String.valueOf(rows.get(x).get("name")));
			
			result.add(rf);
		}
		
		return result;
	}
	
}
