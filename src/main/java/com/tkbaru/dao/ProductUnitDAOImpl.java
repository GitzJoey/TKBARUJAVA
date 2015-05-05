package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.ProductUnit;

@Repository
@SuppressWarnings("unchecked")
public class ProductUnitDAOImpl implements ProductUnitDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProductUnitDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
	public List<ProductUnit> getAllProductUnit() {
		logger.info("[getAllProductUnit] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<ProductUnit> productList = session.createQuery("FROM ProductUnit").list();
	
		for(ProductUnit prod:productList) {
			logger.info("ProductUnit : " + prod.toString());
		}
		
		return productList;
	}

	@Override
	public ProductUnit getProductUnitById(int selectedId) {
		logger.info("[getProductUnitById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		ProductUnit prod = null;
        
        try {
        	prod = (ProductUnit) session.load(ProductUnit.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("ProductUnit loaded successfully, ProductUnit details = " + prod.toString());
                
        return prod;	
	}

	@Override
	public void addProductUnit(ProductUnit prod) {
		logger.info("[addProductUnit] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(prod);		
	}

	@Override
	public void editProductUnit(ProductUnit prod) {
		logger.info("[editProductUnit] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(prod);		
	}

	@Override
	public void deleteProductUnit(int selectedId) {
		logger.info("[deleteProductUnit] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        ProductUnit product = (ProductUnit) session.load(ProductUnit.class, new Integer(selectedId));
        if(null != product){
            session.delete(product);
        }		
	}

	@Override
	public List<ProductUnit> getProductUnitByIds(String selectedIdINClause) {
		logger.info("[getProductUnitByIds] " + "selectedIdINClause: " + selectedIdINClause);
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<ProductUnit> productList = session.createQuery("FROM ProductUnit").list();
	
		for(ProductUnit prod:productList) {
			logger.info("ProductUnit : " + prod.toString());
		}
		
		return productList;
	}

	@Override
	public ProductUnit getProductUnitByIsBase(int productId) {
		logger.info("[getProductUnitById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		ProductUnit prod = null;
        
        try {
        	prod = (ProductUnit) session.createQuery("FROM ProductUnit pu where pu.productEntity.productId = :productId and pu.baseUnit = 1").setParameter("productId", productId).uniqueResult();
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("ProductUnit loaded successfully, ProductUnit details = " + prod.toString());
                
        return prod;	
	}

	@Override
	public ProductUnit getProductUnitByProductIdByUnitCode(int productId, String unitCode) {
		logger.info("[getProductUnitById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		ProductUnit prod = null;
        
        try {
        	prod = (ProductUnit) session.createQuery("FROM ProductUnit pu where pu.productEntity.productId = :productId and pu.unitCode = :unitCode").setParameter("productId", productId).setParameter("unitCode", unitCode).uniqueResult();
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
      //  logger.info("ProductUnit loaded successfully, ProductUnit details = " + prod.toString());
                
        return prod;	
	}

	
}
