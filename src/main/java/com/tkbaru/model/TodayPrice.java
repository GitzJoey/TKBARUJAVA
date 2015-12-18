package com.tkbaru.model;

import java.util.Date;
import java.util.List;

public class TodayPrice {
	public TodayPrice() {
		
	}
	
	public TodayPrice(Date inputDate, List<Stocks> stocksList) {
		this.stocksList = stocksList;
	}
	
	private Date inputDate;
	private List<Stocks> stocksList;
	private Integer selectedStockId;
	private List<PriceLevel> priceLevelList;
	
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public List<Stocks> getStocksList() {
		return stocksList;
	}

	public void setStocksList(List<Stocks> stocksList) {
		this.stocksList = stocksList;
	}

	public Integer getSelectedStockId() {
		return selectedStockId;
	}

	public void setSelectedStockId(Integer selectedStockId) {
		this.selectedStockId = selectedStockId;
	}

	public List<PriceLevel> getPriceLevelList() {
		return priceLevelList;
	}

	public void setPriceLevelList(List<PriceLevel> priceLevelList) {
		this.priceLevelList = priceLevelList;
	}

	@Override
	public String toString() {
		return "TodayPrice [inputDate=" + inputDate + ", stocksList=" + stocksList + ", selectedStockId="
				+ selectedStockId + ", priceLevelList=" + priceLevelList + "]";
	}
	
}
