package com.tkbaru.model.report;

public class StocksReportView {

	private Long prodQuantity;
	private Long currentQuantity;
	private String storeName;
	private String storeAddress1;

	private String storeAddress2;

	private String storeAddress3;

	private String npwpNumber;

	private String storePhone;
	private String productName;
	private String uom;
	private String warehouseName;

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public Long getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Long currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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

	public String getNpwpNumber() {
		return npwpNumber;
	}

	public void setNpwpNumber(String npwpNumber) {
		this.npwpNumber = npwpNumber;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

}
