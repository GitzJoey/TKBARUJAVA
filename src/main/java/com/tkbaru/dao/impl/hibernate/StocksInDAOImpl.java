package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.StocksInDAO;
import com.tkbaru.model.StocksIn;

@Repository
@SuppressWarnings("unchecked")
public class StocksInDAOImpl implements StocksInDAO {
	private static final Logger logger = LoggerFactory.getLogger(StocksInDAOImpl.class);
	
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<StocksIn> getAllStocksIn() {
		logger.info("[getAllStocksIn] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<StocksIn> stocksList = session.createQuery("FROM StocksIn").list();
	
		logger.info("[getAllStocksIn] " + "StocksIn retrieved: " + stocksList.size());
		
		return stocksList;
	}

	@Override
	public void addStocksIn(StocksIn stocksIn) {
		logger.info("[addStocksIn] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
	    session.persist(stocksIn);
	        
        logger.info("[addStocksIn] " + "Stock added successfully, Stock Details = " + stocksIn.toString());		
		
		
	}

	@Override
	public void updateStocksIn(StocksIn stocksIn) {
		logger.info("[updateStocksIn] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
	    session.merge(stocksIn);
	        
	    logger.info("[updateStocksIn] " + "Stock update successfully, Stock Details = " + stocksIn.toString());
		
	}

	@Override
	public long countStocksInByProductId(int productId) {
		Session session = this.sessionFactory.getCurrentSession();
		long qty =(long) session.createQuery("select SUM(s.prodQuantity) FROM StocksIn s where s.productId= :productId").setInteger("productId", productId).uniqueResult();
		return qty;
	}

	@Override
	public List<StocksIn> getAllStocksInByWarehouseId(int warehouseId) {

		return null;
	}

}
