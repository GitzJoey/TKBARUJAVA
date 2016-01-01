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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="tb_stocks")
public class Stocks {
	public Stocks() {
		
	}

	@Id
	@Column(name="stocks_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stocksId;
	@Column(name="quantity")
	private Long prodQuantity;
	@Column(name="current_quantity")
	private Long currentQuantity;
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
	@OrderBy("inputDate DESC")
	private List<Price> priceList;

	@Transient
	public Price getLatestPrice(int priceLevelId) {
		if (priceList != null && priceList.size() > 0) {
			Date latestDate = priceList.get(0).getInputDate();
			
			for (Price p:priceList) {
				if (latestDate.compareTo(p.getInputDate()) == 0 && p.getPriceLevelEntity().getPriceLevelId() == priceLevelId) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Integer getStocksId() {
		return stocksId;
	}

	public void setStocksId(Integer stocksId) {
		this.stocksId = stocksId;
	}

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public Long getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Long currentQuantity) {
		this.currentQuantity = currentQuantity;
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
				+ ", stocksStoreEntity=" + stocksStoreEntity + ", productEntity=" + "productEntity" + ", warehouseEntity="
				+ warehouseEntity + ", priceList=" + "priceList" + "]";
	}

}
