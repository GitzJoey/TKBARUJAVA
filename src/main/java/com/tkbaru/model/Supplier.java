package com.tkbaru.model;

import java.util.List;

public class Supplier {
	public Supplier() {
		
	}
	
	private int supplierId;
	private String companyName;
	private String companyAddress;
	private String companyCity;
	private String supplierRemarks;
	private String compPhone;
	private String compFax;
	private String companyStatus;
	
	private List<Person> picList;
	private List<BankAccount> bankAccList;
	
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
	public List<Person> getPicList() {
		return picList;
	}
	public void setPicList(List<Person> picList) {
		this.picList = picList;
	}
	public List<BankAccount> getBankAccList() {
		return bankAccList;
	}
	public void setBankAccList(List<BankAccount> bankAccList) {
		this.bankAccList = bankAccList;
	}
	
}
