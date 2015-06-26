package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.StocksOut;

public interface StocksOutDAO {
	public List<StocksOut> getAllStocksOut();
	void addStocksOut(StocksOut stocksOut);
	void updateStocksOut(StocksOut stocksOut);
	long countStocksOutByProductId(int productId);
	
	public List<StocksOut> getAllStocksOutByWarehouseId(int warehouseId);
}
