package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Lookup;

public interface LookupService {
	public List<Lookup> getAllLookup();
	public Lookup getLookupById(int selectedId);
	public List<Lookup> getLookupByCategory(String categoryName);
}
