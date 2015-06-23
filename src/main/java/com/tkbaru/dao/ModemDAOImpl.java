package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Modem;

@Repository
@SuppressWarnings("unchecked")
public class ModemDAOImpl implements ModemDAO {
	private static final Logger logger = LoggerFactory.getLogger(ModemDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
	public List<Modem> getAllModem() {
		logger.info("[getAllModem] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<Modem> modemList = session.createQuery("FROM Modem").list();
	
		for(Modem modem:modemList) {
			logger.info("Modem : " + modem.toString());
		}
		
		return modemList;
	}

	@Override
	public Modem getModemById(int selectedId) {
		logger.info("[getModemById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Modem modem = null;
        
        try {
        	modem = (Modem) session.load(Modem.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Modem loaded successfully, Modem details = " + modem.toString());
                
        return modem;	
	}

	@Override
	public void addModem(Modem modem) {
		logger.info("[addModem] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(modem);		
	}

	@Override
	public void editModem(Modem modem) {
		logger.info("[editModem] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(modem);		
	}

	@Override
	public void deleteModem(int selectedId) {
		logger.info("[deleteModem] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Modem modem = (Modem) session.load(Modem.class, new Integer(selectedId));
        if(null != modem){
            session.delete(modem);
        }		
	}

}
