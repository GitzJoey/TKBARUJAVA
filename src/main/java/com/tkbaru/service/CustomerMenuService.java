package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.TruckVendor;

public interface CustomerMenuService {
	public List<SalesOrder> getSalesOrderWithDeliverId();
	public void editDeliver(SalesOrder salesOrder);
}
