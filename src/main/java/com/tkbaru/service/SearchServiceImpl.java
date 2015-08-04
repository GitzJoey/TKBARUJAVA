package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.model.Customer;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	CustomerService customerManager;
	
	@Override
	public List<Customer> searchCustomer(String searchQuery) {
		List<Customer> c = customerManager.getAllCustomer();
		
		return c;
	}

}
