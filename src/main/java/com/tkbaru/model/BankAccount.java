package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_bankacc")
public class BankAccount {
	public BankAccount() {
		
	}
	
	@Id
	@Column(name="bankacc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bankAccId;
	@Column(name="short_name")
	private String shortName;
	@Column(name="bank_name")
	private String bankName;
	@Column(name="account")
	private int accNum;
	@Column(name="remarks")
	private String bankRemarks;
	@Column(name="status")
	private String bankStatus;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
	public int getBankAccId() {
		return bankAccId;
	}
	public void setBankAccId(int bankAccId) {
		this.bankAccId = bankAccId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public String getBankRemarks() {
		return bankRemarks;
	}
	public void setBankRemarks(String bankRemarks) {
		this.bankRemarks = bankRemarks;
	}
	public String getBankStatus() {
		return bankStatus;
	}
	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
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
	@Override
	public String toString() {
		return "BankAccount [bankAccId=" + bankAccId + ", shortName="
				+ shortName + ", bankName=" + bankName + ", accNum=" + accNum
				+ ", bankRemarks=" + bankRemarks + ", bankStatus=" + bankStatus
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ "]";
	}
}
