package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Product;

public interface ProductService {
	public List<Product> getAllCustomer();
	public Product getProductById(int selectedId);
	public void addProduct(Product customer);
	public void editProduct(Product customer);
	public void deleteProduct(int selectedId);

}
