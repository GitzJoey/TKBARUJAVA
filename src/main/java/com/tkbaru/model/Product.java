package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tb_product")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {
	private static final long serialVersionUID = 218642170890404843L;

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
	@Transient
	private MultipartFile imageBinary;
	@Column(name="image_path")
	private String imagePath;
	@Column(name="status")
	private String productStatus;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@OneToMany(mappedBy="productEntity", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ProductUnit> productUnit;
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;
	
	@ManyToOne
	@JoinColumn(name="product_type", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup productTypeLookup;

	@OneToMany(mappedBy="productEntity")
	private List<Stocks> stocksList;

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

	public List<ProductUnit> getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(List<ProductUnit> productUnit) {
		this.productUnit = productUnit;
	}

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	public Lookup getProductTypeLookup() {
		return productTypeLookup;
	}

	public void setProductTypeLookup(Lookup productTypeLookup) {
		this.productTypeLookup = productTypeLookup;
	}

	public List<Stocks> getStocksList() {
		return stocksList;
	}

	public void setStocksList(List<Stocks> stocksList) {
		this.stocksList = stocksList;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productType=" + productType + ", shortCode=" + shortCode
				+ ", productName=" + productName + ", productDesc=" + productDesc + ", imageBinary=" + imageBinary
				+ ", imagePath=" + imagePath + ", productStatus=" + productStatus + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", productUnit=" + productUnit + "]";
	}
	
}
