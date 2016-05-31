package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.TruckVendor;

public class CustomerMenuServiceImpl implements CustomerMenuService{
	@Autowired
	SearchService searchManager;
	
	@Autowired
	com.tkbaru.dao.CustomerMenuDAO CustomerMenuDAO;

	@Override
	@Transactional
	public List<SalesOrder> getSalesOrderWithDeliverId() {

		return CustomerMenuDAO.getSalesOrderWithDeliverId();
	} 
	
	@Override
	@Transactional
	public void editDeliver(SalesOrder salesOrder) {
	
		CustomerMenuDAO.editDeliver(salesOrder);
	}
}
