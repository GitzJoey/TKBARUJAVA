package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Function;

public interface FunctionService {
	public List<Function> getAllFunctions();
	public Function getFunctionById(int selectedId);
}
