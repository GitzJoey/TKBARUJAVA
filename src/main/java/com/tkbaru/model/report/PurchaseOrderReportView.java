package com.tkbaru.model.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PurchaseOrderReportView {

	/**
	 * 
	 */

	private String poCode;

	private Date poCreatedDate;

	private Date shippingDate;

	private String poRemarks;

	private Integer createdBy;

	private Date createdDate;

	private String storeName;

	private String storeAddress1;

	private String storeAddress2;

	private String storeAddress3;

	private String npwpNumber;

	private String storePhone;
	
	private String warehouseName;
	
	private String supplierName;
	
	private List<ItemsReportView> items;
	
	private PurchasePaymentReportView payment;
	

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public Date getPoCreatedDate() {
		return poCreatedDate;
	}

	public void setPoCreatedDate(Date poCreatedDate) {
		this.poCreatedDate = poCreatedDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getPoRemarks() {
		return poRemarks;
	}

	public void setPoRemarks(String poRemarks) {
		this.poRemarks = poRemarks;
	}

	

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress1() {
		return storeAddress1;
	}

	public void setStoreAddress1(String storeAddress1) {
		this.storeAddress1 = storeAddress1;
	}

	public String getStoreAddress2() {
		return storeAddress2;
	}

	public void setStoreAddress2(String storeAddress2) {
		this.storeAddress2 = storeAddress2;
	}

	public String getStoreAddress3() {
		return storeAddress3;
	}

	public void setStoreAddress3(String storeAddress3) {
		this.storeAddress3 = storeAddress3;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getNpwpNumber() {
		return npwpNumber;
	}

	public void setNpwpNumber(String npwpNumber) {
		this.npwpNumber = npwpNumber;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<ItemsReportView> getItems() {
		return items;
	}

	public void setItems(List<ItemsReportView> items) {
		this.items = items;
	}

	public PurchasePaymentReportView getPayment() {
		return payment;
	}

	public void setPayment(PurchasePaymentReportView payment) {
		this.payment = payment;
	}
	
	
}
