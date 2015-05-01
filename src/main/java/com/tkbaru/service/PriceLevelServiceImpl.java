package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.PriceLevelDAO;
import com.tkbaru.model.PriceLevel;

@Service
public class PriceLevelServiceImpl implements PriceLevelService {

	@Autowired
	PriceLevelDAO priceLevelDAO;

	
	@Override
	@Transactional
	public List<PriceLevel> getAllPriceLevel() {
		
		return priceLevelDAO.loadAll();
	}

	@Override
	@Transactional
	public PriceLevel getPriceLevelById(int selectedId) {
		
		return priceLevelDAO.load(selectedId);
	}

	@Override
	@Transactional
	public void addPriceLevel(PriceLevel priceLevel) {
		priceLevelDAO.addPriceLevel(priceLevel);
		
	}

	@Override
	@Transactional
	public void editPriceLevel(PriceLevel priceLevel) {
		
		priceLevelDAO.editPriceLevel(priceLevel);
	}

	@Override
	@Transactional
	public void deletePriceLevel(int selectedId) {

		priceLevelDAO.delete(selectedId);		
	}

}
