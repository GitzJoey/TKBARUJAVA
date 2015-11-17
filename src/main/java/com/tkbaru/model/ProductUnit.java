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
@Table(name="tb_product_unit")
public class ProductUnit {
	public ProductUnit() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prod_unit_id")	
	private Integer productUnitId;
	@Column(name="is_base")
	private Boolean isBaseUnit;
	@Column(name="conversion_val")
	private Long conversionValue;
	@Column(name="remarks")
	private String unitRemarks;
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
	@JoinColumn(name="product_id", nullable=false)
	private Product productEntity;
	
	@ManyToOne
	@JoinColumn(name="unit_code", referencedColumnName="lookup_key")
	private Lookup unitCodeLookup;

	public Integer getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(Integer productUnitId) {
		this.productUnitId = productUnitId;
	}

	public Boolean getIsBaseUnit() {
		return isBaseUnit;
	}

	public void setIsBaseUnit(Boolean isBaseUnit) {
		this.isBaseUnit = isBaseUnit;
	}

	public Long getConversionValue() {
		return conversionValue;
	}

	public void setConversionValue(Long conversionValue) {
		this.conversionValue = conversionValue;
	}

	public String getUnitRemarks() {
		return unitRemarks;
	}

	public void setUnitRemarks(String unitRemarks) {
		this.unitRemarks = unitRemarks;
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

	@Override
	public String toString() {
		return "ProductUnit [productUnitId=" + productUnitId + ", isBaseUnit=" + isBaseUnit + ", conversionValue="
				+ conversionValue + ", unitRemarks=" + unitRemarks + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", productEntity="
				+ "productEntity" + ", unitCodeLookup=" + unitCodeLookup + "]";
	}

}
