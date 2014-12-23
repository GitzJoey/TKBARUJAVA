package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.ProductDAO;
import com.tkbaru.model.Product;

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	@Transactional
	public List<Product> getAllProduct() {
		
		return productDAO.getAllProduct();
	}

	@Override
	@Transactional
	public Product getProductById(int selectedId) {
		
		return productDAO.getProductById(selectedId);
	}

	@Override
	@Transactional
	public void addProduct(Product product) {

		productDAO.addProduct(product);
	}

	@Override
	@Transactional
	public void editProduct(Product product) {

		productDAO.editProduct(product);
	}

	@Override
	@Transactional
	public void deleteProduct(int selectedId) {

		productDAO.deleteProduct(selectedId);		
	}

}
