package com.tkbaru.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.CustomerDAO;
import com.tkbaru.model.Customer;

@Service
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

	@Override
	@Transactional
	public List<Customer> searchCustomer(String searchQuery) {
		return customerDAO.searchCustomer(searchQuery);
	}

	@Override
	public void generateDefaultCustomer() {
		Customer c = new Customer();
		
		c.setCustomerName("Walk In Customer");
		c.setCustomerAddress("");
		c.setCustomerCity("");
		c.setCustomerStatus("L001_A");
		c.setCreatedBy(0);
		c.setCreatedDate(new Date());
		
	}

}
