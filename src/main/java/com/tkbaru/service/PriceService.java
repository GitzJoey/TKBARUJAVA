package com.tkbaru.service;

import java.util.Date;
import java.util.List;

import com.tkbaru.model.Price;

public interface PriceService {
	public boolean checkExistPriceForDate(Date inputDate);
	public void addPrice(Price price);
	public void addMultiplePrice(List<Price> priceList);
	public List<Price> getLatestRetailPrice();
	public Price getLatestRetailPriceByProductId(int productId);
}
