package com.tkbaru.dao;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

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
	@SuppressWarnings("deprecation")
	public boolean checkDBConnection() {
	    String CHECK_SQL_QUERY = "SELECT 1";
	    
	    boolean isConnected = false;
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    
	    try {
	        jdbcTemplate.queryForInt(CHECK_SQL_QUERY);
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
