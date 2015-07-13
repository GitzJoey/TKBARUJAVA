package com.tkbaru.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.PriceLevel;


@Repository
@SuppressWarnings("unchecked")
public class PriceLevelDAOImpl implements PriceLevelDAO {
	private static final Logger logger = LoggerFactory.getLogger(PriceLevelDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public PriceLevel load(Integer priceLevelId) {
		logger.info("[getPriceLevelById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		PriceLevel priceLevel = null;
        
        try {
        	priceLevel = (PriceLevel) session.load(PriceLevel.class, new Integer(priceLevelId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("PriceLevel loaded successfully, PriceLevel details = " + priceLevel.toString());
                
        return priceLevel;	
	}
	@Override
	public List<PriceLevel> loadAll() {
		logger.info("[getAllPriceLevel] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<PriceLevel> priceLevelList = session.createQuery("FROM PriceLevel").list();
	
		for(PriceLevel priceLevel:priceLevelList) {
			logger.info("PriceLevel : " + priceLevel.toString());
		}
		
		return priceLevelList;
	}
	@Override
	public void addPriceLevel(PriceLevel priceLevel) {
		logger.info("[addPriceLevel] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();

        session.persist(priceLevel);		
	}

	@Override
	public void editPriceLevel(PriceLevel priceLevel) {
		logger.info("[editPriceLevel] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    
		session.update(priceLevel);		
	}
	
	@Override
	public void deletePriceLevel(Integer priceLevelId) {
		logger.info("[deletePriceLevel] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        PriceLevel priceLevel = (PriceLevel) session.load(PriceLevel.class, new Integer(priceLevelId));
        
        if(null != priceLevel){
            session.delete(priceLevel);
        }
	}

}
