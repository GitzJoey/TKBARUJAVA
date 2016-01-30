package com.tkbaru.model.report;

import java.util.Date;
import java.util.List;

public class SalesOrderReportView {

	private String salesCode;

	private Date salesCreatedDate;

	private Date shippingDate;

	private String walkInCustDetail;

	private String salesRemarks;

	private Integer createdBy;

	private Date createdDate;

	private String customerName;

	private String storeName;

	private String storeAddress1;

	private String storeAddress2;

	private String storeAddress3;

	private String npwpNumber;

	private String storePhone;

	private List<ItemsReportView> items;

	private SalesPaymentReportView payment;
	
	private DeliverReportView deliver;

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public Date getSalesCreatedDate() {
		return salesCreatedDate;
	}

	public void setSalesCreatedDate(Date salesCreatedDate) {
		this.salesCreatedDate = salesCreatedDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getWalkInCustDetail() {
		return walkInCustDetail;
	}

	public void setWalkInCustDetail(String walkInCustDetail) {
		this.walkInCustDetail = walkInCustDetail;
	}

	public String getSalesRemarks() {
		return salesRemarks;
	}

	public void setSalesRemarks(String salesRemarks) {
		this.salesRemarks = salesRemarks;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public List<ItemsReportView> getItems() {
		return items;
	}

	public void setItems(List<ItemsReportView> items) {
		this.items = items;
	}

	public SalesPaymentReportView getPayment() {
		return payment;
	}

	public void setPayment(SalesPaymentReportView payment) {
		this.payment = payment;
	}

	public DeliverReportView getDeliver() {
		return deliver;
	}

	public void setDeliver(DeliverReportView deliver) {
		this.deliver = deliver;
	}
	
	

}
