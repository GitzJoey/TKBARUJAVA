package com.tkbaru.service;

import java.util.Date;

import com.tkbaru.model.Price;

public interface PriceService {
	public boolean checkExistPriceForDate(Date inputDate);
	public void addPrice(Price price);
}
