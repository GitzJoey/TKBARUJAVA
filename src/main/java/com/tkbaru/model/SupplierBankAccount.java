package com.tkbaru.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_supplier_bankacc")
public class SupplierBankAccount {
	public SupplierBankAccount() {
		
	}
	
	@Id
	@Column(name="supplier_bankacc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int supplierBankAccountId;
	@Column(name="supplier_id")
	private int supplierId;
	@Column(name="bankacc_id")
	private int bankAccId;
	@Column(name="status")
	private String supplierBankAccountRemarks;
	
	@ManyToOne
	@JoinColumn(name="supplier_id", insertable=false, updatable=false)
	private Supplier supplierEnt;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bankacc_id", insertable=false, updatable=false)
	private BankAccount bankAccDetail;
	public BankAccount getBankAccDetail() {
		return bankAccDetail;
	}
	
	public int getSupplierBankAccountId() {
		return supplierBankAccountId;
	}
	public void setSupplierBankAccountId(int supplierBankAccountId) {
		this.supplierBankAccountId = supplierBankAccountId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getBankAccId() {
		return bankAccId;
	}
	public void setBankAccId(int bankAccId) {
		this.bankAccId = bankAccId;
	}
	public String getSupplierBankAccountRemarks() {
		return supplierBankAccountRemarks;
	}
	public void setSupplierBankAccountRemarks(String supplierBankAccountRemarks) {
		this.supplierBankAccountRemarks = supplierBankAccountRemarks;
	}
	
	@Override
	public String toString() {
		return "SupplierBankAccount [supplierBankAccountId=" + supplierBankAccountId + ", supplierId=" + supplierId
				+ ", bankAccId=" + bankAccId + ", supplierBankAccountRemarks=" + supplierBankAccountRemarks + "]";
	}
}
