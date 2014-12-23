package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_product")
public class Product {
	public Product() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private int productId;
	@Column(name="product_type")
	private String productType;
	@Column(name="short_code")
	private String shortCode;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_description")
	private String productDesc;
	@Column(name="unit")
	private String unit;
	@Column(name="status")
	private String productStatus;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productType="
				+ productType + ", shortCode=" + shortCode + ", productName="
				+ productName + ", productDesc=" + productDesc + ", unit="
				+ unit + ", productStatus=" + productStatus + "]";
	}
}
