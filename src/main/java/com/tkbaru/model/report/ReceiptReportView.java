package com.tkbaru.model.report;

import java.util.Date;

public class ReceiptReportView {
	
	private Date receiptDate;

	private Long bruto;
	
	private Long baseBruto;
	
	private Long net;
	
	private Long baseNet;
	
	private Long tare;
	
	private Long baseTare;
	
	private Integer createdBy;
	
	private Date createdDate;
	
	private String unitCode;
	
	private String baseUnitCode;

	private String receiptStore;

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Long getBruto() {
		return bruto;
	}

	public void setBruto(Long bruto) {
		this.bruto = bruto;
	}

	public Long getBaseBruto() {
		return baseBruto;
	}

	public void setBaseBruto(Long baseBruto) {
		this.baseBruto = baseBruto;
	}

	public Long getNet() {
		return net;
	}

	public void setNet(Long net) {
		this.net = net;
	}

	public Long getBaseNet() {
		return baseNet;
	}

	public void setBaseNet(Long baseNet) {
		this.baseNet = baseNet;
	}

	public Long getTare() {
		return tare;
	}

	public void setTare(Long tare) {
		this.tare = tare;
	}

	public Long getBaseTare() {
		return baseTare;
	}

	public void setBaseTare(Long baseTare) {
		this.baseTare = baseTare;
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

	public String getBaseUnitCode() {
		return baseUnitCode;
	}

	public void setBaseUnitCode(String baseUnitCode) {
		this.baseUnitCode = baseUnitCode;
	}

	public String getReceiptStore() {
		return receiptStore;
	}

	public void setReceiptStore(String receiptStore) {
		this.receiptStore = receiptStore;
	}
	
	

}
