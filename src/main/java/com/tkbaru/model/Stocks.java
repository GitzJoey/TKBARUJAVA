package com.tkbaru.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_stocks")
public class Stocks {
	public Stocks() {
		
	}

	@Id
	@Column(name="stocks_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stocksId;
	@Column(name="po_id")
	private int poId;
	@Column(name="product_id")
	private int productId;
	@Column(name="warehouse_id")
	private int warehouseId;
	@Column(name="quantity")
	private long prodQuantity;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name="po_id", unique=true, insertable=false, updatable=false)
	private PurchaseOrder poLookup;

	@ManyToOne
	@JoinColumn(name="product_id", unique=true, insertable=false, updatable=false)
	private Product productLookup;

	@ManyToOne
	@JoinColumn(name="warehouse_id", unique=true, insertable=false, updatable=false)
	private Warehouse warehouseLookup;

	@ManyToOne
	@JoinColumn(name="product_id", unique=true, insertable=false, updatable=false)
	private Product productEntity;

	@OneToMany(mappedBy="stocksEntity")
	private List<Price> priceList;

	public int getStocksId() {
		return stocksId;
	}

	public void setStocksId(int stocksId) {
		this.stocksId = stocksId;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(long prodQuantity) {
		this.prodQuantity = prodQuantity;
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

	public PurchaseOrder getPoLookup() {
		return poLookup;
	}

	public void setPoLookup(PurchaseOrder poLookup) {
		this.poLookup = poLookup;
	}

	public Product getProductLookup() {
		return productLookup;
	}

	public void setProductLookup(Product productLookup) {
		this.productLookup = productLookup;
	}

	public Warehouse getWarehouseLookup() {
		return warehouseLookup;
	}

	public void setWarehouseLookup(Warehouse warehouseLookup) {
		this.warehouseLookup = warehouseLookup;
	}

	public Product getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(Product productEntity) {
		this.productEntity = productEntity;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	@Override
	public String toString() {
		return "Stocks [stocksId=" + stocksId + ", poId=" + poId + ", productId=" + productId + ", warehouseId="
				+ warehouseId + ", prodQuantity=" + prodQuantity + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", poLookup=" + poLookup
				+ ", productLookup=" + productLookup + ", warehouseLookup=" + warehouseLookup + ", productEntity="
				+ productEntity + ", priceList=" + priceList + "]";
	}
	
}
