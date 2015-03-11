package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Lookup;

public interface LookupDAO {
	public List<Lookup> getAllLookup();
	public Lookup getLookupById(int selectedId);
	public void addLookup(Lookup lookup);
	public void editLookup(Lookup lookup);
	public void deleteLookup(int selectedId);

	public List<Lookup> getLookupByCategory(String categoryCode, String languageCode);
	public List<Lookup> getLookupByCategories(String categoryCodes, String languageCode);
	public Lookup getLookupByKey(String lookupKey, String languageCode);
	public List<Lookup> getLookupByLookupKeys(String lookupKeys, String languageCode);
	public List<Lookup> getAllCategory();
}
