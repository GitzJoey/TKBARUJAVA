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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 218642170890404843L;

	public Product() {
		
	}

	public Product(Integer productId) {
		this.productId = productId;
	}
	
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private Integer productId;
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

	@OneToMany(mappedBy="productEntity", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ProductUnit> productUnit;
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup productStatusLookup;
	
	@ManyToOne
	@JoinColumn(name="product_type", referencedColumnName="lookup_key")
	private Lookup productTypeLookup;

	@OneToMany(mappedBy="productEntity")
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Stocks> stocksList;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store productStoreEntity;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<ProductUnit> getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(List<ProductUnit> productUnit) {
		this.productUnit = productUnit;
	}

	public Lookup getProductStatusLookup() {
		return productStatusLookup;
	}

	public void setProductStatusLookup(Lookup productStatusLookup) {
		this.productStatusLookup = productStatusLookup;
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

	public Store getProductStoreEntity() {
		return productStoreEntity;
	}

	public void setProductStoreEntity(Store productStoreEntity) {
		this.productStoreEntity = productStoreEntity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", shortCode=" + shortCode + ", productName=" + productName
				+ ", productDesc=" + productDesc + ", imageBinary=" + imageBinary + ", imagePath=" + imagePath
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", productUnit=" + productUnit + ", productStatusLookup="
				+ productStatusLookup + ", productTypeLookup=" + productTypeLookup + ", stocksList=" + "stocksList"
				+ ", productStoreEntity=" + productStoreEntity + "]";
	}
	
}
