package com.tkbaru.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name="base_unit")
	private String baseUnit;
	@Transient
	private MultipartFile imageBinary;
	@Column(name="image_path")
	private String imagePath;
	@Column(name="status")
	private String productStatus;

	@OneToMany(mappedBy="productEntity", cascade=CascadeType.ALL)
	private List<ProductUnit> productUnit;

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

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
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

	public List<ProductUnit> getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(List<ProductUnit> productUnit) {
		this.productUnit = productUnit;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productType="
				+ productType + ", shortCode=" + shortCode + ", productName="
				+ productName + ", productDesc=" + productDesc + ", baseUnit="
				+ baseUnit + ", imageBinary=" + imageBinary + ", imagePath="
				+ imagePath + ", productStatus=" + productStatus
				+ ", productUnit=" + productUnit + "]";
	}
	
}
