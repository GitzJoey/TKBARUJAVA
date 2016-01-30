package com.tkbaru.model.report;

public class ItemsReportView {

	/**
	 * 
	 */

	private String productName;
	
	private Long prodQuantity;

	private Long prodPrice;

	private String unitCode;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(Long prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public Long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	

}
