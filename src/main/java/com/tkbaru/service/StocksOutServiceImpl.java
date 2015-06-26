package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StocksOutDAO;
import com.tkbaru.model.StocksOut;

@Service
public class StocksOutServiceImpl implements StocksOutService {

	@Autowired
	StocksOutDAO stocksOutDAO;
	
	@Override
	@Transactional
	public List<StocksOut> getAllStocksOut() {
		
		return stocksOutDAO.getAllStocksOut();
	}

	@Override
	@Transactional
	public void addOrCreateStocksOut(StocksOut stocks) {
		
		stocksOutDAO.addStocksOut(stocks);
	}

	@Override
	@Transactional
	public void updateStocksOut(StocksOut stocks) {
		
		stocksOutDAO.updateStocksOut(stocks);
	}

	@Override
	@Transactional
	public long countStocksOutByProductId(int productId) {
		
		return stocksOutDAO.countStocksOutByProductId(productId);
	}

	@Override
	@Transactional
	public List<StocksOut> getAllStocksOutByWarehouseId(int warehouseId) {
		
		return stocksOutDAO.getAllStocksOutByWarehouseId(warehouseId);
	}

}
