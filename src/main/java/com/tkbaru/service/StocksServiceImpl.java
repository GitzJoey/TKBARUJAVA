package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.dao.StocksDAO;
import com.tkbaru.model.Stocks;

@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	StocksDAO stocksDAO;
	
	@Override
	public List<Stocks> getAllStocks() {
		
		return null;
	}

}
