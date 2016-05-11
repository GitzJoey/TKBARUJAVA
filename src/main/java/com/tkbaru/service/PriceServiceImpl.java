package com.tkbaru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.PriceDAO;
import com.tkbaru.model.Price;
import com.tkbaru.model.Stocks;

@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	PriceDAO priceDAO;

	@Autowired
	StocksService stocksManager;
	
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
	public List<Price> getLatestRetailPrice(int stocksId, int priceLevelId) {
		List<Price> perStock = getLatestRetailPrice(stocksId, priceLevelId);

		return perStock;
	}

	@Override
	@Transactional
	public List<Price> getLatestRetailPrice(int priceLevelId) {
		List<Price> result = new ArrayList<Price>();
		
		List<Stocks> stocksList = stocksManager.getAllStocks();		
		for (Stocks s:stocksList) {
			List<Price> perStock = getLatestRetailPrice(s.getStocksId(), priceLevelId);
			if (perStock.size() > 0) {
				result.addAll(perStock);
			}
		}

		return result;
	}

}
