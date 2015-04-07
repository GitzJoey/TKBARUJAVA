package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.SalesOrderDAO;
import com.tkbaru.model.Customer;
import com.tkbaru.model.SalesOrder;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	SearchService searchManager;
	
	@Autowired
	SalesOrderDAO salesOrderDAO; 
	
	@Override
	@Transactional
	public List<Customer> searchCustomer(String querySearch) {
		
		return searchManager.searchCustomer(querySearch);
	}

	@Override
	@Transactional
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<SalesOrder> getSalesOrderByStatus(String statusCode) {
		
		return salesOrderDAO.getSalesOrderByStatus(statusCode);
	}

	@Override
	@Transactional
	public SalesOrder getSalesOrderById(int selectedId) {
		
		return salesOrderDAO.getSalesOrderById(selectedId);
	}

	@Override
	@Transactional
	public void addSalesOrder(SalesOrder so) {
		salesOrderDAO.addSalesOrder(so);
		
	}

	@Override
	@Transactional
	public void editSalesOrder(SalesOrder so) {
		salesOrderDAO.editSalesOrder(so);
		
	}

	@Override
	@Transactional
	public void deleteSalesOrder(int selectedId) {
		salesOrderDAO.deleteSalesOrder(selectedId);
		
	}

}
