package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.SupplierDAO;
import com.tkbaru.model.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDAO supplierDAO;
	
	@Override
	@Transactional
	public Supplier getSupplierById(int selectedId) {
		return this.supplierDAO.getSupplierById(selectedId);
	}

	@Override
	@Transactional
	public void addNewSupplier(Supplier supplier) {
		this.supplierDAO.addNewSupplier(supplier);
	}

	@Override
	@Transactional
	public void editSupplier(Supplier supplier) {
		this.supplierDAO.editSupplier(supplier);
	}

	@Override
	@Transactional
	public List<Supplier> getAllSupplier() {
		return this.supplierDAO.getAllSupplier();
	}

	@Override
	@Transactional
	public void deleteSupplier(int selectedId) {
		this.supplierDAO.deleteSupplier(selectedId);
	}

}
