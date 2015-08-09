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
	public PriceLevel loadPriceLevelById(Integer priceLevelId) {
		logger.info("[loadPriceLevelById] " + "");
        
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
	public List<PriceLevel> loadAllPriceLevel() {
		logger.info("[loadAllPriceLevel] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<PriceLevel> priceLevelList = session.createQuery("FROM PriceLevel").list();
	
		logger.info("PriceLevel Size: " + priceLevelList.size());
		
		return priceLevelList;
	}
	@Override
	public void addPriceLevel(PriceLevel priceLevel) {
		logger.info("[addPriceLevel] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();

        session.persist(priceLevel);
        
        logger.info("Price Level added successfully, Price Level Details = " + priceLevel.toString());
	}

	@Override
	public void editPriceLevel(PriceLevel priceLevel) {
		logger.info("[editPriceLevel] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    
		session.update(priceLevel);
		
	    logger.info("Price Level updated successfully, Price Level Details = " + priceLevel.toString());
	}
	
	@Override
	public void deletePriceLevel(Integer priceLevelId) {
		logger.info("[deletePriceLevel] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        PriceLevel priceLevel = (PriceLevel) session.load(PriceLevel.class, new Integer(priceLevelId));
        
        if(null != priceLevel){
            session.delete(priceLevel);
        }
        
        logger.info("Price Level deleted successfully, Price Level details = " + priceLevel.toString());
	}

}
