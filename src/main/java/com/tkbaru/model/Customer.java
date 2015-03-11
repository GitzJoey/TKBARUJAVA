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
@Table(name="tb_customer")
@SuppressWarnings("unchecked")
public class Customer {
	public Customer() {
		
	}
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;
	@Column(name="store_name")
	private String storeName;
	@Column(name="address")
	private String storeAddress;
	@Column(name="city")
	private String storeCity;
	@Column(name="phone")
	private String storePhone;
	@Column(name="status")
	private String storeStatus;
	@Column(name="remarks")
	private String storeRemarks;
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
	@JoinTable(name="tb_customer_bankacc", 
				joinColumns={@JoinColumn(name="customer_id", referencedColumnName="customer_id")},
				inverseJoinColumns={@JoinColumn(name="bankacc_id", referencedColumnName="bankacc_id")})
	private List<BankAccount> bankAccList = LazyList.decorate(new ArrayList<BankAccount>(), FactoryUtils.instantiateFactory(BankAccount.class));

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_customer_pic", 
				joinColumns={@JoinColumn(name="customer_id", referencedColumnName="customer_id")},
				inverseJoinColumns={@JoinColumn(name="person_id", referencedColumnName="person_id")})
	private List<Person> picList = LazyList.decorate(new ArrayList<Person>(), FactoryUtils.instantiateFactory(Person.class));

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getStoreRemarks() {
		return storeRemarks;
	}

	public void setStoreRemarks(String storeRemarks) {
		this.storeRemarks = storeRemarks;
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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", storeName="
				+ storeName + ", storeAddress=" + storeAddress + ", storeCity="
				+ storeCity + ", storePhone=" + storePhone + ", storeStatus="
				+ storeStatus + ", storeRemarks=" + storeRemarks + ", npwpNum="
				+ npwpNum + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", bankAccList=" + bankAccList + ", picList="
				+ picList + "]";
	}

}
