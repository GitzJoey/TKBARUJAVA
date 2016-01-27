package com.tkbaru.model.report;

import java.util.Date;

public class PurchaseOrderReportView {

	private String poCode;

	private Date poCreatedDate;

	private Date shippingDate;

	private String poRemarks;

	private Integer createdBy;

	private Date createdDate;

	private Long prodQuantity;

	private Long prodPrice;

	private String unitCode;

	private Long toBaseValue;

	private Long toBaseQty;

	private String productName;

	private String storeName;

	private String storeAddress1;

	private String storeAddress2;

	private String storeAddress3;

	private String npwpNumber;

	private String storePhone;

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

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public Long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Long getToBaseValue() {
		return toBaseValue;
	}

	public void setToBaseValue(Long toBaseValue) {
		this.toBaseValue = toBaseValue;
	}

	public Long getToBaseQty() {
		return toBaseQty;
	}

	public void setToBaseQty(Long toBaseQty) {
		this.toBaseQty = toBaseQty;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
}
