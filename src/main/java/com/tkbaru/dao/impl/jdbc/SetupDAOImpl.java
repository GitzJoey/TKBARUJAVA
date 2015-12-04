package com.tkbaru.dao.impl.jdbc;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tkbaru.dao.SetupDAO;

public class SetupDAOImpl implements SetupDAO {
	private static final Logger logger = LoggerFactory.getLogger(SetupDAOImpl.class);

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	
	@Override
	public boolean checkDBConnection() {
	    String CHECK_SQL_QUERY = "SELECT 1";
	    
	    boolean isConnected = false;
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    
	    try {
	        Integer i = jdbcTemplate.queryForObject(CHECK_SQL_QUERY, Integer.class);
	        isConnected = true;
	    } catch (Exception err) {
	        logger.info("[checkDBConnection] Connection failed. Reason: " + err.getMessage());
	    }
	    
	    return isConnected;	
	}

	@Override
	public boolean checkInitialDataIsValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
