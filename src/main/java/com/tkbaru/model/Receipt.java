package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_receipt")
public class Receipt {
	public Receipt() {

	}

	@Id
	@Column(name = "receipt_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int receiptId;
	@Column(name = "receipt_date")
	private Date receiptDate;
	@Column(name = "items_id")
	private int itemsId;
	@Column(name = "net")
	private int net;
	@Column(name = "tare")
	private int tare;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "items_id", referencedColumnName = "items_id", unique = true, insertable = false, updatable = false)
	private Items itemsLookup;
	
	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public int getItemsId() {
		return itemsId;
	}

	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}

	public int getNet() {
		return net;
	}

	public void setNet(int net) {
		this.net = net;
	}

	public int getTare() {
		return tare;
	}

	public void setTare(int tare) {
		this.tare = tare;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Items getItemsLookup() {
		return itemsLookup;
	}

	public void setItemsLookup(Items itemsLookup) {
		this.itemsLookup = itemsLookup;
	}


	@Override
	public String toString() {
		return "Receipt [receiptId=" + receiptId + ", itemsId="
				+ itemsId + ", receiptDate=" + receiptDate
				+ ", net=" + net + ", tare=" + tare
				+ ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
