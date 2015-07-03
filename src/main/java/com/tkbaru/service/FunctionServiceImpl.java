package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.common.Converter;
import com.tkbaru.dao.FunctionDAO;
import com.tkbaru.model.Function;

public class FunctionServiceImpl implements FunctionService {

	@Autowired
	FunctionDAO functionDAO;	
	
	@Override
	public List<Function> getAllFunctions() {
		List<Function> lf = functionDAO.getAllFunctions(); 
		
		return lf;
	}

	@Override
	public Function getFunctionById(int selectedId) {
		Function f = functionDAO.getFunctionById(selectedId);
		
		return f;
	}

	@Override
	public void addFunction(Function func) {

		functionDAO.addFunction(func);
	}

	@Override
	public void editFunction(Function func) {
		
		functionDAO.editFunction(func);
	}

	@Override
	public void deleteFunction(int selectedId) {
		
		functionDAO.deleteFunction(selectedId);
	}

	@Override
	public List<Function> getFunctionById(List<Integer> selectedIds) {
		List<Function> lfs = functionDAO.getFunctionById("(" + Converter.convertToCommaSeparated(selectedIds) + ")");

		return lfs;
	}

}
