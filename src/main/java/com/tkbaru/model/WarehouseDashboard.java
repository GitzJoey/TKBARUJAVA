package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDashboard {
	
	List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}
	
	
}
