package com.tkbaru.model;

import java.util.List;

public class TodayPrice {
	public TodayPrice() {
		
	}
	
	public TodayPrice(List<Stocks> stocksList) {
		this.stocksList = stocksList;
	}
	
	private List<Stocks> stocksList;

	public List<Stocks> getStocksList() {
		return stocksList;
	}

	public void setStocksList(List<Stocks> stocksList) {
		this.stocksList = stocksList;
	}

	@Override
	public String toString() {
		return "TodayPrice [stocksList=" + stocksList + "]";
	}
		
}
