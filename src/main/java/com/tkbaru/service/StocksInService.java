package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.StocksIn;

public interface StocksInService {
	public List<StocksIn> getAllStocksIn();
	public void addOrCreateStocksIn(StocksIn stocksIn);
	public void updateStocksIn(StocksIn stocksIn);
	long countStocksInByProductId(int productId);
	
	public List<StocksIn> getAllStocksInByWarehouseId(int warehouseId);
}
