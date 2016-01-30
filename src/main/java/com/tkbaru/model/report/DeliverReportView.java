package com.tkbaru.model.report;

import java.util.Date;

public class DeliverReportView {
	
	private Date deliverDate;

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

	private String deliverStore;

	

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

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getDeliverStore() {
		return deliverStore;
	}

	public void setDeliverStore(String deliverStore) {
		this.deliverStore = deliverStore;
	}

	
	
	

}
