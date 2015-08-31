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
    @Column(name="level_type")
    private String priceLevelType;
    @Column(name="level_name", length=100)
    private String priceLevelName;
    @Column(name="level_description", length=255)
    private String priceLevelDescription;
    @Column(name="status", length=15)
    private String priceLevelStatus;    
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
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;

	@ManyToOne
	@JoinColumn(name="level_type", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup levelTypeLookup;

	public int getPriceLevelId() {
		return priceLevelId;
	}

	public void setPriceLevelId(int priceLevelId) {
		this.priceLevelId = priceLevelId;
	}

	public String getPriceLevelType() {
		return priceLevelType;
	}

	public void setPriceLevelType(String priceLevelType) {
		this.priceLevelType = priceLevelType;
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

	public String getPriceLevelStatus() {
		return priceLevelStatus;
	}

	public void setPriceLevelStatus(String priceLevelStatus) {
		this.priceLevelStatus = priceLevelStatus;
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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	public Lookup getLevelTypeLookup() {
		return levelTypeLookup;
	}

	public void setLevelTypeLookup(Lookup levelTypeLookup) {
		this.levelTypeLookup = levelTypeLookup;
	}

	@Override
	public String toString() {
		return "PriceLevel [priceLevelId=" + priceLevelId + ", priceLevelType=" + priceLevelType + ", priceLevelName="
				+ priceLevelName + ", priceLevelDescription=" + priceLevelDescription + ", priceLevelStatus="
				+ priceLevelStatus + ", incrementValue=" + incrementValue + ", percentageValue=" + percentageValue
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}
