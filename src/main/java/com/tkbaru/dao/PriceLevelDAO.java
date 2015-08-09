
package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.PriceLevel;

public interface PriceLevelDAO {
	
	public PriceLevel loadPriceLevelById(Integer priceLevelId) ;
	public List<PriceLevel> loadAllPriceLevel() ;
	public void addPriceLevel(PriceLevel priceLevel) ;
	public void editPriceLevel(PriceLevel priceLevel) ;
	public void deletePriceLevel(Integer priceLevelId) ;
}
