package com.tkbaru.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.StoreDAO;
import com.tkbaru.model.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	LookupService lookupManager;
	
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
		
		if (store.getIsDefaultLookup().getLookupKey().equals("L003_YES") && getDefaultStore() != null) {
			setAllStoreIsDefaultNo();
		}
		
		storeDAO.addStore(store);
	}

	@Override
	@Transactional
	public void editStore(Store store) {

		if (store.getIsDefaultLookup().getLookupKey().equals("L003_YES") 
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
	@Transactional
	public Store getDefaultStore() {
		
		return storeDAO.getDefaultStore();
	}

	private void setAllStoreIsDefaultNo() {
		List<Store> all = getAllStore();
		
		for (Store s:all) {
			s.setIsDefaultLookup(lookupManager.getLookupByKey("L003_NO"));
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
				s.setIsDefaultLookup(except.getIsDefaultLookup());
				s.setNpwpNumber(except.getNpwpNumber());
				s.setStoreStatusLookup(except.getStoreStatusLookup());
			} else {
				s.setIsDefaultLookup(lookupManager.getLookupByKey("L003_NO"));
			}
		}
		
		storeDAO.batchEditStore(all);
	}

	@Override
	@Transactional
	public void generateDefaultStore() {
		Store s1 = new Store();
		Store s2 = new Store();
		
		s1.setStoreName("Toko Baru");
		s1.setIsDefaultLookup(lookupManager.getLookupByKey("L003_YES"));
		s1.setStoreStatusLookup(lookupManager.getLookupByKey("L001_A"));
		s1.setNpwpNumber("0000-0000-0000-0000");
		s1.setStoreAddress1("Wangon");
		s1.setCreatedBy(0);
		s1.setCreatedDate(new Date());

		s2.setStoreName("Toko Baru-");
		s2.setIsDefaultLookup(lookupManager.getLookupByKey("L003_YES"));
		s2.setStoreStatusLookup(lookupManager.getLookupByKey("L001_A"));
		s2.setNpwpNumber("0000-0000-0000-0000");
		s2.setStoreAddress1("Wangon");
		s2.setCreatedBy(0);
		s2.setCreatedDate(new Date());

		addStore(s1);
		addStore(s2);
	}
}
