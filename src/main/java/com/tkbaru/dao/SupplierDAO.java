package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Supplier;

public interface SupplierDAO {
	public List<Supplier> getAllSupplier();
	public Supplier getSupplierById(int selectedId);
	public void addNewSupplier(Supplier supplier);
	public void editSupplier(Supplier supplier);
	public void deleteSupplier(int selectedId);
}
