
package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.PriceLevel;

public interface PriceLevelDAO {
	
	public PriceLevel load(Integer priceLevelId) ;

	
	public List<PriceLevel> loadAll() ;

	
	public void addPriceLevel(PriceLevel priceLevel) ;
	
	public void editPriceLevel(PriceLevel priceLevel) ;
	
	public void delete(Integer priceLevelId) ;

	
	
}
