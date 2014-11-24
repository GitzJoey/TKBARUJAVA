package com.tkbaru.service;

import com.tkbaru.model.Supplier;

public interface SupplierService {
	public Supplier getSupplierById(int selectedId);
	public void addNewSupplier(Supplier supplier);
	public void editSupplier(Supplier supplier);
}
