package com.tkbaru.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StocksDAOImpl implements StocksDAO {
	private static final Logger logger = LoggerFactory.getLogger(StocksDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

}
