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
@Table(name = "tb_stocks_out")
public class StocksOut {
	public StocksOut() {

	}

	@Id
	@Column(name = "stocks_out_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stocksOutId;
	@Column(name = "so_id")
	private int salesId;
	@Column(name = "product_id")
	private int productId;
	@Column(name = "warehouse_id")
	private int warehouseId;
	@Column(name = "quantity")
	private long prodQuantity;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "stocks_id", unique = true, insertable = false, updatable = false)
	private Stocks stocksLookup;

	@ManyToOne
	@JoinColumn(name = "product_id", unique = true, insertable = false, updatable = false)
	private Product productLookup;

	@ManyToOne
	@JoinColumn(name = "warehouse_id", unique = true, insertable = false, updatable = false)
	private Warehouse warehouseLookup;

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public int getStocksOutId() {
		return stocksOutId;
	}

	public void setStocksOutId(int stocksOutId) {
		this.stocksOutId = stocksOutId;
	}

	public Stocks getStocksLookup() {
		return stocksLookup;
	}

	public void setStocksLookup(Stocks stocksLookup) {
		this.stocksLookup = stocksLookup;
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

	@Override
	public String toString() {
		return "Stocks [stocksOutId=" + stocksOutId + ", productId="
				+ productId + ", warehouseId=" + warehouseId
				+ ", prodQuantity=" + prodQuantity + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}

}
