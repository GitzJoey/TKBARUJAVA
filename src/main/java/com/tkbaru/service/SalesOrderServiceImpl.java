package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.model.Customer;

public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	SearchService searchManager;
	
	@Override
	public List<Customer> searchCustomer(String querySearch) {
		
		return searchManager.searchCustomer(querySearch);
	}

}
