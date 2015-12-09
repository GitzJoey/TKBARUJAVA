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
	@Column(name="to_base_bruto")
	private Long baseBruto;
	@Column(name="net")
	private Long net;
	@Column(name="to_base_net")
	private Long baseNet;
	@Column(name="tare")
	private Long tare;
	@Column(name="to_base_tare")
	private Long baseTare;
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

	@ManyToOne
	@JoinColumn(name="unit_code", referencedColumnName="lookup_key")
	private Lookup unitCodeLookup;
	
	@ManyToOne
	@JoinColumn(name="base_unit_code", referencedColumnName="lookup_key")
	private Lookup baseUnitCodeLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store receiptStoreEntity;

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

	public Lookup getUnitCodeLookup() {
		return unitCodeLookup;
	}

	public void setUnitCodeLookup(Lookup unitCodeLookup) {
		this.unitCodeLookup = unitCodeLookup;
	}

	public Lookup getBaseUnitCodeLookup() {
		return baseUnitCodeLookup;
	}

	public void setBaseUnitCodeLookup(Lookup baseUnitCodeLookup) {
		this.baseUnitCodeLookup = baseUnitCodeLookup;
	}

	public Store getReceiptStoreEntity() {
		return receiptStoreEntity;
	}

	public void setReceiptStoreEntity(Store receiptStoreEntity) {
		this.receiptStoreEntity = receiptStoreEntity;
	}

	@Override
	public String toString() {
		return "Receipt [receiptId=" + receiptId + ", receiptDate=" + receiptDate + ", bruto=" + bruto + ", baseBruto="
				+ baseBruto + ", net=" + net + ", baseNet=" + baseNet + ", tare=" + tare + ", baseTare=" + baseTare
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", unitCodeLookup=" + "unitCodeLookup" + ", baseUnitCodeLookup="
				+ "baseUnitCodeLookup" + ", receiptStoreEntity=" + "receiptStoreEntity" + "]";
	}

}
