package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_store")
public class Store {
	public Store() {
		
	}

	@Id
	@GeneratedValue
	@Column(name="store_id")
	private int StoreId;
	@Column(name="store_name")
	private String StoreName;
	@Column(name="address_1")
	private String StoreAddress1;
	@Column(name="address_2")
	private String StoreAddress2;
	@Column(name="address_3")
	private String StoreAddress3;
	
	public int getStoreId() {
		return StoreId;
	}
	public void setStoreId(int storeId) {
		StoreId = storeId;
	}
	public String getStoreName() {
		return StoreName;
	}
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	public String getStoreAddress1() {
		return StoreAddress1;
	}
	public void setStoreAddress1(String storeAddress1) {
		StoreAddress1 = storeAddress1;
	}
	public String getStoreAddress2() {
		return StoreAddress2;
	}
	public void setStoreAddress2(String storeAddress2) {
		StoreAddress2 = storeAddress2;
	}
	public String getStoreAddress3() {
		return StoreAddress3;
	}
	public void setStoreAddress3(String storeAddress3) {
		StoreAddress3 = storeAddress3;
	}
	@Override
	public String toString() {
		return "Store [StoreId=" + StoreId + ", StoreName=" + StoreName
				+ ", StoreAddress1=" + StoreAddress1 + ", StoreAddress2="
				+ StoreAddress2 + ", StoreAddress3=" + StoreAddress3 + "]";
	}	
}
