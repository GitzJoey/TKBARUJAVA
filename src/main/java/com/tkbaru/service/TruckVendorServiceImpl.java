package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.TruckVendorDAO;
import com.tkbaru.model.TruckVendor;

public class TruckVendorServiceImpl implements TruckVendorService{

	@Autowired
	LookupService lookupManager;
	
	@Autowired
	TruckVendorDAO truckVendorDAO;
	
	@Override
	@Transactional
	public List<TruckVendor> getAllTruckVendor() {
		
		return truckVendorDAO.getAllTruckVendor();
	}

	@Override
	@Transactional
	public TruckVendor getTruckVendorById(int selectedId) {
		
		return truckVendorDAO.getTruckVendorById(selectedId);
	}

	@Override
	@Transactional
	public void addTruckVendor(TruckVendor truckVendor) {

		truckVendorDAO.addTruckVendor(truckVendor);
	}

	@Override
	@Transactional
	public void editTruckVendor(TruckVendor truckVendor) {
	
		truckVendorDAO.editTruckVendor(truckVendor);
	}

	@Override
	@Transactional
	public void deleteTruckVendor(int selectedId) {
		
		truckVendorDAO.deleteTruckVendor(selectedId);
	}
	
}
