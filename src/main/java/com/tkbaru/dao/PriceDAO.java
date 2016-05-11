package com.tkbaru.dao;

import java.util.Date;
import java.util.List;

import com.tkbaru.model.Price;

public interface PriceDAO {
	public List<Price> getAllPriceForDate(Date inputDate);
	public void addPrice(Price price);
	
	public List<Price> getLatestRetailPrice(int stocksId, int priceLevelId);
}
