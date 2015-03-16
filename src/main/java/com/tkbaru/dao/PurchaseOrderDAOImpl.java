package com.tkbaru.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

}
