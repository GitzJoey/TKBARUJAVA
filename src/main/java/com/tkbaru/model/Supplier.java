package com.tkbaru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_supplier")
@SuppressWarnings("unchecked")
public class Supplier {
	public Supplier() {
		
	}

	@Id
	@Column(name="supplier_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int supplierId;
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
	@Column(name="status")
	private String supplierStatus;
	@Column(name="npwp_num")
	private Integer npwpNum;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_supplier_bankacc", 
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="bankacc_id", referencedColumnName="bankacc_id")})
	private List<BankAccount> bankAccList;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_supplier_pic", 
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="person_id", referencedColumnName="person_id")})
	private List<Person> picList;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="tb_supplier_prod",
				joinColumns={@JoinColumn(name="supplier_id", referencedColumnName="supplier_id")},
				inverseJoinColumns={@JoinColumn(name="product_id", referencedColumnName="product_id")})
	private List<Product> prodList;

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
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

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public Integer getNpwpNum() {
		return npwpNum;
	}

	public void setNpwpNum(Integer npwpNum) {
		this.npwpNum = npwpNum;
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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName="
				+ supplierName + ", supplierAddress=" + supplierAddress
				+ ", supplierCity=" + supplierCity + ", supplierRemarks="
				+ supplierRemarks + ", supplierPhone=" + supplierPhone
				+ ", supplierFax=" + supplierFax + ", supplierStatus="
				+ supplierStatus + ", npwpNum=" + npwpNum + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", bankAccList="
				+ bankAccList + ", picList=" + picList + ", prodList="
				+ prodList + "]";
	}

}
