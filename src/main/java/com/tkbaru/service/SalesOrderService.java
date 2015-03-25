package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Customer;
import com.tkbaru.model.SalesOrder;

public interface SalesOrderService {
	public List<Customer> searchCustomer(String querySearch);
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId);
}
