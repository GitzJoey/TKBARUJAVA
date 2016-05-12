
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="tb_customer")
@SuppressWarnings("unchecked")
@FilterDef(name="UserStore", parameters=@ParamDef(name="userStoreParam", type="integer"))
@Filter(name="UserStore", condition="store_id = :userStoreParam")
public class Customer implements Serializable {
	private static final long serialVersionUID = -6138044220608174337L;

	public Customer() {
		
	}
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="address")
	private String customerAddress;
	@Column(name="city")
	private String customerCity;
	@Column(name="phone")
	private String customerPhone;
	@Column(name="remarks")
	private String customerRemarks;
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
	@Column(name="payment_due_day")
	private Integer paymentDueDay;
	
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
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup customerStatusLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store customerStoreEntity;
	
	@OneToOne
	@JoinColumn(name="price_level_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private PriceLevel priceLevelEntity;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
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

	public Integer getPaymentDueDay() {
		return paymentDueDay;
	}

	public void setPaymentDueDay(Integer paymentDueDay) {
		this.paymentDueDay = paymentDueDay;
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

	public Lookup getCustomerStatusLookup() {
		return customerStatusLookup;
	}

	public void setCustomerStatusLookup(Lookup customerStatusLookup) {
		this.customerStatusLookup = customerStatusLookup;
	}

	public Store getCustomerStoreEntity() {
		return customerStoreEntity;
	}

	public void setCustomerStoreEntity(Store customerStoreEntity) {
		this.customerStoreEntity = customerStoreEntity;
	}

	public PriceLevel getPriceLevelEntity() {
		return priceLevelEntity;
	}

	public void setPriceLevelEntity(PriceLevel priceLevelEntity) {
		this.priceLevelEntity = priceLevelEntity;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerCity=" + customerCity + ", customerPhone=" + customerPhone
				+ ", customerRemarks=" + customerRemarks + ", npwpNum=" + npwpNum + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", paymentDueDay=" + paymentDueDay + ", bankAccList=" + bankAccList + ", picList=" + picList
				+ ", customerStatusLookup=" + customerStatusLookup + ", customerStoreEntity=" + customerStoreEntity
				+ ", priceLevelEntity=" + priceLevelEntity + "]";
	}

}
