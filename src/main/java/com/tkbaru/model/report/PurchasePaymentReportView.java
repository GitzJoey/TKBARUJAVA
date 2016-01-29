package com.tkbaru.model.report;

import java.util.Date;

import com.tkbaru.model.Lookup;

public class PurchasePaymentReportView {

	
	private Date paymentDate;
	
	private Long totalAmount;
	
	private Date effectiveDate;
	
	private Boolean isLinked;
	
	private Integer createdBy;
	
	private Date createdDate;
	
	private String paymentType;

	private String paymentStore;

	private String paymentStatus;

	private String bankCode;
	
	private PurchaseOrderReportView po;

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Boolean getIsLinked() {
		return isLinked;
	}

	public void setIsLinked(Boolean isLinked) {
		this.isLinked = isLinked;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentStore() {
		return paymentStore;
	}

	public void setPaymentStore(String paymentStore) {
		this.paymentStore = paymentStore;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PurchaseOrderReportView getPo() {
		return po;
	}

	public void setPo(PurchaseOrderReportView po) {
		this.po = po;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	

}
