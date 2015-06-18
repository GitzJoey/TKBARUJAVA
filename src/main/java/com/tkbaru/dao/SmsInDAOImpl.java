package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.SmsIn;

@Repository
@SuppressWarnings("unchecked")
public class SmsInDAOImpl implements SmsInDAO {
	private static final Logger logger = LoggerFactory.getLogger(SmsInDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
	public List<SmsIn> getAllSmsIn() {
		logger.info("[getAllSmsIn] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<SmsIn> smsList = session.createQuery("FROM SmsIn").list();
	
		for(SmsIn sms:smsList) {
			logger.info("SmsIn : " + sms.toString());
		}
		
		return smsList;
	}

	@Override
	public SmsIn getSmsInById(int selectedId) {
		logger.info("[getSmsInById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		SmsIn sms = null;
        
        try {
        	sms = (SmsIn) session.load(SmsIn.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("SmsIn loaded successfully, SmsIn details = " + sms.toString());
                
        return sms;	
	}

	@Override
	public void addSmsIn(SmsIn sms) {
		logger.info("[addSmsIn] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(sms);		
	}

	@Override
	public void editSmsIn(SmsIn sms) {
		logger.info("[editSmsIn] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(sms);		
	}

	@Override
	public void deleteSmsIn(int selectedId) {
		logger.info("[deleteSmsIn] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        SmsIn sms = (SmsIn) session.load(SmsIn.class, new Integer(selectedId));
        if(null != sms){
            session.delete(sms);
        }		
	}

}
