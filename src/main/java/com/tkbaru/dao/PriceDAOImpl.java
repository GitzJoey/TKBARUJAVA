package com.tkbaru.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Price;

@Repository
public class PriceDAOImpl implements PriceDAO {
	private static final Logger logger = LoggerFactory.getLogger(PriceDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
}
