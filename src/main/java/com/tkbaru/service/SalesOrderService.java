package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Customer;
import com.tkbaru.model.SalesOrder;

public interface SalesOrderService {
	public List<Customer> searchCustomer(String querySearch);
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId);
	public List<SalesOrder> getSalesOrderByStatus(String statusCode);
	public List<SalesOrder> getSalesOrderBySalesCode(String salesCode);
	public SalesOrder getSalesOrderById(int selectedId);
	public void addSalesOrder(SalesOrder so);
	public void editSalesOrder(SalesOrder so);
	public void deleteSalesOrder(int selectedId);

	public String generateSalesCode();
}
