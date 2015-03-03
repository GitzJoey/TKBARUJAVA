package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Product;

public interface ProductDAO {
	public List<Product> getAllProduct();
	public Product getProductById(int selectedId);
	public List<Product> getProductByIds(String selectedIdINClause);
	public void addProduct(Product prod);
	public void editProduct(Product prod);
	public void deleteProduct(int selectedId);
}
