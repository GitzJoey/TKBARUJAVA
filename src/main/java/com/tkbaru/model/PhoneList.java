package com.tkbaru.model;

public class PhoneList {
	public PhoneList() {
		
	}

	private int phoneListId;
	private String providerName;
	private int phoneNumber;
	private String phoneStatus;
	private String phoneNumRemarks;
	
	public int getPhoneListId() {
		return phoneListId;
	}
	public void setPhoneListId(int phoneListId) {
		this.phoneListId = phoneListId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneStatus() {
		return phoneStatus;
	}
	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}
	public String getPhoneNumRemarks() {
		return phoneNumRemarks;
	}
	public void setPhoneNumRemarks(String phoneNumRemarks) {
		this.phoneNumRemarks = phoneNumRemarks;
	}
	
}
