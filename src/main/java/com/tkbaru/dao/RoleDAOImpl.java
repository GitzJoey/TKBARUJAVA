package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Function;
import com.tkbaru.model.Role;
import com.tkbaru.model.RoleFunction;

@Repository
@SuppressWarnings("unchecked")
public class RoleDAOImpl implements RoleDAO {
	private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public RoleFunction getRoleFunctionByUserId(int userId) {
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
			"INNER JOIN tb_role_function rolefunc ON role.role_id = rolefunc.role_id     " +
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

	@Override
	public RoleFunction getRoleFunctionById(int roleId) {
		RoleFunction result = new RoleFunction();
		String sqlquery = 
			"SELECT	role.role_id,                                                       " +
			"		role.name,                                                          " +
			"       func.function_id,                                                   " +
			"       func.function_code,                                                 " +
			"       func.module_icon,                                                   " +
			"		func.module,                                                        " +
			"       func.menu_icon,                                                     " +
			"       func.menu_name,                                                     " +
			"       func.url,                                                           " +
			"       func.order_num,                                                     " +	
			"       func.deep_level                                                     " +
			"FROM tb_role role                                                          " +
			"INNER JOIN tb_role_function rolefunc ON role.role_id = rolefunc.role_id    " +
			"INNER JOIN tb_function func ON rolefunc.function_id  = func.function_id    " +
			"WHERE role.role_id = ?                                                     " +
			"ORDER BY func.order_num ASC                                                " ;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery, new Object[] { roleId });
	
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
	public List<Role> getAllRole() {
		logger.info("[getAllRole] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = session.createQuery("FROM Role").list();
	
		for(Role role:roleList) {
			logger.info("Role : " + role.toString());
		}
		
		return roleList;
	}

	@Override
	public Role getRoleById(int selectedId) {
		logger.info("[getRoleById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Role role = null;
        
        try {
        	role = (Role) session.load(Role.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Role loaded successfully, Role details = " + role.toString());
        
        return role;	
	}

	@Override
	public void addRole(Role role) {
		logger.info("[addRole] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(role);
        
        logger.info("Role added successfully, Customer Details = " + role.toString());		
	}

	@Override
	public void editRole(Role role) {
		logger.info("[editRole] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(role);
	    
	    logger.info("Role updated successfully, Role Details = " + role.toString());			
	}

	@Override
	public void deleteRole(int selectedId) {
		logger.info("[deleteRole] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class, new Integer(selectedId));
        if(null != role){
            session.delete(role);
        }
        
        logger.info("Role deleted successfully, Role details = " + role.toString());	
	}
	
}
