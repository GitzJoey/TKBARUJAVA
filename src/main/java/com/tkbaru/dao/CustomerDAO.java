package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Customer;

public interface CustomerDAO {
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int selectedId);
	public void addCustomer(Customer cust);
	public void editCustomer(Customer cust);
	public void deleteCustomer(int selectedId);
}
