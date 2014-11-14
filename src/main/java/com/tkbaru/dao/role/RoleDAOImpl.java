package com.tkbaru.dao.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.Function;
import com.tkbaru.model.Person;
import com.tkbaru.model.RoleFunction;
import com.tkbaru.model.User;

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
		}
		
		return result;
	}
	
}
