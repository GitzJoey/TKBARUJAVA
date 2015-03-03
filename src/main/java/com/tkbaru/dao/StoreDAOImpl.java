package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Store;

@Repository
@SuppressWarnings("unchecked")
public class StoreDAOImpl implements StoreDAO {
	private static final Logger logger = LoggerFactory.getLogger(StoreDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    	
	@Override
	public List<Store> getAllStore() {
		logger.info("[getAllStore] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Store> storeList = session.createQuery("FROM Store").list();
	
		for(Store s:storeList) {
			logger.info("Store : " + s.toString());
		}
		
		return storeList;
	}

	@Override
	public Store getStoreById(int selectedId) {
		logger.info("[getStoreById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
        Store s = null;
        
        try {
        	s = (Store) session.load(Store.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Store loaded successfully, Store details = " + s.toString());
        
        return s;
	}
	
	@Override
	public void addStore(Store store) {
		logger.info("[addStore] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(store);
        
        logger.info("Store added successfully, Store Details = " + store.toString());		
	}
	
	@Override
	public void editStore(Store store) {
		logger.info("[editStore] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(store);
	    
	    logger.info("Store updated successfully, Store Details = " + store.toString());			
	}

	@Override
	public void deleteStore(int selectedId) {
		logger.info("[deleteStore] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Store store = (Store) session.load(Store.class, new Integer(selectedId));
        if(null != store){
            session.delete(store);
        }
        
        logger.info("Store deleted successfully, Store details = " + store.toString());
	}

	@Override
	public Store getDefaultStore() {
		logger.info("[getDefaultStore] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		Query q = session.createQuery("FROM Store S WHERE S.isDefault = :isdef");
		q.setParameter("isdef", "L003_YES");
		
		Store s = (Store)q.uniqueResult();

		if (s == null) {
			logger.info("Default Store Data : " + "null");
		} else {
			logger.info("Default Store Data : " + s.toString());
		}
		
		return s;
	}

	@Override
	public void batchEditStore(List<Store> stores) {
		logger.info("[batchEditStore] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    for (Store s:stores) {
	    	session.update(s);
	    }
	    
	    logger.info("Batch Edit Store updated successfully");					
	}

}
