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
	@Column(name="quantity")
	private long prodQuantity;
	@Column(name="current_quantity")
	private long currentQuantity;
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
	@JoinColumn(name="po_id")
	private PurchaseOrder purchaseOrderEntity;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store stocksStoreEntity;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product productEntity;

	@ManyToOne
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouseEntity;

	@OneToMany(mappedBy="stocksEntity")
	private List<Price> priceList;

	public int getStocksId() {
		return stocksId;
	}

	public void setStocksId(int stocksId) {
		this.stocksId = stocksId;
	}

	public long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public long getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(long currentQuantity) {
		this.currentQuantity = currentQuantity;
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

	public PurchaseOrder getPurchaseOrderEntity() {
		return purchaseOrderEntity;
	}

	public void setPurchaseOrderEntity(PurchaseOrder purchaseOrderEntity) {
		this.purchaseOrderEntity = purchaseOrderEntity;
	}

	public Store getStocksStoreEntity() {
		return stocksStoreEntity;
	}

	public void setStocksStoreEntity(Store stocksStoreEntity) {
		this.stocksStoreEntity = stocksStoreEntity;
	}

	public Product getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(Product productEntity) {
		this.productEntity = productEntity;
	}

	public Warehouse getWarehouseEntity() {
		return warehouseEntity;
	}

	public void setWarehouseEntity(Warehouse warehouseEntity) {
		this.warehouseEntity = warehouseEntity;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	@Override
	public String toString() {
		return "Stocks [stocksId=" + stocksId + ", prodQuantity=" + prodQuantity + ", currentQuantity="
				+ currentQuantity + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", purchaseOrderEntity=" + purchaseOrderEntity
				+ ", stocksStoreEntity=" + stocksStoreEntity + ", productEntity=" + productEntity + ", warehouseEntity="
				+ warehouseEntity + ", priceList=" + priceList + "]";
	}

}
