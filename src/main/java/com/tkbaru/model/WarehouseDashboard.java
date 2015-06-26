package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDashboard {
	public WarehouseDashboard() {

	}

	private int selectedWarehouse;
	private int selectedPO;
	private int selectedSales;
	private int selectedItems;
	private Receipt receipt;
	private Deliver deliver;
	private List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
	private List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();

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

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public int getSelectedSales() {
		return selectedSales;
	}

	public void setSelectedSales(int selectedSales) {
		this.selectedSales = selectedSales;
	}

	public Deliver getDeliver() {
		return deliver;
	}

	public void setDeliver(Deliver deliver) {
		this.deliver = deliver;
	}

}
