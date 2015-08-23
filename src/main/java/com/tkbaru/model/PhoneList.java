package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tb_phonelist")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhoneList implements Serializable{
	private static final long serialVersionUID = 190957030383617785L;

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
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup statusLookup;

	@ManyToOne
	@JoinColumn(name="provider", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup providerLookup;

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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}
	
	public Lookup getProviderLookup() {
		return providerLookup;
	}

	public void setProviderLookup(Lookup providerLookup) {
		this.providerLookup = providerLookup;
	}

	@Override
	public String toString() {
		return "PhoneList [phoneListId=" + phoneListId + ", providerName="
				+ providerName + ", phoneNumber=" + phoneNumber
				+ ", phoneStatus=" + phoneStatus + ", phoneNumRemarks="
				+ phoneNumRemarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}
