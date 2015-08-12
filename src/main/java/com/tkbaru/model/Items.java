package com.tkbaru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="tb_items")
@SuppressWarnings("unchecked")
public class Items implements Serializable {
	private static final long serialVersionUID = -2414813447524893528L;

	public Items() {
		
	}

	@Id
	@Column(name="items_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemsId;
	@Column(name="product_id")
	private int productId;
	@Column(name="quantity")
	private long prodQuantity;
	@Column(name="unit_code")
	private String unitCode;
	@Column(name="price")
	private long prodPrice;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
	@Column(name="base_unit_code")
	private String baseUnitCode;
	@Column(name="to_base_value")
	private Long toBaseValue;
	@Column(name="to_base_qty")
	private Long toBaseQty;

	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName="product_id", unique=true, insertable=false, updatable=false)
	private Product productLookup;

	@ManyToOne
	@JoinColumn(name="unit_code", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup unitCodeLookup;

	@ManyToOne
	@JoinColumn(name="base_unit_code", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup baseUnitCodeLookup;	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_items_receipt", 
				joinColumns={@JoinColumn(name="items_id", referencedColumnName="items_id")},
				inverseJoinColumns={@JoinColumn(name="receipt_id", referencedColumnName="receipt_id")})
	private List<Receipt> receiptList= LazyList.decorate(new ArrayList<Receipt>(), FactoryUtils.instantiateFactory(Receipt.class));
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_items_deliver", 
				joinColumns={@JoinColumn(name="items_id", referencedColumnName="items_id")},
				inverseJoinColumns={@JoinColumn(name="deliver_id", referencedColumnName="deliver_id")})
	private List<Deliver> deliverList= LazyList.decorate(new ArrayList<Deliver>(), FactoryUtils.instantiateFactory(Deliver.class));

	public int getItemsId() {
		return itemsId;
	}

	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(long prodPrice) {
		this.prodPrice = prodPrice;
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

	public String getBaseUnitCode() {
		return baseUnitCode;
	}

	public void setBaseUnitCode(String baseUnitCode) {
		this.baseUnitCode = baseUnitCode;
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

	public Product getProductLookup() {
		return productLookup;
	}

	public void setProductLookup(Product productLookup) {
		this.productLookup = productLookup;
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

	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(List<Receipt> receiptList) {
		this.receiptList = receiptList;
	}

	public List<Deliver> getDeliverList() {
		return deliverList;
	}

	public void setDeliverList(List<Deliver> deliverList) {
		this.deliverList = deliverList;
	}

	@Override
	public String toString() {
		return "Items [itemsId=" + itemsId + ", productId=" + productId + ", prodQuantity=" + prodQuantity
				+ ", unitCode=" + unitCode + ", prodPrice=" + prodPrice + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", baseUnitCode="
				+ baseUnitCode + ", toBaseValue=" + toBaseValue + ", toBaseQty=" + toBaseQty + "]";
	}

}
