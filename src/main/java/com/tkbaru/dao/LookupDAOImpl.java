package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.Lookup;

public class LookupDAOImpl implements LookupDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Lookup> getLookupByCategory(String categoryCode) {
		List<Lookup> result = new ArrayList<Lookup>();
		
		String sqlquery = "SELECT * FROM tb_lookup WHERE UCASE(category) = UCASE('" + categoryCode + "')";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
		
		for (Map<String, Object> row : rows) {
			Lookup res = new Lookup();
			res.setLookupId(Integer.valueOf(String.valueOf(row.get("lookup_id"))));
			res.setLookupCode(String.valueOf(row.get("category")));
			res.setShortVal(String.valueOf(row.get("short_val")));
			res.setLongVal(String.valueOf(row.get("long_val")));
			res.setLookupDescription(String.valueOf(row.get("description")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			res.setLookupStatus(String.valueOf(row.get("status")));
			res.setLookupMaintainability(String.valueOf(row.get("maintainable")));
			
			result.add(res);
		}		
		
		return result;
	}

}
