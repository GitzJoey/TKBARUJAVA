package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SalesOrder;

public interface SalesOrderDAO {
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId);
}
