package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkbaru.dao.TruckVendorDAO;
import com.tkbaru.model.TruckVendor;

public class TruckVendorDAOImpl implements TruckVendorDAO {
	private static final Logger logger = LoggerFactory.getLogger(StoreDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<TruckVendor> getAllTruckVendor() {
		logger.info("[getAllTruckVendor] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<TruckVendor> TruckVendorList = session.createQuery("FROM TruckVendor").list();
	
		logger.info("TruckVendor Count: " + TruckVendorList.size());
		
		return TruckVendorList;
	}

	@Override
	public TruckVendor getTruckVendorById(int selectedId) {
		logger.info("[getTruckVendorById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		TruckVendor tv = null;
        
        try {
        	tv = (TruckVendor) session.load(TruckVendor.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("TruckVendor loaded successfully, TruckVendor details = " + tv.toString());
        
        return tv;
	}

	@Override
	public void addTruckVendor(TruckVendor truckVendor) {
		logger.info("[addTruckVendor] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        session.persist(truckVendor);
        
        logger.info("TruckVendor added successfully, TruckVendor Details = " + truckVendor.toString());	
	}

	@Override
	public void editTruckVendor(TruckVendor truckVendor) {
		logger.info("[editTruckVendor] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    
		session.update(truckVendor);
	    
	    logger.info("TruckVendor updated successfully, TruckVendor Details = " + truckVendor.toString());			
	}

	@Override
	public void deleteTruckVendor(int selectedId) {
		logger.info("[deleteTruckVendor] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        TruckVendor truckVendor = (TruckVendor) session.load(TruckVendor.class, new Integer(selectedId));
        
        if(null != truckVendor){
            session.delete(truckVendor);
        }
        
        logger.info("TruckVendor deleted successfully, TruckVendor details = " + truckVendor.toString());
	}

}
