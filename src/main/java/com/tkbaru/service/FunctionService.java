package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Function;

public interface FunctionService {
	public List<Function> getAllFunctions();
	public Function getFunctionById(int selectedId);
	public List<Function> getFunctionById(List<Integer> selectedIds);
	public void addFunction(Function func);
	public void editFunction(Function func);
	public void deleteFunction(int selectedId);
}
