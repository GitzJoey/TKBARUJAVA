package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Customer;

public interface SalesOrderService {
	public List<Customer> searchCustomer(String querySearch);
}
