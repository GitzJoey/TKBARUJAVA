package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StocksDAO;
import com.tkbaru.model.Stocks;

@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	StocksDAO stocksDAO;
	
	@Override
	@Transactional
	public List<Stocks> getAllStocks() {
		
		return null;
	}

	@Override
	@Transactional
	public void addOrCreateStocks(int productId, long qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void updateStocks(int productId, long qty) {
		// TODO Auto-generated method stub
		
	}

}
