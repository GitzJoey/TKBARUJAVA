package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_product_unit")
public class ProductUnit {
	public ProductUnit() {
		
	}
	
	@Id
	@Column(name="prod_unit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productUnitId;
	@Column(name="unit_code")
	private String unitCode;
	@Column(name="conversion_val")
	private Long conversionValue;
	@Column(name="remarks")
	private String unitRemarks;

	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product productEntity;

	public int getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(int productUnitId) {
		this.productUnitId = productUnitId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public Product getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(Product productEntity) {
		this.productEntity = productEntity;
	}

	@Override
	public String toString() {
		return "ProductUnit [productUnitId=" + productUnitId + ", unitCode="
				+ unitCode + ", conversionValue=" + conversionValue
				+ ", unitRemarks=" + unitRemarks + "]";
	}

}
