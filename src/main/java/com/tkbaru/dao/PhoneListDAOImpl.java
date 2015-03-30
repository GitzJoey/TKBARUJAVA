package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.PhoneList;

public class PhoneListDAOImpl implements PhoneListDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
			
			res.setPhoneListId(Integer.valueOf(String.valueOf(row.get("phonelist_id"))));
			res.setProviderName(String.valueOf(row.get("provider")));
			res.setPhoneNumber(String.valueOf(row.get("number")));
			
			result.add(res);
		}

		return result;
	}

}
