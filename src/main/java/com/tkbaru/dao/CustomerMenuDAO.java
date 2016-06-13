package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SalesOrder;

public interface CustomerMenuDAO {
	public List<SalesOrder> getSalesOrderWithDeliverId();
	public void editDeliver(SalesOrder salesOrder);
}
