package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.PurchaseOrder;

public interface PurchaseOrderService {
	public List<PurchaseOrder> getAllPurchaseOrder();
	public PurchaseOrder getPurchaseOrderById(int selectedId);
	public List<PurchaseOrder> getPurchaseOrderByIds(String selectedIds);
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status);
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByStatus(int warehouseId, String status);
	public void addPurchaseOrder(PurchaseOrder po);
	public void editPurchaseOrder(PurchaseOrder po);
	public void deletePurchaseOrder(int selectedId);
	
}
