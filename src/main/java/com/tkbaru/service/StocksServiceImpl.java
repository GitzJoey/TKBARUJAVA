package com.tkbaru.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StocksDAO;
import com.tkbaru.model.Stocks;
import com.tkbaru.model.StocksOut;

@Service
public class StocksServiceImpl implements StocksService {
	private static final Logger logger = LoggerFactory.getLogger(StocksServiceImpl.class);
	
	@Autowired
	StocksDAO stocksDAO;
	
	@Autowired
	StocksOutService stocksOutManager;
	
	@Autowired
	StoreService storeManager;
	
	@Autowired
	ProductService productManager;
	
	@Autowired
	WarehouseService warehouseManager;
	
	@Override
	@Transactional
	public List<Stocks> getAllStocks() {
		
		return stocksDAO.getAllStocks();
	}

	@Override
	@Transactional
	public void addOrCreateStocks(Stocks stocks) {
		
		stocksDAO.addStocks(stocks);
	}

	@Override
	@Transactional
	public void updateStocks(Stocks stocks) {
		
		stocksDAO.updateStocks(stocks);
	}

	@Override
	@Transactional
	public long countStocksByProductId(int productId) {
		
		return stocksDAO.countStocksByProductId(productId);
	}

	@Override
	@Transactional
	public List<Stocks> getAllStocksByWarehouseId(int warehouseId) {
		
		return stocksDAO.getAllStocksByWarehouseId(warehouseId);
	}

	@Override
	@Transactional
	public long findStockByProductIdAndByWarehouseId(int productId, int warehouseId) {
		
		return stocksDAO.findStockByProductIdAndByWarehouseId(productId, warehouseId);
	}

	@Override
	@Transactional
	public Stocks getStocksById(int selectedId) {
		
		return stocksDAO.getStocksById(selectedId);
	}

	@Override
	@Transactional
	public void updateStocks(List<Stocks> stocks) {
		
		for (Stocks s:stocks) {
			stocksDAO.updateStocks(s);
		}		
	}

	@Override
	@Transactional
	public long getQuantityByStocksId(int stocksId, int warehouseId) {

		return stocksDAO.getQuantityByStocksId(stocksId, warehouseId);
	}

	@Override
	@Transactional
	public void mergeStocks(int stocksId_From, int stocksId_To, int warehouseId_From, int warehouseId_To, int userId, int storeId) {
		logger.info("[mergeStocks] " + "stocksId_From: " + stocksId_From + 
										", stocksId_To: " + stocksId_To + 
										", warehouseId_From: " + warehouseId_From +
										", warehouseId_To: " + warehouseId_To +
										", userId: " + userId +
										", storeId: " + storeId);
		
		long qty = stocksDAO.getQuantityByStocksId(stocksId_From, warehouseId_From);
		
		logger.info("[mergeStocks] " + "stocksId_From: " + stocksId_From + ", warehouseId_From: " +  warehouseId_From + ", prodQuantity: " + qty);
		
		Stocks sFrom = stocksDAO.getStocksById(stocksId_From);
		sFrom.setCurrentQuantity(new Long(0));
		sFrom.setUpdatedBy(userId);
		sFrom.setUpdatedDate(new Date());
		
		StocksOut so = new StocksOut();
		so.setCreatedBy(userId);
		so.setCreatedDate(new Date());
		so.setProdQuantity(qty);
		so.setSalesOrderEntity(null);
		so.setSalesStoreEntity(storeManager.getStoreById(storeId));
		so.setStocksEntity(stocksDAO.getStocksById(stocksId_From));
		so.setProductEntity(productManager.getProductById(sFrom.getProductEntity().getProductId()));
		so.setWarehouseEntity(warehouseManager.getWarehouseById(warehouseId_From));
		
		stocksOutManager.addOrCreateStocksOut(so);
		stocksDAO.updateStocks(sFrom);
		
		logger.info("[mergeStocks] " + "stocksId_From: " + stocksId_From + ", warehouseId_From: " + warehouseId_From + " successfully zeroed");
		
		Stocks sTo = stocksDAO.getStocksById(stocksId_To);
		long prev = sTo.getCurrentQuantity();
		sTo.setCurrentQuantity(sTo.getCurrentQuantity() + qty);
		sTo.setUpdatedBy(userId);
		sTo.setUpdatedDate(new Date());

		stocksDAO.updateStocks(sTo);
		logger.info("[mergeStocks] " + "stocksId_To: " + stocksId_To + ", warehouseId_To: " + warehouseId_To + " successfully added. Previous: " + prev + ", Current: " + sTo.getCurrentQuantity());
	}
}
