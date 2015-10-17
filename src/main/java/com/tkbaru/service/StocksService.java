package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Stocks;

public interface StocksService {
	public List<Stocks> getAllStocks();
	public void addOrCreateStocks(Stocks stocks);
	public void updateStocks(Stocks stocks);
	public void updateStocks(List<Stocks> stocks);
	long countStocksByProductId(int productId);
	long findStockByProductIdAndByWarehouseId(int productId, int warehouseId);
	public List<Stocks> getAllStocksByWarehouseId(int warehouseId);
	public Stocks getStocksById(int selectedId);
}
