package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.PriceDAO;
import com.tkbaru.model.Price;

@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	PriceDAO priceDAO;

	
	@Override
	@Transactional
	public List<Price> getAllPrice() {
		
		return priceDAO.loadAll();
	}

	@Override
	@Transactional
	public Price getPriceById(int selectedId) {
		
		return priceDAO.load(selectedId);
	}

	@Override
	@Transactional
	public void addPrice(Price price) {
		priceDAO.addPrice(price);
		
	}

	@Override
	@Transactional
	public void editPrice(Price price) {
		
		priceDAO.editPrice(price);
	}

	@Override
	@Transactional
	public void deletePrice(int selectedId) {

		priceDAO.delete(selectedId);		
	}

}
