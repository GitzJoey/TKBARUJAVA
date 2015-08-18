package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_store")
public class Store implements Serializable {
	private static final long serialVersionUID = 7580463652597779013L;

	public Store() {
		
	}

	@Id
	@GeneratedValue
	@Column(name="store_id")
	private int storeId;
	@Column(name="store_name")
	private String storeName;
	@Column(name="address_1")
	private String storeAddress1;
	@Column(name="address_2")
	private String storeAddress2;
	@Column(name="address_3")
	private String storeAddress3;
	@Column(name="is_default")
	private String isDefault;
	@Column(name="npwp_number")
	private String npwpNumber;
	@Column(name="status")
	private String storeStatus;
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
	@JoinColumn(name="is_default", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup storeStatusLookup;
	
	@ManyToOne
	@JoinColumn(name="is_default", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup isDefaultLookup;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress1() {
		return storeAddress1;
	}

	public void setStoreAddress1(String storeAddress1) {
		this.storeAddress1 = storeAddress1;
	}

	public String getStoreAddress2() {
		return storeAddress2;
	}

	public void setStoreAddress2(String storeAddress2) {
		this.storeAddress2 = storeAddress2;
	}

	public String getStoreAddress3() {
		return storeAddress3;
	}

	public void setStoreAddress3(String storeAddress3) {
		this.storeAddress3 = storeAddress3;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getNpwpNumber() {
		return npwpNumber;
	}

	public void setNpwpNumber(String npwpNumber) {
		this.npwpNumber = npwpNumber;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
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

	public Lookup getStoreStatusLookup() {
		return storeStatusLookup;
	}

	public void setStoreStatusLookup(Lookup storeStatusLookup) {
		this.storeStatusLookup = storeStatusLookup;
	}

	public Lookup getIsDefaultLookup() {
		return isDefaultLookup;
	}

	public void setIsDefaultLookup(Lookup isDefaultLookup) {
		this.isDefaultLookup = isDefaultLookup;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", storeName=" + storeName
				+ ", storeAddress1=" + storeAddress1 + ", storeAddress2="
				+ storeAddress2 + ", storeAddress3=" + storeAddress3
				+ ", isDefault=" + isDefault + ", npwpNumber=" + npwpNumber
				+ ", storeStatus=" + storeStatus + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}

}
