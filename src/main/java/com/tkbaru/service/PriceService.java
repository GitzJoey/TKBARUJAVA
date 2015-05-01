package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Price;

public interface PriceService {
	public List<Price> getAllPrice();
	public Price getPriceById(int selectedId);
	public void addPrice(Price price);
	public void editPrice(Price price);
	public void deletePrice(int selectedId);
}
