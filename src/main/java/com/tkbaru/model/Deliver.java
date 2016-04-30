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
@Table(name="tb_deliver")
public class Deliver {
	public Deliver() {

	}

	@Id
	@Column(name="deliver_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer deliverId;
	@Column(name="deliver_date")
	@Temporal(TemporalType.DATE)
	private Date deliverDate;
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
	@Column(name="truck_plate")
	private String truckPlate;
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
	private Store deliverStoreEntity;
	
	@ManyToOne
	@JoinColumn(name="items_id")
	private Items deliverItemsEntity;

	@ManyToOne
	@JoinColumn(name="vendor_truck_id")
	private TruckVendor truckVendorEntity;

	public Integer getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(Integer deliverId) {
		this.deliverId = deliverId;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
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

	public String getTruckPlate() {
		return truckPlate;
	}

	public void setTruckPlate(String truckPlate) {
		this.truckPlate = truckPlate;
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

	public Store getDeliverStoreEntity() {
		return deliverStoreEntity;
	}

	public void setDeliverStoreEntity(Store deliverStoreEntity) {
		this.deliverStoreEntity = deliverStoreEntity;
	}

	public Items getDeliverItemsEntity() {
		return deliverItemsEntity;
	}

	public void setDeliverItemsEntity(Items deliverItemsEntity) {
		this.deliverItemsEntity = deliverItemsEntity;
	}

	public TruckVendor getTruckVendorEntity() {
		return truckVendorEntity;
	}

	public void setTruckVendorEntity(TruckVendor truckVendorEntity) {
		this.truckVendorEntity = truckVendorEntity;
	}

	@Override
	public String toString() {
		return "Deliver [deliverId=" + deliverId + ", deliverDate=" + deliverDate + ", bruto=" + bruto + ", baseBruto="
				+ baseBruto + ", net=" + net + ", baseNet=" + baseNet + ", tare=" + tare + ", baseTare=" + baseTare
				+ ", truckPlate=" + truckPlate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", unitCodeLookup=" + unitCodeLookup
				+ ", baseUnitCodeLookup=" + baseUnitCodeLookup + ", deliverStoreEntity=" + deliverStoreEntity
				+ ", deliverItemsEntity=" + deliverItemsEntity + ", truckVendorEntity=" + truckVendorEntity + "]";
	}

}
