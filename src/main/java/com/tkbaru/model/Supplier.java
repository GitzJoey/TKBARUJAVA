package com.tkbaru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

import com.tkbaru.common.Converter;

@Entity
@Table(name="tb_supplier")
@SuppressWarnings("unchecked")
public class Supplier implements Serializable {
	private static final long serialVersionUID = -4629631293424685987L;

	public Supplier() {
		
	}

	@Id
	@Column(name="supplier_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer supplierId;
	@Column(name="supplier_name")
	private String supplierName;
	@Column(name="address")
	private String supplierAddress;
	@Column(name="city")
	private String supplierCity;
	@Column(name="remarks")
	private String supplierRemarks;
	@Column(name="phone")
	private String supplierPhone;
	@Column(name="fax")
	private String supplierFax;
	@Column(name="npwp_num")
	private String npwpNum;
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

	public String getProductIdList() {
		List<Integer> ids = new ArrayList<Integer>();
		
		for (Product p : this.prodList) {
			ids.add(p.getProductId());
		}
		
		return Converter.convertToCommaSeparated(ids);
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_supplier_bankacc", 
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="bankacc_id", referencedColumnName="bankacc_id")})
	private List<BankAccount> bankAccList = LazyList.decorate(new ArrayList<BankAccount>(), FactoryUtils.instantiateFactory(BankAccount.class));

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_supplier_pic", 
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="person_id", referencedColumnName="person_id")})
	private List<Person> picList = LazyList.decorate(new ArrayList<Person>(), FactoryUtils.instantiateFactory(Person.class));

	@ManyToMany
	@JoinTable(name="tb_supplier_prod",
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="product_id", referencedColumnName="product_id")})
	private List<Product> prodList = LazyList.decorate(new ArrayList<Product>(), FactoryUtils.instantiateFactory(Product.class));

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup supplierStatusLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store supplierStoreEntity;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getSupplierRemarks() {
		return supplierRemarks;
	}

	public void setSupplierRemarks(String supplierRemarks) {
		this.supplierRemarks = supplierRemarks;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}

	public String getNpwpNum() {
		return npwpNum;
	}

	public void setNpwpNum(String npwpNum) {
		this.npwpNum = npwpNum;
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

	public List<BankAccount> getBankAccList() {
		return bankAccList;
	}

	public void setBankAccList(List<BankAccount> bankAccList) {
		this.bankAccList = bankAccList;
	}

	public List<Person> getPicList() {
		return picList;
	}

	public void setPicList(List<Person> picList) {
		this.picList = picList;
	}

	public List<Product> getProdList() {
		return prodList;
	}

	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}

	public Lookup getSupplierStatusLookup() {
		return supplierStatusLookup;
	}

	public void setSupplierStatusLookup(Lookup supplierStatusLookup) {
		this.supplierStatusLookup = supplierStatusLookup;
	}

	public Store getSupplierStoreEntity() {
		return supplierStoreEntity;
	}

	public void setSupplierStoreEntity(Store supplierStoreEntity) {
		this.supplierStoreEntity = supplierStoreEntity;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierAddress="
				+ supplierAddress + ", supplierCity=" + supplierCity + ", supplierRemarks=" + supplierRemarks
				+ ", supplierPhone=" + supplierPhone + ", supplierFax=" + supplierFax + ", npwpNum=" + npwpNum
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", bankAccList=" + bankAccList + ", picList=" + picList
				+ ", prodList=" + prodList + ", supplierStatusLookup=" + supplierStatusLookup + ", supplierStoreEntity="
				+ supplierStoreEntity + "]";
	} 
	
}
