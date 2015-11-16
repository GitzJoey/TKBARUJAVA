package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_receipt")
public class Receipt {
	public Receipt() {

	}

	@Id
	@Column(name="receipt_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer receiptId;
	@Column(name="receipt_date")
	private Date receiptDate;
	@Column(name="bruto")
	private Long bruto;
	@Column(name="net")
	private Long net;
	@Column(name="tare")
	private Long tare;
	@Column(name="created_by")
	private Integer createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private Integer updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
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
	public Long getNet() {
		return net;
	}
	public void setNet(Long net) {
		this.net = net;
	}
	public Long getTare() {
		return tare;
	}
	public void setTare(Long tare) {
		this.tare = tare;
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
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Receipt [receiptId=" + receiptId + ", receiptDate=" + receiptDate + ", bruto=" + bruto + ", net=" + net
				+ ", tare=" + tare + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
