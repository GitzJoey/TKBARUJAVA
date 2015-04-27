package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Price;

public interface PriceDAO {
	public Price load(Integer priceId) ;
	public List<Price> loadAll() ;
	public void addPrice(Price price) ;
	public void editPrice(Price price) ;
	public void delete(Integer priceId) ;
}
