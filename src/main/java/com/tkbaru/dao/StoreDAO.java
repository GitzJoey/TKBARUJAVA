package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Store;

public interface StoreDAO {
	public List<Store> getAllStore();
	public Store getStoreById(int selectedId);
	public void addStore(Store store);
	public void editStore(Store store);
	public void deleteStore(int selectedId);

	public Store getDefaultStore();
	public void batchEditStore(List<Store> stores);
}
