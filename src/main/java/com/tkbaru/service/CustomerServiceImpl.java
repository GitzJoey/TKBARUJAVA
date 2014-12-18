package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.CustomerDAO;
import com.tkbaru.model.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomer() {
		
		return customerDAO.getAllCustomer();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int selectedId) {
		
		return customerDAO.getCustomerById(selectedId);
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {		
		
		customerDAO.addCustomer(customer); 
	}

	@Override
	@Transactional
	public void editCustomer(Customer customer) {

		customerDAO.editCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int selectedId) {

		customerDAO.deleteCustomer(selectedId);
	}

}
