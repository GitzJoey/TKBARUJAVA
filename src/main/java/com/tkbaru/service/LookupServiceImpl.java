package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.LookupDAO;
import com.tkbaru.model.Lookup;

public class LookupServiceImpl implements LookupService {

	@Autowired
	LookupDAO lookupDAO;
	
	@Override
	public List<Lookup> getLookupByCategory(String categoryName) {
		List<Lookup> result = new ArrayList<Lookup>();
		
		result = lookupDAO.getLookupByCategory(categoryName);
		
		return result;
	}

}
