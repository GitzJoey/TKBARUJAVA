package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Person;
import com.tkbaru.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    private BasicDataSource dbcpDataSource;
    public void setDbcpDataSource(BasicDataSource dbcpDataSource) {
    	this.dbcpDataSource = dbcpDataSource;
    }

	public User getUser(String userName) {
		User result = null;
		String sqlquery = "SELECT * FROM tb_user WHERE UCASE(user_name) = UCASE('" + userName + "')";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> usr = jdbcTemplate.query(sqlquery, new UserRowMapper());
		
		if (usr.size() == 0) return result;
		else result = usr.get(0);
		
		return result;
	}

	@Override
	public List<User> getAllUser() {
		List<User> result = new ArrayList<User>();
		String sqlquery = 
				"SELECT tbuser.user_id,                                                    "+
				"		tbuser.user_name,                                                  "+
				"        tbuser.role_id,                                                   "+
				"        tbrole.name,                                                      "+
				"        tbperson.first_name,                                              "+
				"        tbperson.last_name,                                               "+
				"        tbperson.addr_1,                                                  "+
				"        tbperson.addr_2,                                                  "+
				"        tbperson.addr_3                                                   "+
				"FROM tb_user tbuser                                                       "+
				"INNER JOIN tb_person tbperson ON tbuser.person_id = tbperson.person_id    "+
				"INNER JOIN tb_role tbrole ON tbuser.role_id = tbrole.role_id              ";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
	
		for (Map<String, Object> row : rows) {
			User res = new User();
			res.setUserId(Integer.valueOf(String.valueOf(row.get("user_id"))));
			res.setUserName(String.valueOf(row.get("user_name")));
			
			Person p = new Person();
			p.setFirstName(String.valueOf(row.get("first_name")));
			p.setLastName(String.valueOf(row.get("last_name")));
			p.setAddressLine1(String.valueOf(row.get("addr_1")));
			p.setAddressLine2(String.valueOf(row.get("addr_2")));
			p.setAddressLine3(String.valueOf(row.get("addr_3")));
			
			res.setPersonEntity(p);
			result.add(res);
		}
		
		return result;
	}

	@Override
	public User getUserById(int selectedId) {
		User result = new User();
		
		String sqlquery = 
				"SELECT tbuser.user_id,                                                    "+
				"		tbuser.user_name,                                                  "+
				"        tbuser.role_id,                                                   "+
				"        tbuser.person_id                                                  "+
				"FROM tb_user tbuser                                                       "+
				"WHERE tbuser.user_id = ? ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { selectedId }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User usr = new User();
				
				usr.setUserId(rs.getInt("user_id"));
				usr.setUserName(rs.getString("user_name"));
				usr.setRoleId(rs.getInt("role_id"));
				usr.setPersonId(rs.getInt("person_id"));
				
				return usr;
			}
		});
				
		return result;
	}

	@Override
	public void addUser(User usr) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(usr);
        logger.info("User added successfully, User Details = " + usr.toString());		
	}

	@Override
	public void editUser(User usr) {
		// TODO Auto-generated method stub
		
	}
}
