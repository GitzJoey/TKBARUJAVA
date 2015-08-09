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
	
}
