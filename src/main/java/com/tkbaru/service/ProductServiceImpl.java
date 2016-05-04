package com.tkbaru.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.Converter;
import com.tkbaru.common.RandomProvider;
import com.tkbaru.dao.ProductDAO;
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
			if (product.getImageBinary() != null) {
				RandomProvider rndm = new RandomProvider(100000, 999999);

				String fileName = product.getProductName().replace(" ", "-") + "-" + 
									rndm.generateRandomInString() + "." + 
									FilenameUtils.getExtension(product.getImageBinary().getOriginalFilename());
				product.getImageBinary().transferTo(new File(cdnFolder + fileName).getAbsoluteFile());
				
				product.setImagePath(fileName);
				logger.info("[addProduct] " + "Adding product image: " + fileName);
			} else {
				logger.info("[addProduct] " + "Not adding product image.");
			}
		} catch (IllegalStateException e) {
			logger.info("[addProduct] " + "IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("[addProduct] " + "IOException: " + e.getMessage());
		} catch (Exception e) {
			logger.info("[addProduct] " + "Exception: " + e.getMessage());
		}
		productDAO.addProduct(product);
	}

	@Override
	@Transactional
	public void editProduct(Product product) {
		try {			
			logger.info("[editProduct] " + "product.getImagePath(): " + product.getImagePath());
			logger.info("[editProduct] " + "product.getImageBinary(): " + product.getImageBinary() == null ? "null":product.getImageBinary().toString());
			logger.info("[editProduct] " + "product.getImageBinary().isEmpty(): " + String.valueOf(product.getImageBinary().isEmpty()));
			
			if (product.getImagePath() != null && !product.getImageBinary().isEmpty()) {				
				RandomProvider rndm = new RandomProvider(100000, 999999);
				
				String fileName = product.getProductName().replaceAll(" ", "-") + "-" + 
									rndm.generateRandomInString() + "." +
									FilenameUtils.getExtension(product.getImageBinary().getOriginalFilename());
				product.getImageBinary().transferTo(new File(cdnFolder + fileName).getAbsoluteFile());
				
				product.setImagePath(fileName);
				logger.info("[editProduct] " + "Product image changed to: " + fileName);
			}
			else {
				logger.info("[editProduct] " + "Product image not changed.");
			}			
		} catch (IllegalStateException e) {
			logger.info("[editProduct] " + "IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("[editProduct] " + "IOException: " + e.getMessage());
		} catch (Exception e) {
			logger.info("[editProduct] " + "Exception: " + e.getMessage());
		}
		productDAO.editProduct(product);
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
