package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Stocks;

public interface StocksService {
	public List<Stocks> getAllStocks();
	public void addOrCreateStocks(int productId, long qty);
	public void updateStocks(int productId, long qty);
}
