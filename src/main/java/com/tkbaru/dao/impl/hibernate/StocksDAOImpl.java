package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.StocksDAO;
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
		logger.info("[getAllStocks] " + "");
		
		List<Stocks> stocksList = null;
		Session session = this.sessionFactory.getCurrentSession();
		stocksList = session.createQuery("FROM Stocks").list();
	
		if (stocksList != null) {
			logger.info("[getAllStocks] " + "Stocks retrieved: " + stocksList.size());
		} else {
			logger.info("[getAllStocks] " + "Stocks retrieved: " + "null");
		}
		
		return stocksList;
	}

	@Override
	public void addStocks(Stocks stocks) {
		logger.info("[addStocks] " + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.persist(stocks);
	        
        logger.info("[addStocks] " + "Stock added successfully, Stock Details = " + stocks.toString());		
		
	}

	@Override
	public void updateStocks(Stocks stocks) {
		logger.info("[updateStocks] " + "");
		
		 Session session = this.sessionFactory.getCurrentSession();
		
	     session.merge(stocks);
	        
	     logger.info("[updateStocks] " + "Stock update successfully, Stock Details = " + stocks.toString());
		
	}

	@Override
	public long countStocksByProductId(int productId) {
		logger.info("[countStocksByProductId] " + "productId: " + productId);
		Session session = this.sessionFactory.getCurrentSession();
		long qty =(long) session.createQuery("select SUM(s.prodQuantity) FROM Stocks s where s.productId= :productId").setInteger("productId", productId).uniqueResult();
		return qty;
	}

	@Override
	public List<Stocks> getAllStocksByWarehouseId(int warehouseId) {
		logger.info("[getAllStocksByWarehouseId] " + "warehouseId: " + warehouseId);
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Stocks> stocksList = session.createQuery(
				"SELECT s FROM Stocks s " +
				"INNER JOIN s.poLookup " +
				"WHERE s.poLookup.warehouseId = :whId").setInteger("whId", warehouseId).list();
	
		logger.info("[getAllStocksByWarehouseId] " + "Stocks in warehouseId " + warehouseId + ": " + stocksList.size());
		
		return stocksList;
	}

	@Override
	public long findStockByProductIdAndByWarehouseId(int productId, int warehouseId) {
		logger.info("[findStockByProductIdAndByWarehouseId] " + "productId: " +  productId + ", warehouseId: " + warehouseId);
		
		Session session = this.sessionFactory.getCurrentSession();
		long qty =(long) session.createQuery("select SUM(s.prodQuantity) FROM Stocks s where s.productId= :productId and s.warehouseId = :warehouseId").setInteger("productId", productId).setInteger("warehouseId", warehouseId).uniqueResult();
		return qty;
	}

	@Override
	public Stocks getStocksById(int selectedId) {
		logger.info("[getStocksById] " + "selectedId: " + selectedId);

		Session session = this.sessionFactory.getCurrentSession();
		Stocks s = null;
        
        try {
        	s = (Stocks) session.load(Stocks.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("[getStocksById] " + "Stocks loaded successfully, Stocks details = " + s.toString());

		return s;
	}

	@Override
	public long getQuantityByStocksId(int stocksId, int warehouseId) {
		logger.info("[getQuantityByStocksId] " + "stocksId: " +  stocksId + ", warehouseId: " + warehouseId);
		
		Session session = this.sessionFactory.getCurrentSession();
		long qty = (long)session.createQuery("select s.prodQuantity FROM Stocks s where s.stocksId= :stocksId and s.warehouseId = :warehouseId").setInteger("stocksId", stocksId).setInteger("warehouseId", warehouseId).uniqueResult();
		
		logger.info("[getQuantityByStocksId] " + "Quantity: " +  qty);
		return qty;
	}

}
