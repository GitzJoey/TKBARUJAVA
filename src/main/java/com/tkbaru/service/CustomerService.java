package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int selectedId);
	public void addCustomer(Customer customer);
	public void editCustomer(Customer customer);
	public void deleteCustomer(int selectedId);

}
