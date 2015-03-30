package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.PurchaseOrder;

public interface PurchaseOrderDAO

{

	public List<PurchaseOrder> getAllPurchaseOrder();

	public PurchaseOrder getPurchaseOrderById(int selectedId);

	public List<PurchaseOrder> getPurchaseOrderByIds(String selectedIdINClause);
	
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status);

	public void addPurchaseOrder(PurchaseOrder prod);

	public void editPurchaseOrder(PurchaseOrder prod);

	public void deletePurchaseOrder(int selectedId);
	
	public void savePayment(PurchaseOrder payment);

}
