package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.StocksOut;

public interface StocksOutService {
	public List<StocksOut> getAllStocksOut();
	public void addOrCreateStocksOut(StocksOut stocksOut);
	public void updateStocksOut(StocksOut stocksOut);
	long countStocksOutByProductId(int productId);
	
	public List<StocksOut> getAllStocksOutByWarehouseId(int warehouseId);
}
