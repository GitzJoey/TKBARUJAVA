package com.tkbaru.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.RandomProvider;
import com.tkbaru.dao.ProductUnitDAO;
import com.tkbaru.model.ProductUnit;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {

	@Autowired
	ProductUnitDAO productUnitDAO;

	@Autowired
	ServletContext servletContext;
	
	@Override
	@Transactional
	public List<ProductUnit> getAllProductUnit() {
		
		return productUnitDAO.getAllProductUnit();
	}

	@Override
	@Transactional
	public ProductUnit getProductUnitByProductIdByUnitCode(int selectedId, String unitCode) {
		
		return productUnitDAO.getProductUnitByProductIdByUnitCode(selectedId,unitCode);
	}

	@Override
	@Transactional
	public void addProductUnit(ProductUnit product) {
		
			
			productUnitDAO.addProductUnit(product);
		
	}

	@Override
	@Transactional
	public void editProductUnit(ProductUnit product) {
		
		productUnitDAO.editProductUnit(product);
	}

	@Override
	@Transactional
	public void deleteProductUnit(int selectedId) {

		productUnitDAO.deleteProductUnit(selectedId);		
	}

	@Override
	public List<ProductUnit> getProductUnitByIds(String selectedIds) {
		
		if (selectedIds.length() == 0) return new ArrayList<ProductUnit>();
		
		return productUnitDAO.getProductUnitByIds(selectedIds);
	}

	@Override
	@Transactional
	public ProductUnit getProductUnitByIsBase(int selectedId) {
		return productUnitDAO.getProductUnitByIsBase(selectedId);
	}



}
