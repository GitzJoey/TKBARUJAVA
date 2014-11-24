package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.FunctionDAO;
import com.tkbaru.model.Function;

public class FunctionServiceImpl implements FunctionService {

	@Autowired
	FunctionDAO functionDAO;
	
	@Override
	public List<Function> getAllFunctions() {
		
		return functionDAO.getAllFunctions();
	}

	@Override
	public Function getFunctionById(int selectedId) {
		
		return functionDAO.getFunctionById(selectedId);
	}

}
