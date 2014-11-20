package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tkbaru.model.Person;
import com.tkbaru.model.PhoneList;

public class PersonDAOImpl implements PersonDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Person getPersonEntityById(int personId) {
		Person result = new Person();
		
		String sqlquery = 
				"SELECT tbperson.person_id,           "+
				"		tbperson.first_name,          "+
				"       tbperson.last_name,           "+
				"       tbperson.addr_1,              "+
				"       tbperson.addr_2,              "+
				"       tbperson.addr_3,              "+
				"       tbperson.email,               "+
				"       tbperson.photo_path           "+
				"FROM tb_person tbperson              "+
				"WHERE tbperson.person_id = ?         ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { personId }, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person pers = new Person();
				
				pers.setPersonId(rs.getInt("person_id"));
				pers.setFirstName(rs.getString("first_name"));
				pers.setLastName(rs.getString("last_name"));
				pers.setAddressLine1(rs.getString("addr_1"));
				pers.setAddressLine2(rs.getString("addr_2"));
				pers.setAddressLine2(rs.getString("addr_3"));
				pers.setEmailAddr(rs.getString("email"));
				pers.setPhotoPath(rs.getString("photo_path"));
				
				return pers;
			}
		});
				
		return result;
	}

	@Override
	public List<PhoneList> getPhoneListById(int personId) {
		List<PhoneList> result = new ArrayList<PhoneList>();
		String sqlquery = 
				"SELECT tbpl.phonelist_id,       "+
				"		tbpl.provider,           "+
				"       tbpl.number,             "+
				"       tbpl.status              "+
				"FROM tb_phonelist tbpl          "+
				"WHERE tbpl.person_id = ?        ";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery, new Object[] { personId });

		for (Map<String, Object> row : rows) {
			PhoneList res = new PhoneList();
			
			res.setPhoneListId(Integer.valueOf(String.valueOf(row.get("phone_id"))));
			res.setProviderName(String.valueOf(row.get("provider")));
			res.setPhoneNumber(Integer.valueOf(String.valueOf(row.get("number"))));
			
			result.add(res);
		}

		return result;
	}

	@Override
	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

}
