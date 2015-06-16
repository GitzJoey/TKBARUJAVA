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
	public List<SmsIn> getAllSmsInbox() {
		logger.info("[getAllSmsInbox] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<SmsIn> smsList = session.createQuery("FROM SmsInbox").list();
	
		for(SmsIn sms:smsList) {
			logger.info("SmsInbox : " + sms.toString());
		}
		
		return smsList;
	}

	@Override
	public SmsIn getSmsInboxById(int selectedId) {
		logger.info("[getSmsInboxById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		SmsIn sms = null;
        
        try {
        	sms = (SmsIn) session.load(SmsIn.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("SmsInbox loaded successfully, SmsInbox details = " + sms.toString());
                
        return sms;	
	}

	@Override
	public void addSmsInbox(SmsIn sms) {
		logger.info("[addSmsInbox] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(sms);		
	}

	@Override
	public void editSmsInbox(SmsIn sms) {
		logger.info("[editSmsInbox] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(sms);		
	}

	@Override
	public void deleteSmsInbox(int selectedId) {
		logger.info("[deleteSmsInbox] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        SmsIn sms = (SmsIn) session.load(SmsIn.class, new Integer(selectedId));
        if(null != sms){
            session.delete(sms);
        }		
	}

}
