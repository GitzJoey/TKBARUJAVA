package com.tkbaru.model;

import java.io.Serializable;
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
@Table(name="tb_so_copy_items")
public class SalesOrderCopyItems implements Serializable {
	private static final long serialVersionUID = -700505133253984796L;

	public SalesOrderCopyItems() {
		
	}

	@Id
	@Column(name="so_copy_items_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer salesOrderCopyItemsId;
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
	@JoinColumn(name="so_copy_id", nullable=false)
	private SalesOrderCopy salesOrderCopyEntity;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product productEntity;

	@ManyToOne
	@JoinColumn(name="unit_code", referencedColumnName="lookup_key")
	private Lookup unitCodeLookup;

	@ManyToOne
	@JoinColumn(name="base_unit_code", referencedColumnName="lookup_key")
	private Lookup baseUnitCodeLookup;

	public Integer getSalesOrderCopyItemsId() {
		return salesOrderCopyItemsId;
	}

	public void setSalesOrderCopyItemsId(Integer salesOrderCopyItemsId) {
		this.salesOrderCopyItemsId = salesOrderCopyItemsId;
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

	@Override
	public String toString() {
		return "SalesOrderCopyItems [salesOrderCopyItemsId=" + salesOrderCopyItemsId + ", prodQuantity=" + prodQuantity
				+ ", prodPrice=" + prodPrice + ", toBaseValue=" + toBaseValue + ", toBaseQty=" + toBaseQty
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", productEntity=" + "productEntity" + ", unitCodeLookup="
				+ "unitCodeLookup" + ", baseUnitCodeLookup=" + "baseUnitCodeLookup" + "]";
	}	
	
}
