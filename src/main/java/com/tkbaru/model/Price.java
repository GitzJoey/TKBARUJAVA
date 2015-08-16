package com.tkbaru.model;

import java.math.BigDecimal;
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
@Table(name="tb_price")
public class Price {
    public Price() {
		
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="price_id", nullable=false)
    private Integer priceId;
    @Column(name="stocks_id")
    private int stocksId;
    @Column(name="price_level_id")
    private Integer priceLevelId;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="input_date")
    private Date inputDate;
    @Column(name="status")
    private String priceStatus;
    @Column(name="created_by")
    private int createdBy;
    @Column(name="created_date")
    private Date createdDate;
    @Column(name="updated_by")
    private int updatedBy;
    @Column(name="updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name="stocks_id", nullable=false, insertable=false, updatable=false)
    private Stocks stocksEntity;

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public int getStocksId() {
		return stocksId;
	}

	public void setStocksId(int stocksId) {
		this.stocksId = stocksId;
	}

	public Integer getPriceLevelId() {
		return priceLevelId;
	}

	public void setPriceLevelId(Integer priceLevelId) {
		this.priceLevelId = priceLevelId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getPriceStatus() {
		return priceStatus;
	}

	public void setPriceStatus(String priceStatus) {
		this.priceStatus = priceStatus;
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

	public Stocks getStocksEntity() {
		return stocksEntity;
	}

	public void setStocksEntity(Stocks stocksEntity) {
		this.stocksEntity = stocksEntity;
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", priceLevelId=" + priceLevelId + ", stocksId=" + stocksId + ", price=" + price
				+ ", inputDate=" + inputDate + ", priceStatus=" + priceStatus + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", stocksEntity=" + stocksEntity + "]";
	}
        
}
