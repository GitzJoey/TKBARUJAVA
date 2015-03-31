package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Lookup;

@Repository
@SuppressWarnings("unchecked")
public class LookupDAOImpl implements LookupDAO {
	private static final Logger logger = LoggerFactory.getLogger(LookupDAOImpl.class);
	
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	
	@Override
	public List<Lookup> getLookupByCategory(String categoryCode, String languageCode) {
		logger.info("[getLookupByCategory] " + "categoryCode: " + categoryCode);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Lookup WHERE lookupCategory = :cat");
		q.setParameter("cat", categoryCode);
		
		List<Lookup> result = q.list();
		
		for(Lookup l:result) {
			l.setLanguageCode(languageCode);
			logger.info("Lookup : " + l.toString());
		}		
		
		return result;
	}

	@Override
	public List<Lookup> getAllLookup() {
		logger.info("[getAllLookup] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();	
		List<Lookup> result = session.createQuery("FROM Lookup").list();
		
		for(Lookup l:result) {
			logger.info("Lookup : " + l.toString());
		}		
		
		return result;
	}

	@Override
	public Lookup getLookupById(int selectedId) {
		logger.info("[getLookupById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
        Lookup l = null;
        
        try {
        	l = (Lookup) session.load(Lookup.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Lookup loaded successfully, Lookup details = " + l.toString());
        
        return l;
	}

	@Override
	public List<Lookup> getAllCategory() {
		Session session = this.sessionFactory.getCurrentSession();
		
		 Criteria crit = (Criteria) session.createCriteria(Lookup.class);
		 crit.setProjection(Projections.distinct(Projections.property("lookupCategory")));

		 List<Lookup> result = new ArrayList<Lookup>();
		 
		 for (Object cat:crit.list()) {
			 Lookup l = new Lookup();
			 l.setLookupCategory(cat.toString());
			 
			 result.add(l);
		 }

		 return result;
	}

	@Override
	public void addLookup(Lookup lookup) {
		logger.info("[addLookup] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(lookup);
        
        logger.info("Lookup added successfully, Lookup Details = " + lookup.toString());		
	}

	@Override
	public void editLookup(Lookup lookup) {
		logger.info("[editLookup] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(lookup);
	    
	    logger.info("Lookup updated successfully, Lookup Details = " + lookup.toString());			
	}

	@Override
	public void deleteLookup(int selectedId) {
		logger.info("[deleteLookup] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Lookup l = (Lookup) session.load(Lookup.class, new Integer(selectedId));
        if(null != l){
            session.delete(l);
        }
        
        logger.info("Lookup deleted successfully, Lookup details = " + l.toString());	
	}

	@Override
	public List<Lookup> getLookupByCategories(String categoryCodes, String languageCode) {
		logger.info("[getLookupByCategories] " + "categoryCodes: " + categoryCodes);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Lookup WHERE lookupCategoy IN :cat");
		q.setParameter("cat", categoryCodes);
		
		List<Lookup> result = q.list();
		
		for(Lookup l:result) {
			l.setLanguageCode(languageCode);
			logger.info("Lookup : " + l.toString());
		}		
		
		return result;
	}

	@Override
	public List<Lookup> getLookupByLookupKeys(String lookupKeys, String languageCode) {
		logger.info("[getLookupByLookupKeys] " + "lookupKeys: " + lookupKeys);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Lookup WHERE lookupKey IN :keys");
		q.setParameter("keys", lookupKeys);
		
		List<Lookup> result = q.list();
		
		for(Lookup l:result) {
			l.setLanguageCode(languageCode);
			logger.info("Lookup : " + l.toString());
		}		
		
		return result;
	}

	@Override
	public Lookup getLookupByKey(String lookupKey, String languageCode) {
		logger.info("[getLookupByLookupKey] " + "lookupKey: " + lookupKey);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Lookup WHERE lookupKey = :key");
		q.setParameter("key", lookupKey);
		
		List<Lookup> result = q.list();
		
		if (result.size() == 0) return new Lookup();
		return result.get(0);
	}
		
}
