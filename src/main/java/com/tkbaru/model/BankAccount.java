package com.tkbaru.model;

public class BankAccount {
	public BankAccount() {
		
	}
	
	private int bankAccId;
	private String shortName;
	private String bankName;
	private int accNum;
	private String bankRemarks;
	private String bankStatus;
	
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
	@Override
	public String toString() {
		return "BankAccount [bankAccId=" + bankAccId + ", shortName=" + shortName + ", bankName=" + bankName
				+ ", accNum=" + accNum + ", bankRemarks=" + bankRemarks + ", bankStatus=" + bankStatus + "]";
	}	
}
