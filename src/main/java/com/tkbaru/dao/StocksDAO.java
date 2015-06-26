package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Stocks;

public interface StocksDAO {
	public List<Stocks> getAllStocks();
	void addStocks(Stocks stocks);
	void updateStocks(Stocks stocks);
	long countStocksByProductId(int productId);
	long findStockByProductIdAndByWarehouseId(int productId, int warehouseId);
	public List<Stocks> getAllStocksByWarehouseId(int warehouseId);
}
