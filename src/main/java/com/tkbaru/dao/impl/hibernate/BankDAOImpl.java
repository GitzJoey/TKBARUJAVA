package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.BankDAO;
import com.tkbaru.model.Bank;

@Repository
public class BankDAOImpl implements BankDAO {
	private static final Logger logger = LoggerFactory.getLogger(BankDAOImpl.class);
	
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public void batchAddBank(List<Bank> bankList) {
		logger.info("[batchAddBank] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        for (Bank b:bankList) {
        	session.save(b);	
        }
        
        logger.info("Batch Bank added successfully");				
	}

}
