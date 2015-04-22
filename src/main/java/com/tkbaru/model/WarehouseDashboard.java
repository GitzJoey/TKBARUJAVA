package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDashboard {
	public WarehouseDashboard() {
		
	}
	private int selectedWarehouse;
	
	List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();

	public int getSelectedWarehouse() {
		return selectedWarehouse;
	}

	public void setSelectedWarehouse(int selectedWarehouse) {
		this.selectedWarehouse = selectedWarehouse;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}	
}
