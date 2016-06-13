package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.StocksIn;

public interface StocksInDAO {
	public List<StocksIn> getAllStocksIn();
	void addStocksIn(StocksIn stocksIn);
	void updateStocksIn(StocksIn stocksIn);
	long countStocksInByProductId(int productId);
	
	public List<StocksIn> getAllStocksInByWarehouseId(int warehouseId);
}