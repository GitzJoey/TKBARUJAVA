package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.SmsOut;

@Repository
@SuppressWarnings("unchecked")
public class SmsOutDAOImpl implements SmsOutDAO {
	private static final Logger logger = LoggerFactory.getLogger(SmsOutDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
	public List<SmsOut> getAllSmsOutbox() {
		logger.info("[getAllSmsOutbox] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<SmsOut> productList = session.createQuery("FROM SmsOutbox").list();
	
		for(SmsOut prod:productList) {
			logger.info("SmsOutbox : " + prod.toString());
		}
		
		return productList;
	}

	@Override
	public SmsOut getSmsOutboxById(int selectedId) {
		logger.info("[getSmsOutboxById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		SmsOut prod = null;
        
        try {
        	prod = (SmsOut) session.load(SmsOut.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("SmsOutbox loaded successfully, SmsOutbox details = " + prod.toString());
                
        return prod;	
	}

	@Override
	public void addSmsOutbox(SmsOut prod) {
		logger.info("[addSmsOutbox] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(prod);		
	}

	@Override
	public void editSmsOutbox(SmsOut prod) {
		logger.info("[editSmsOutbox] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(prod);		
	}

	@Override
	public void deleteSmsOutbox(int selectedId) {
		logger.info("[deleteSmsOutbox] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        SmsOut product = (SmsOut) session.load(SmsOut.class, new Integer(selectedId));
        if(null != product){
            session.delete(product);
        }		
	}

}
