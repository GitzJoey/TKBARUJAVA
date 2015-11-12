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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_price_level")
public class PriceLevel{
    public PriceLevel() {
		
    }
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="price_level_id")
    private int priceLevelId ;
    @Column(name="level_name", length=100)
    private String priceLevelName;
    @Column(name="level_description", length=255)
    private String priceLevelDescription;
    @Column(name="increment_value")
    private BigDecimal incrementValue;
    @Column(name="percentage_value")
    private BigDecimal percentageValue;
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
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup priceLevelStatusLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store priceLevelStoreEntity;

	@ManyToOne
	@JoinColumn(name="level_type", referencedColumnName="lookup_key")
	private Lookup priceLevelTypeLookup;

	public int getPriceLevelId() {
		return priceLevelId;
	}

	public void setPriceLevelId(int priceLevelId) {
		this.priceLevelId = priceLevelId;
	}

	public String getPriceLevelName() {
		return priceLevelName;
	}

	public void setPriceLevelName(String priceLevelName) {
		this.priceLevelName = priceLevelName;
	}

	public String getPriceLevelDescription() {
		return priceLevelDescription;
	}

	public void setPriceLevelDescription(String priceLevelDescription) {
		this.priceLevelDescription = priceLevelDescription;
	}

	public BigDecimal getIncrementValue() {
		return incrementValue;
	}

	public void setIncrementValue(BigDecimal incrementValue) {
		this.incrementValue = incrementValue;
	}

	public BigDecimal getPercentageValue() {
		return percentageValue;
	}

	public void setPercentageValue(BigDecimal percentageValue) {
		this.percentageValue = percentageValue;
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

	public Lookup getPriceLevelStatusLookup() {
		return priceLevelStatusLookup;
	}

	public void setPriceLevelStatusLookup(Lookup priceLevelStatusLookup) {
		this.priceLevelStatusLookup = priceLevelStatusLookup;
	}

	public Store getPriceLevelStoreEntity() {
		return priceLevelStoreEntity;
	}

	public void setPriceLevelStoreEntity(Store priceLevelStoreEntity) {
		this.priceLevelStoreEntity = priceLevelStoreEntity;
	}

	public Lookup getPriceLevelTypeLookup() {
		return priceLevelTypeLookup;
	}

	public void setPriceLevelTypeLookup(Lookup priceLevelTypeLookup) {
		this.priceLevelTypeLookup = priceLevelTypeLookup;
	}

	@Override
	public String toString() {
		return "PriceLevel [priceLevelId=" + priceLevelId + ", priceLevelName=" + priceLevelName
				+ ", priceLevelDescription=" + priceLevelDescription + ", incrementValue=" + incrementValue
				+ ", percentageValue=" + percentageValue + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", priceLevelStatusLookup="
				+ priceLevelStatusLookup + ", priceLevelStoreEntity=" + priceLevelStoreEntity
				+ ", priceLevelTypeLookup=" + priceLevelTypeLookup + "]";
	}

}
