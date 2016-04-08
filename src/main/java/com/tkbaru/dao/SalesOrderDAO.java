package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SalesOrder;

public interface SalesOrderDAO {
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId);
	public List<SalesOrder> getSalesOrderByStatus(String statusCode);
	public List<SalesOrder> getSalesOrderBySalesCode(String statusCode, String salesCode);
	public SalesOrder getSalesOrderById(int selectedId);
	public void addSalesOrder(SalesOrder so);
	public void editSalesOrder(SalesOrder so);
	public void deleteSalesOrder(int selectedId);

	public boolean isExistingSalesCode(String salesCode);
}
