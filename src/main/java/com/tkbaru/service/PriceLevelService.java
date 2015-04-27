package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.PriceLevel;

public interface PriceLevelService {
	public List<PriceLevel> getAllPriceLevel();
	public PriceLevel getPriceLevelById(int selectedId);
	public void addPriceLevel(PriceLevel product);
	public void editPriceLevel(PriceLevel product);
	public void deletePriceLevel(int selectedId);

}
