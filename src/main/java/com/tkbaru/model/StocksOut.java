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
@Table(name = "tb_stocks_out")
public class StocksOut {
	public StocksOut() {

	}

	@Id
	@Column(name="stocks_out_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stocksOutId;
	@Column(name="quantity")
	private Long prodQuantity;
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
	@JoinColumn(name="so_id")
	private SalesOrder salesOrderEntity;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store salesStoreEntity;

	@ManyToOne
	@JoinColumn(name="stocks_id")
	private Stocks stocksEntity;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product productEntity;

	@ManyToOne
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouseEntity;

	public Integer getStocksOutId() {
		return stocksOutId;
	}

	public void setStocksOutId(Integer stocksOutId) {
		this.stocksOutId = stocksOutId;
	}

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
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

	public SalesOrder getSalesOrderEntity() {
		return salesOrderEntity;
	}

	public void setSalesOrderEntity(SalesOrder salesOrderEntity) {
		this.salesOrderEntity = salesOrderEntity;
	}

	public Store getSalesStoreEntity() {
		return salesStoreEntity;
	}

	public void setSalesStoreEntity(Store salesStoreEntity) {
		this.salesStoreEntity = salesStoreEntity;
	}

	public Stocks getStocksEntity() {
		return stocksEntity;
	}

	public void setStocksEntity(Stocks stocksEntity) {
		this.stocksEntity = stocksEntity;
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

	@Override
	public String toString() {
		return "StocksOut [stocksOutId=" + stocksOutId + ", prodQuantity=" + prodQuantity + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", salesOrderEntity=" + salesOrderEntity + ", salesStoreEntity=" + salesStoreEntity
				+ ", stocksEntity=" + stocksEntity + ", productEntity=" + productEntity + ", warehouseEntity="
				+ warehouseEntity + "]";
	}

}
