package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.TruckVendor;

public interface TruckVendorDAO {
	public List<TruckVendor> getAllTruckVendor();
	public TruckVendor getTruckVendorById(int selectedId);
	public void addTruckVendor(TruckVendor truckVendor);
	public void editTruckVendor(TruckVendor truckVendor);
	public void deleteTruckVendor(int selectedId);
}
