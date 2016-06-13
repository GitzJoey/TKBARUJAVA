package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StocksInDAO;
import com.tkbaru.model.StocksIn;

@Service
public class StocksInServiceImpl implements StocksInService {

	@Autowired
	StocksInDAO stocksInDAO;
	
	@Override
	@Transactional
	public List<StocksIn> getAllStocksIn() {
		return stocksInDAO.getAllStocksIn();
		
	}

	@Override
	@Transactional
	public void addOrCreateStocksIn(StocksIn stocksIn) {

		stocksInDAO.addStocksIn(stocksIn);
	}

	@Override
	@Transactional
	public void updateStocksIn(StocksIn stocksIn) {

		stocksInDAO.updateStocksIn(stocksIn);
	}

	@Override
	@Transactional
	public long countStocksInByProductId(int productId) {

		return stocksInDAO.countStocksInByProductId(productId);
	}

	@Override
	@Transactional
	public List<StocksIn> getAllStocksInByWarehouseId(int warehouseId) {

		return stocksInDAO.getAllStocksInByWarehouseId(warehouseId);
	}

}
