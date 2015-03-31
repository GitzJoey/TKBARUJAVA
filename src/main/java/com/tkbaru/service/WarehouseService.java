package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Warehouse;

public interface WarehouseService {
	public List<Warehouse> getAllWarehouse();
	public Warehouse getWarehouseById(int selectedId);
	public void addWarehouse(Warehouse warehouse);
	public void editWarehouse(Warehouse warehouse);
	public void deleteWarehouse(int selectedId);

}
