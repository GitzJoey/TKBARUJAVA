package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Price;

@Repository
@SuppressWarnings("unchecked")
public class PriceDAOImpl implements PriceDAO {
	private static final Logger logger = LoggerFactory.getLogger(PriceDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public Price load(Integer priceId) {
		logger.info("[getPriceById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Price price = null;
        
        try {
        	price = (Price) session.load(Price.class, new Integer(priceId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Price loaded successfully, Price details = " + price.toString());
                
        return price;	
	}
	@Override
	public List<Price> loadAll() {
		logger.info("[getAllPrice] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<Price> priceList = session.createQuery("FROM Price").list();
	
		for(Price price:priceList) {
			logger.info("Price : " + price.toString());
		}
		
		return priceList;
	}
	@Override
	public void addPrice(Price price) {
		logger.info("[addPrice] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(price);		
	}

	@Override
	public void editPrice(Price price) {
		logger.info("[editPrice] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(price);		
	}
	@Override
	public void delete(Integer priceId) {
		logger.info("[delete] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Price price = (Price) session.load(Price.class, new Integer(priceId));
        if(null != price){
            session.delete(price);
        }		
		
	}
}
