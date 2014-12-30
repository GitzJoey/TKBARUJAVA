package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

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
	@Column(name="in_kg")
	private int inKilo;
	@Transient
	private MultipartFile imageBinary;
	@Column(name="image_path")
	private String imagePath;
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
	public int getInKilo() {
		return inKilo;
	}
	public void setInKilo(int inKilo) {
		this.inKilo = inKilo;
	}
	public MultipartFile getImageBinary() {
		return imageBinary;
	}
	public void setImageBinary(MultipartFile imageBinary) {
		this.imageBinary = imageBinary;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
				+ unit + ", imageBinary=" + imageBinary + ", imagePath="
				+ imagePath + ", productStatus=" + productStatus + "]";
	}	
}
