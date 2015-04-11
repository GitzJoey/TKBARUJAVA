package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name="quantity")
	private long prodQuantity;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
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
	
	@Override
	public String toString() {
		return "Stocks [stocksId=" + stocksId + ", poId=" + poId+", productId=" + productId
				+ ", prodQuantity=" + prodQuantity + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}


}
