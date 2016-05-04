package com.tkbaru.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.Converter;
import com.tkbaru.common.RandomProvider;
import com.tkbaru.dao.ProductDAO;
import com.tkbaru.dao.impl.hibernate.ProductDAOImpl;
import com.tkbaru.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDAO productDAO;

	@Autowired
	ServletContext servletContext;
	
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

	@Value("${app.settings.cdnfolder}")
	private String cdnFolder;
	
	@Override
	@Transactional
	public void addProduct(Product product) {
		try {
			logger.info("[addProduct] " + "product.getImageBinary()isEmpty(): " + product.getImageBinary().isEmpty());

			if (!product.getImageBinary().isEmpty()) {
				RandomProvider rndm = new RandomProvider(10000, 99999);
				Path p = Paths.get(product.getImageBinary().getOriginalFilename());
				
				String fileName = product.getProductName() + "-" + rndm.generateRandomInString() + "-" + p.getFileName();
				product.getImageBinary().transferTo(new File(cdnFolder + fileName).getAbsoluteFile());
				
				product.setImagePath(fileName);
				logger.info("[addProduct] " + "Adding product image: " + fileName);
			} else {
				logger.info("[addProduct] " + "Not adding product image.");
				productDAO.addProduct(product);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void editProduct(Product product) {
		try {			
			logger.info("[editProduct] " + "product.getImagePath(): " + product.getImagePath());
			logger.info("[editProduct] " + "product.getImageBinary()isEmpty(): " + product.getImageBinary().isEmpty());

			if (product.getImagePath() != null && !product.getImageBinary().isEmpty()) {
				
				
				RandomProvider rndm = new RandomProvider(10000, 99999);
				Path p = Paths.get(product.getImageBinary().getOriginalFilename());
				
				String fileName = product.getProductName().replaceAll(" ", "-") + "-" + rndm.generateRandomInString() + "-" + p.getFileName();
				product.getImageBinary().transferTo(new File(cdnFolder + fileName).getAbsoluteFile());
				
				product.setImagePath(fileName);
				logger.info("[editProduct] " + "Product image changed to: " + fileName);
			}
			else {
				logger.info("[editProduct] " + "Product image not changed.");
				productDAO.editProduct(product);
			}			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteProduct(int selectedId) {

		productDAO.deleteProduct(selectedId);		
	}

	@Override
	@Transactional
	public List<Product> getProductByIds(String selectedIds) {		
		if (selectedIds.length() == 0) return new ArrayList<Product>();
		
		return productDAO.getProductByIds(Converter.convertToINClause(selectedIds));
	}

	@Override
	@Transactional
	public List<Product> getProductHasInStock() {
		
		return productDAO.getProductHasInStock();
	}

}
