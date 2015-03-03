package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.Converter;
import com.tkbaru.dao.LookupDAO;
import com.tkbaru.model.Lookup;

@Service
public class LookupServiceImpl implements LookupService {

	@Autowired
	LookupDAO lookupDAO;
	
	private String defaultLanguageCode = "L010_EN";
	
	@Override
	@Transactional
	public List<Lookup> getLookupByCategory(String categoryName) {
		List<Lookup> result = new ArrayList<Lookup>();
		
		result = lookupDAO.getLookupByCategory(categoryName, defaultLanguageCode);
		
		return result;
	}

	@Override
	@Transactional
	public List<Lookup> getAllLookup() {

		return lookupDAO.getAllLookup();
	}

	@Override
	@Transactional
	public Lookup getLookupById(int selectedId) {
		
		return lookupDAO.getLookupById(selectedId);
	}

	@Override
	@Transactional
	public List<Lookup> getAllCategory() {
		
		return lookupDAO.getAllCategory();
	}

	@Override
	@Transactional
	public void addLookup(Lookup lookup) {

		lookupDAO.addLookup(lookup);
	}

	@Override
	@Transactional
	public void editLookup(Lookup lookup) {
		
		lookupDAO.editLookup(lookup);
	}

	@Override
	@Transactional
	public void deleteLookup(int selectedId) {
		
		lookupDAO.deleteLookup(selectedId);
	}

	@Override
	@Transactional
	public List<Lookup> getLookupByCategory(List<String> categoryNames) {

		String inClause = Converter.convertToINClause(categoryNames);
		
		return lookupDAO.getLookupByCategories(inClause.toUpperCase(), defaultLanguageCode);
	}

	@Override
	public List<Lookup> getLookupByLookupKeys(List<String> lookupKeys) {

		String inClause = Converter.convertToINClause(lookupKeys);
		
		return lookupDAO.getLookupByLookupKeys(inClause.toUpperCase(), defaultLanguageCode);
	}

}
