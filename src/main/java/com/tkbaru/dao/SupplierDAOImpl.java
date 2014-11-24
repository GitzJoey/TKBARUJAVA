package com.tkbaru.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.model.Supplier;

public class SupplierDAOImpl implements SupplierDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Supplier getSupplierById(int selectedId) {
		Supplier result = new Supplier();
		
		String sqlquery = "";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery, new Object[] { selectedId });

		return result;
	}

	@Override
	public void addNewSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		
	}

}
