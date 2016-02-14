package com.tkbaru.service;

import java.util.Date;
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
	public boolean checkExistPriceForDate(Date inputDate) {
		boolean exist = false;
		
		List<Price> p = priceDAO.getAllPriceForDate(inputDate);
		
		if (p.size() > 0) exist = true; 
		
		return exist;
	}

	@Override
	@Transactional
	public void addPrice(Price price) {
		
		priceDAO.addPrice(price);
	}

	@Override
	@Transactional
	public void addMultiplePrice(List<Price> priceList) {
		for (Price p:priceList) {
			priceDAO.addPrice(p);
		}
	}

	@Override
	@Transactional
	public Price getLatestRetailPriceByProductId(int productId) {
		
		return priceDAO.getLatestRetailPriceByProductId(productId);
	}

	@Override
	@Transactional
	public List<Price> getLatestRetailPrice() {
		
		return priceDAO.getLatestRetailPrice();
	}
	
}
