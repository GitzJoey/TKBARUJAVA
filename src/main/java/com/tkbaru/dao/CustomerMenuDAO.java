package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.TruckVendor;

public interface CustomerMenuDAO {
	public List<SalesOrder> getSalesOrderWithDeliverId();
	public void editDeliver(SalesOrder salesOrder);
}
