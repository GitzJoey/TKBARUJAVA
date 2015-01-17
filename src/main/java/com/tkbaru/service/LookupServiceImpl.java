package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.common.Converter;
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

	@Override
	public List<Lookup> getAllLookup() {

		return lookupDAO.getAllLookup();
	}

	@Override
	public Lookup getLookupById(int selectedId) {
		
		return lookupDAO.getLookupById(selectedId);
	}

	@Override
	public List<Lookup> getAllCategory() {
		
		return lookupDAO.getAllCategory();
	}

	@Override
	public void addLookup(Lookup lookup) {

		lookupDAO.addLookup(lookup);
	}

	@Override
	public void editLookup(Lookup lookup) {
		
		lookupDAO.editLookup(lookup);
	}

	@Override
	public void deleteLookup(int selectedId) {
		
		lookupDAO.deleteLookup(selectedId);
	}

	@Override
	public List<Lookup> getLookupByCategory(List<String> categoryNames) {

		String inClause = Converter.convertToINClause(categoryNames);
		
		return lookupDAO.getLookupByCategories(inClause.toUpperCase());
	}

	@Override
	public List<Lookup> getLookupByLookupCode(List<String> lookupCodes) {

		String inClause = Converter.convertToINClause(lookupCodes);
		
		return lookupDAO.getLookupByLookupCodes(inClause.toUpperCase());
	}

}
