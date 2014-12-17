package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_supplier")
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
	
	@SuppressWarnings("unchecked")
	@OneToMany(mappedBy="supplierEnt")
	private List<SupplierPIC> picList = LazyList.decorate(new ArrayList<SupplierPIC>(), FactoryUtils.instantiateFactory(SupplierPIC.class));
	@SuppressWarnings("unchecked")
	@OneToMany(mappedBy="supplierEnt", cascade=CascadeType.ALL)
	private List<SupplierBankAccount> bankAccList = LazyList.decorate(new ArrayList<SupplierBankAccount>(), FactoryUtils.instantiateFactory(SupplierBankAccount.class));
	
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
	public List<SupplierPIC> getPicList() {
		return picList;
	}
	public void setPicList(List<SupplierPIC> picList) {
		this.picList = picList;
	}
	public List<SupplierBankAccount> getBankAccList() {
		return bankAccList;
	}
	public void setBankAccList(List<SupplierBankAccount> bankAccList) {
		this.bankAccList = bankAccList;
	}
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", companyName=" + companyName + ", companyAddress="
				+ companyAddress + ", companyCity=" + companyCity + ", supplierRemarks=" + supplierRemarks
				+ ", compPhone=" + compPhone + ", compFax=" + compFax + ", companyStatus=" + companyStatus
				+ ", picList=" + picList + ", bankAccList=" + bankAccList + "]";
	}	
}
