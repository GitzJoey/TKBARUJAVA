package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;

import com.tkbaru.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> result = new ArrayList<Customer>();
		return result;
	}

	@Override
	public Customer getCustomerById(int selectedId) {
		Customer result = new Customer();
		return result;
	}

	@Override
	public void saveCustomer(Customer cust) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCustomer(Customer cust) {
		// TODO Auto-generated method stub
		
	}

}
