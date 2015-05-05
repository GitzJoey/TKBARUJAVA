package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.ProductUnit;

public interface ProductUnitDAO {
	public List<ProductUnit> getAllProductUnit();
	public ProductUnit getProductUnitById(int selectedId);
	public ProductUnit getProductUnitByProductIdByUnitCode(int selectedId, String unitCode);
	public ProductUnit getProductUnitByIsBase(int productId);
	public List<ProductUnit> getProductUnitByIds(String selectedIdINClause);
	public void addProductUnit(ProductUnit prod);
	public void editProductUnit(ProductUnit prod);
	public void deleteProductUnit(int selectedId);

}
