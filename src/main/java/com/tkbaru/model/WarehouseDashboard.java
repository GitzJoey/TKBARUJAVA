package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDashboard {
	public WarehouseDashboard() {

	}

	private Integer selectedWarehouse;
	private Integer selectedPO;
	private Integer selectedSales;
	private Integer selectedItems;
	private Receipt receipt;
	private Deliver deliver;
	private List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
	private List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();

	public Integer getSelectedWarehouse() {
		return selectedWarehouse;
	}

	public void setSelectedWarehouse(Integer selectedWarehouse) {
		this.selectedWarehouse = selectedWarehouse;
	}

	public Integer getSelectedPO() {
		return selectedPO;
	}

	public void setSelectedPO(Integer selectedPO) {
		this.selectedPO = selectedPO;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public Integer getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(Integer selectedItems) {
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

	public Integer getSelectedSales() {
		return selectedSales;
	}

	public void setSelectedSales(Integer selectedSales) {
		this.selectedSales = selectedSales;
	}

	public Deliver getDeliver() {
		return deliver;
	}

	public void setDeliver(Deliver deliver) {
		this.deliver = deliver;
	}

}
