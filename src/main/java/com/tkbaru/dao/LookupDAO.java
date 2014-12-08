package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Lookup;

public interface LookupDAO {
	public Lookup getLookupById(int selectedId);
	public List<Lookup> getAllLookup();
	public List<Lookup> getLookupByCategory(String categoryCode);
	public List<Lookup> getAllCategory();
	public void addLookup(Lookup lookup);
	public void editLookup(Lookup lookup);
	public void deleteLookup(int selectedId);
}
