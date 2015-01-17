package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Lookup;

public interface LookupService {
	public List<Lookup> getAllLookup();
	public Lookup getLookupById(int selectedId);
	public List<Lookup> getLookupByCategory(String categoryName);
	public List<Lookup> getLookupByCategory(List<String> categoryNames);
	public List<Lookup> getLookupByLookupCode(List<String> lookupCodes);
	public List<Lookup> getAllCategory();
	public void addLookup(Lookup lookup);
	public void editLookup(Lookup lookup);
	public void deleteLookup(int selectedId);
}
