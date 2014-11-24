package com.tkbaru.dao;

import com.tkbaru.model.Supplier;

public interface SupplierDAO {
	public Supplier getSupplierById(int selectedId);
	public void addNewSupplier(Supplier supplier);
	public void editSupplier(Supplier supplier);
	
}
