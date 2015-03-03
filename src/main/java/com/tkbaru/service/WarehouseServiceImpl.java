package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.WarehouseDAO;
import com.tkbaru.model.Warehouse;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	WarehouseDAO warehouseDAO;
	
	@Override
	@Transactional
	public List<Warehouse> getAllWarehouse() {
		
		return warehouseDAO.getAllWarehouse();
	}

	@Override
	@Transactional
	public Warehouse getWarehouseById(int selectedId) {
		
		return warehouseDAO.getWarehouseById(selectedId);
	}

	@Override
	@Transactional
	public void addWarehouse(Warehouse warehouse) {
		
		warehouseDAO.addWarehouse(warehouse);
	}

	@Override
	@Transactional
	public void editWarehouse(Warehouse warehouse) {
		
		warehouseDAO.editWarehouse(warehouse);
	}

	@Override
	@Transactional
	public void deleteWarehouse(int selectedId) {
		
		warehouseDAO.deleteWarehouse(selectedId);
	}

}
