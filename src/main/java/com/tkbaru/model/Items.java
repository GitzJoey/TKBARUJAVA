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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
	private Integer itemsId;
	@Column(name="quantity")
	private Long prodQuantity;
	@Column(name="price")
	private Long prodPrice;
	@Column(name="to_base_value")
	private Long toBaseValue;
	@Column(name="to_base_qty")
	private Long toBaseQty;
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
	@JoinColumn(name="product_id")
	private Product productEntity;

	@ManyToOne
	@JoinColumn(name="stocks_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Stocks stocksEntity;

	@ManyToOne
	@JoinColumn(name="unit_code", referencedColumnName="lookup_key")
	private Lookup unitCodeLookup;

	@ManyToOne
	@JoinColumn(name="base_unit_code", referencedColumnName="lookup_key")
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

	public Integer getItemsId() {
		return itemsId;
	}

	public void setItemsId(Integer itemsId) {
		this.itemsId = itemsId;
	}

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public Long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
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

	public Product getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(Product productEntity) {
		this.productEntity = productEntity;
	}

	public Stocks getStocksEntity() {
		return stocksEntity;
	}

	public void setStocksEntity(Stocks stocksEntity) {
		this.stocksEntity = stocksEntity;
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
		return "Items [itemsId=" + itemsId + ", prodQuantity=" + prodQuantity + ", prodPrice=" + prodPrice
				+ ", toBaseValue=" + toBaseValue + ", toBaseQty=" + toBaseQty + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", productEntity=" + productEntity + ", stocksEntity=" + stocksEntity + ", unitCodeLookup="
				+ unitCodeLookup + ", baseUnitCodeLookup=" + baseUnitCodeLookup + ", receiptList=" + receiptList
				+ ", deliverList=" + deliverList + "]";
	}

}
