package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.SalesOrderCopy;

public interface SalesOrderDAO {
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId);
	public List<SalesOrder> getSalesOrderByStatus(String statusCode);
	public List<SalesOrder> getSalesOrderBySalesCode(String salesCode);
	public SalesOrder getSalesOrderById(int selectedId);
	public void addSalesOrder(SalesOrder so);
	public void editSalesOrder(SalesOrder so);
	public void addSalesOrderCopy(SalesOrderCopy cp);
	public void editSalesOrderCopy(SalesOrderCopy cp);
	public void deleteSalesOrder(int selectedId);

	public boolean isExistingSalesCode(String salesCode);
}
