package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Stocks;

public interface StocksService {
	public List<Stocks> getAllStocks();
	public void addOrCreateStocks(Stocks stocks);
	public void updateStocks(Stocks stocks);
	long countStocksByProductId(int productId);
	
	public List<Stocks> getAllStocksByWarehouseId(int warehouseId);
}
