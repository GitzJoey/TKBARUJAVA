package com.tkbaru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.RandomProvider;
import com.tkbaru.dao.LookupDAO;
import com.tkbaru.dao.PurchaseOrderDAO;
import com.tkbaru.model.PurchaseOrder;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	LookupDAO lookupDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<PurchaseOrder> getAllPurchaseOrder() {

		return purchaseOrderDAO.getAllPurchaseOrder();
	}

	@Override
	@Transactional
	public PurchaseOrder getPurchaseOrderById(int selectedId) {

		return purchaseOrderDAO.getPurchaseOrderById(selectedId);
	}

	@Override
	@Transactional
	public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
		try {
			purchaseOrderDAO.addPurchaseOrder(purchaseOrder);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void editPurchaseOrder(PurchaseOrder purchaseOrder) {
		
		purchaseOrderDAO.editPurchaseOrder(purchaseOrder);
	}

	@Override
	@Transactional
	public void deletePurchaseOrder(int selectedId) {
		
		purchaseOrderDAO.deletePurchaseOrder(selectedId);
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByIds(String selectedIds) {

		if (selectedIds.length() == 0)
			return new ArrayList<PurchaseOrder>();

		return purchaseOrderDAO.getPurchaseOrderByIds(selectedIds);
	}

	@Override
	@Transactional
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status) {
		
		return purchaseOrderDAO.getPurchaseOrderByStatus(status);
	}

	@Override
	@Transactional
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByStatus(int warehouseId, String status) {
		
		return purchaseOrderDAO.getPurchaseOrderByWarehouseIdByStatus(warehouseId, status);
	}

	@Override
	@Transactional
	public String generatePOCode() {
		
		String generatedPOCode = "";

		RandomProvider r = new RandomProvider(6);
		
		do {
			generatedPOCode = r.generateAlphaNumericRandom();
		} while (purchaseOrderDAO.isExistingPOCode(generatedPOCode));
			
		return generatedPOCode;
	}

	@Override
	@Transactional
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByShippingDate(int warehouseId, Date startDate, Date endDate) {
		
		return purchaseOrderDAO.getPurchaseOrderByWarehouseIdByShippingDate(warehouseId, startDate, endDate);
	}

	@Override
	@Transactional
	public List<PurchaseOrder> getAllUnfinishedPurchaseOrder() {
		
		return purchaseOrderDAO.getAllUnfinishedPurchaseOrder();
	}

	@Override
	@Transactional
	public int getCountPaymentDue() {
		
		return purchaseOrderDAO.getCountPaymentDue();
	}

}
