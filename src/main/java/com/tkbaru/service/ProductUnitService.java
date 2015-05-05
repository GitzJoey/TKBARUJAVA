package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.ProductUnit;

public interface ProductUnitService {
	public List<ProductUnit> getAllProductUnit();
	public ProductUnit getProductUnitByProductIdByUnitCode(int productId, String unitCode);
	public ProductUnit getProductUnitByIsBase(int productId);
	public List<ProductUnit> getProductUnitByIds(String selectedIds);
	public void addProductUnit(ProductUnit product);
	public void editProductUnit(ProductUnit product);
	public void deleteProductUnit(int selectedId);


}
