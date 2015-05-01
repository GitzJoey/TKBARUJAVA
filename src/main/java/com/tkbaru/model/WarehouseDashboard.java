package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDashboard {
	public WarehouseDashboard() {
		
	}

	private int selectedWarehouse;
	private int selectedPO;
	private int selectedItems;
	private Receipt receipt;
	
	List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();

	public int getSelectedWarehouse() {
		return selectedWarehouse;
	}

	public void setSelectedWarehouse(int selectedWarehouse) {
		this.selectedWarehouse = selectedWarehouse;
	}

	public int getSelectedPO() {
		return selectedPO;
	}

	public void setSelectedPO(int selectedPO) {
		this.selectedPO = selectedPO;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public int getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(int selectedItems) {
		this.selectedItems = selectedItems;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}	
	
	
}
