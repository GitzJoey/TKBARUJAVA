package com.tkbaru.dao;

import java.util.Date;
import java.util.List;

import com.tkbaru.model.PurchaseOrder;

public interface PurchaseOrderDAO {
	public List<PurchaseOrder> getAllPurchaseOrder();
	public PurchaseOrder getPurchaseOrderById(int selectedId);
	public List<PurchaseOrder> getPurchaseOrderByIds(String selectedIdINClause);
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status);
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByStatus(int warehouseId, String status);
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByShippingDate(int warehouseId, Date startDate, Date endDate);
	public List<PurchaseOrder> getAllUnfinishedPurchaseOrder();
	public void addPurchaseOrder(PurchaseOrder po);
	public void editPurchaseOrder(PurchaseOrder po);
	public void deletePurchaseOrder(int selectedId);
	public void savePayment(PurchaseOrder po);

	public int getCountPaymentDue();
	public boolean isExistingPOCode(String poCode);
}
