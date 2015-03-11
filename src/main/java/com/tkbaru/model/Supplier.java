package com.tkbaru.model;

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
	@Column(name="company_name")
	private String companyName;
	@Column(name="address")
	private String companyAddress;
	@Column(name="city")
	private String companyCity;
	@Column(name="remarks")
	private String supplierRemarks;
	@Column(name="phone")
	private String compPhone;
	@Column(name="fax")
	private String compFax;
	@Column(name="status")
	private String companyStatus;
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
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getSupplierRemarks() {
		return supplierRemarks;
	}

	public void setSupplierRemarks(String supplierRemarks) {
		this.supplierRemarks = supplierRemarks;
	}

	public String getCompPhone() {
		return compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	public String getCompFax() {
		return compFax;
	}

	public void setCompFax(String compFax) {
		this.compFax = compFax;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
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
		return "Supplier [supplierId=" + supplierId + ", companyName="
				+ companyName + ", companyAddress=" + companyAddress
				+ ", companyCity=" + companyCity + ", supplierRemarks="
				+ supplierRemarks + ", compPhone=" + compPhone + ", compFax="
				+ compFax + ", companyStatus=" + companyStatus + ", npwpNum="
				+ npwpNum + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", bankAccList=" + bankAccList + ", picList="
				+ picList + ", prodList=" + prodList + "]";
	}

}
