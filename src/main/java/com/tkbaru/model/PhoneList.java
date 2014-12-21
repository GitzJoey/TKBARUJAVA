package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_phonelist")
public class PhoneList {
	public PhoneList() {
		
	}

	@Id
	@Column(name="phonelist_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int phoneListId;
	@Column(name="provider")
	private String providerName;
	@Column(name="number")
	private String phoneNumber;
	@Column(name="status")
	private String phoneStatus;
	@Column(name="remarks")
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
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
	@Override
	public String toString() {
		return "PhoneList [phoneListId=" + phoneListId + ", providerName=" + providerName + ", phoneNumber="
				+ phoneNumber + ", phoneStatus=" + phoneStatus + ", phoneNumRemarks=" + phoneNumRemarks + "]";
	}
}
