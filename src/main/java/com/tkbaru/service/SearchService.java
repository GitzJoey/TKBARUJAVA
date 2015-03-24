package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Customer;

public interface SearchService {
	public List<Customer> searchCustomer(String searchQuery);
}
