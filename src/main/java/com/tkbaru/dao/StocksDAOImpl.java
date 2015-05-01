package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Stocks;

@Repository
@SuppressWarnings("unchecked")
public class StocksDAOImpl implements StocksDAO {
	private static final Logger logger = LoggerFactory.getLogger(StocksDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<Stocks> getAllStocks() {
		logger.info("[getAllStocks]" + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Stocks> stocksList = session.createQuery("FROM Stocks").list();
	
		for(Stocks stock:stocksList) {
			logger.info("Stocks	 : " + stock.toString());
		}
		
		return stocksList;
	}

	@Override
	public void addStocks(Stocks stocks) {
		logger.info("[addStocks" + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.persist(stocks);
	        
        logger.info("Stock added successfully, Stock Details = " + stocks.toString());		
		
	}

	@Override
	public void updateStocks(Stocks stocks) {
		logger.info("[updateStocks" + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.merge(stocks);
	        
	     logger.info("Stock update successfully, Stock Details = " + stocks.toString());
		
	}

	@Override
	public long countStocksByProductId(int productId) {
		Session session = this.sessionFactory.getCurrentSession();
		long qty =(long) session.createQuery("select SUM(s.prodQuantity) FROM Stocks s where s.productId= :productId").setInteger("productId", productId).uniqueResult();
		return qty;
	}

}
