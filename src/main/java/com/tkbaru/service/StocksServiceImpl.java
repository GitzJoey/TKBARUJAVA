package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StocksDAO;
import com.tkbaru.model.Stocks;

@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	StocksDAO stocksDAO;
	
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

}
