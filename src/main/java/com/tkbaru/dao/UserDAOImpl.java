package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.User;

@Repository
public class UserDAOImpl implements UserDAO {	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public User getUser(String userName) {
		logger.info("[getUser] " + "");
		
		User result = null;
		String sqlquery = "SELECT * FROM tb_user WHERE UCASE(user_name) = UCASE('" + userName + "')";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		result = jdbcTemplate.queryForObject(sqlquery, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				
				user.setUserId(Integer.valueOf(rs.getString("user_id")));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("passwd"));
				user.setRoleId(rs.getInt("role_id"));
				user.setPersonId(rs.getInt("person_id"));
				user.setUserStatus(rs.getString("status"));
				
				return user;
			}
		});
		
		return result;
	}

	@Override
	public List<User> getAllUser() {
		logger.info("[getAllUser] " + "");
		
		List<User> result = new ArrayList<User>();
		String sqlquery = 
				"SELECT tbuser.user_id,														"+
				"		tbuser.user_name,													"+
				"		tbuser.passwd,														"+
				"		tbuser.role_id,														"+
				"		tbuser.store_id,													"+
				"		tbuser.person_id,													"+
				"		tbuser.status														"+
				"FROM tb_user tbuser														"+
				"WHERE tbuser.status = 'L001_A'												";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
	
		for (Map<String, Object> row : rows) {
			User res = new User();
			res.setUserId(Integer.valueOf(String.valueOf(row.get("user_id"))));
			res.setUserName(String.valueOf(row.get("user_name")));
			res.setUserPassword(String.valueOf(row.get("passwd")));
			res.setRoleId(Integer.valueOf(String.valueOf(row.get("role_id"))));
			res.setStoreId(Integer.valueOf(String.valueOf(row.get("store_id"))));
			res.setPersonId(Integer.valueOf(String.valueOf(row.get("person_id"))));
			res.setUserStatus(String.valueOf(row.get("status")));
			
			result.add(res);
		}
		
		return result;
	}

	@Override
	public User getUserById(int selectedId) {
		logger.info("[getUserById] " + "");
		
		User result = new User();
		
		String sqlquery = 
				"SELECT tbuser.user_id,			"+
				"		tbuser.user_name,		"+
				"		tbuser.passwd,			"+
				"		tbuser.role_id,			"+
				"		tbuser.store_id,		"+
				"		tbuser.person_id,		"+
				"		tbuser.status			"+				
				"FROM tb_user tbuser            "+
				"WHERE tbuser.user_id = ? 		";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { selectedId }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User usr = new User();
				
				usr.setUserId(rs.getInt("user_id"));
				usr.setUserName(rs.getString("user_name"));
				usr.setUserPassword(rs.getString("passwd"));
				usr.setRoleId(rs.getInt("role_id"));
				usr.setStoreId(rs.getInt("store_id"));
				usr.setPersonId(rs.getInt("person_id"));
				usr.setUserStatus(rs.getString("status"));
				
				return usr;
			}
		});
				
		return result;
	}

	@Override
	public void addUser(User usr) {
		logger.info("[addUser] " + "");
		
        String sql = "INSERT INTO tb_user (user_name, passwd, role_id, person_id, store_id, status) " +
        				"VALUES (?, ?, ?, ?, ?, ?) ";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
	        jdbcTemplate.update(sql, new Object[] { usr.getUserName(), usr.getUserPassword(), usr.getRoleId(), usr.getPersonId(), usr.getStoreId(), usr.getUserStatus() });
        } catch(Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }
		
	}

	@Override
	public void editUser(User usr) {
		logger.info("[editUser] " + "");
		
        String query = "UPDATE tb_user SET user_name = ?, passwd = ?, role_id = ?, person_id = ?, store_id = ?, status = ? " +
        				"WHERE user_id = ? ";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        int out = 0;
        
        try {
            Object[] args = new Object[] { 
            		usr.getUserName(), usr.getUserPassword(), usr.getRoleId(), usr.getPersonId(), usr.getStoreId(), usr.getUserStatus(), usr.getUserId() };

            out = jdbcTemplate.update(query, args);        	
        } catch (Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }

        logger.info("User updated successfully, row updated : " + out);
	}

	@Override
	public void deleteUser(int selectedId) {
		logger.info("[deleteUser] " + "");
		
        String query = "DELETE FROM tb_user WHERE user_id = ? ";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        int out = 0;
        
        try {
        	out = jdbcTemplate.update(query, selectedId);
        } catch (Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }

        logger.info("User deleted successfully, row deleted : " + out);
	}
}
