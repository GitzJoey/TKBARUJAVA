package com.tkbaru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_po")
@SuppressWarnings("unchecked")
public class PurchaseOrder {

	@Id
	@Column(name="po_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int poId;
	@Column(name="po_code")
	private String poCode;
	@Column(name="po_created")
	private Date poCreatedDate;
	@Column(name="shipping_date")
	private Date shippingDate;
	@Column(name="supplier_id")
	private int supplierId;
	@Column(name="warehouse_id")
	private int warehouseId;
	@Column(name="po_type")
	private String poType;
	@Column(name="status")
	private String poStatus;
	@Column(name="remarks")
	private String poRemarks;	
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="tb_po_items", 
				joinColumns={@JoinColumn(name="po_id", referencedColumnName="po_id")},
				inverseJoinColumns={@JoinColumn(name="items_id", referencedColumnName="items_id")})
	private List<Items> itemsList = LazyList.decorate(new ArrayList<Items>(), FactoryUtils.instantiateFactory(Items.class));

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="tb_po_payment", 
				joinColumns={@JoinColumn(name="po_id", referencedColumnName="po_id")},
				inverseJoinColumns={@JoinColumn(name="payment_id", referencedColumnName="payment_id")})
	private List<Payment> paymentList = LazyList.decorate(new ArrayList<Payment>(), FactoryUtils.instantiateFactory(Payment.class));

	@ManyToOne
	@JoinColumn(name="supplier_id", referencedColumnName="supplier_id", unique=true, insertable=false, updatable=false)
	private Supplier supplierLookup;

	@ManyToOne
	@JoinColumn(name="warehouse_id", referencedColumnName="warehouse_id", unique=true, insertable=false, updatable=false)
	private Warehouse warehouseLookup;

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;
	
	@ManyToOne
	@JoinColumn(name="po_type", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup poTypeLookup;

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

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

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getPoRemarks() {
		return poRemarks;
	}

	public void setPoRemarks(String poRemarks) {
		this.poRemarks = poRemarks;
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

	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public Supplier getSupplierLookup() {
		return supplierLookup;
	}

	public void setSupplierLookup(Supplier supplierLookup) {
		this.supplierLookup = supplierLookup;
	}

	public Warehouse getWarehouseLookup() {
		return warehouseLookup;
	}

	public void setWarehouseLookup(Warehouse warehouseLookup) {
		this.warehouseLookup = warehouseLookup;
	}

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	public Lookup getPoTypeLookup() {
		return poTypeLookup;
	}

	public void setPoTypeLookup(Lookup poTypeLookup) {
		this.poTypeLookup = poTypeLookup;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [poId=" + poId + ", poCode=" + poCode
				+ ", poCreatedDate=" + poCreatedDate + ", shippingDate="
				+ shippingDate + ", supplierId=" + supplierId
				+ ", warehouseId=" + warehouseId + ", poType=" + poType
				+ ", poStatus=" + poStatus + ", poRemarks=" + poRemarks
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", itemsList=" + itemsList + ", paymentList=" + paymentList
				+ "]";
	}

}
