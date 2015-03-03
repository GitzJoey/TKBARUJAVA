package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StoreDAO;
import com.tkbaru.model.Store;

@Service
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
		
		if (store.getIsDefault().equals("L003_YES") && getDefaultStore() != null) {
			setAllStoreIsDefaultNo();
		}
		
		storeDAO.addStore(store);
	}

	@Override
	@Transactional
	public void editStore(Store store) {

		if (store.getIsDefault().equals("L003_YES") 
				&& getDefaultStore() != null
				&& store.getStoreId() != getDefaultStore().getStoreId()) {
			setAllStoreIsDefaultNo(store);
		} else {
			storeDAO.editStore(store);
		}
	}

	@Override
	@Transactional
	public void deleteStore(int selectedId) {
		
		storeDAO.deleteStore(selectedId);
	}

	@Override
	public Store getDefaultStore() {
		
		return storeDAO.getDefaultStore();
	}

	private void setAllStoreIsDefaultNo() {
		List<Store> all = getAllStore();
		
		for (Store s:all) {
			s.setIsDefault("L003_NO");
		}
		
		storeDAO.batchEditStore(all);
	}

	private void setAllStoreIsDefaultNo(Store except) {
		List<Store> all = getAllStore();
		
		for (Store s:all) {
			if (s.getStoreId() == except.getStoreId()) {
				s.setStoreName(except.getStoreName());
				s.setStoreAddress1(except.getStoreAddress1());
				s.setStoreAddress2(except.getStoreAddress2());
				s.setStoreAddress3(except.getStoreAddress3());
				s.setIsDefault(except.getIsDefault());
				s.setNpwpNumber(except.getNpwpNumber());
				s.setStoreStatus(except.getStoreStatus());
			} else {
				s.setIsDefault("L003_NO");
			}
		}
		
		storeDAO.batchEditStore(all);
	}
}
