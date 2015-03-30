package com.tkbaru.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkbaru.model.Stocks;

public class StocksDAOImpl implements StocksDAO {
	private static final Logger logger = LoggerFactory.getLogger(StocksDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<Stocks> getAllStocks() {
		logger.info("[getAllStocks" + "");
		
		return null;
	}

}
