package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Lookup;

public interface LookupDAO {
	public List<Lookup> getLookupByCategory(String categoryCode);
}
