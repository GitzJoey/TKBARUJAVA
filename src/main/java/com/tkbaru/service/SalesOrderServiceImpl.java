package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.model.Customer;
import com.tkbaru.model.SalesOrder;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	SearchService searchManager;
	
	@Override
	public List<Customer> searchCustomer(String querySearch) {
		
		return searchManager.searchCustomer(querySearch);
	}

	@Override
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
