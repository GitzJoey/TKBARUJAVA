package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StoreDAO;
import com.tkbaru.model.Store;

@Repository
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDAO storeDAO;
	
	@Override
	@Transactional
	public List<Store> getAllStore() {
		
		return storeDAO.getAllStore();
	}

	@Override
	@Transactional
	public Store getStoreById(int selectedId) {
		
		return storeDAO.getStoreById(selectedId);
	}

	@Override
	@Transactional
	public void addStore(Store store) {
		
		storeDAO.addStore(store);
	}

	@Override
	@Transactional
	public void editStore(Store store) {
		
		storeDAO.editStore(store);
	}

	@Override
	@Transactional
	public void deleteStore(int selectedId) {
		
		storeDAO.deleteStore(selectedId);
	}

}
