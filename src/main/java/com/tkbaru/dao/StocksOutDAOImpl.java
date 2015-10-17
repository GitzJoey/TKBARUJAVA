package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.StocksOut;

@Repository
@SuppressWarnings("unchecked")
public class StocksOutDAOImpl implements StocksOutDAO {
	private static final Logger logger = LoggerFactory.getLogger(StocksOutDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<StocksOut> getAllStocksOut() {
		logger.info("[getAllStocksOut] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<StocksOut> stocksList = session.createQuery("FROM StocksOut").list();
	
		logger.info("StocksOut retrieved: " + stocksList.size());
		
		return stocksList;
	}

	@Override
	public void addStocksOut(StocksOut stocks) {
		logger.info("[addStocksOut] " + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.persist(stocks);
	        
        logger.info("Stock added successfully, Stock Details = " + stocks.toString());		
		
	}

	@Override
	public void updateStocksOut(StocksOut stocks) {
		logger.info("[updateStocksOut] " + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.merge(stocks);
	        
	     logger.info("Stock update successfully, Stock Details = " + stocks.toString());
		
	}

	@Override
	public long countStocksOutByProductId(int productId) {
		Session session = this.sessionFactory.getCurrentSession();
		long qty =(long) session.createQuery("select SUM(s.prodQuantity) FROM StocksOut s where s.productId= :productId").setInteger("productId", productId).uniqueResult();
		return qty;
	}

	@Override
	public List<StocksOut> getAllStocksOutByWarehouseId(int warehouseId) {
		logger.info("[getAllStocksByWarehouseId] " + "warehouseId: " + warehouseId);
		
		Session session = this.sessionFactory.getCurrentSession();
		List<StocksOut> stocksList = session.createQuery(
				"SELECT s FROM StocksOut s " +
				"INNER JOIN s.poLookup " +
				"WHERE s.poLookup.warehouseId = :whId").setInteger("whId", warehouseId).list();
	
		logger.info("StocksOut in warehouseId " + warehouseId + ": " + stocksList.size());
		
		return stocksList;
	}

}
