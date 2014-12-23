package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		logger.info("[getAllProduct] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<Product> productList = session.createQuery("FROM Product").list();
	
		for(Product prod:productList) {
			logger.info("Product : " + prod.toString());
		}
		
		return productList;
	}

	@Override
	public Product getProductById(int selectedId) {
		logger.info("[getProductById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Product prod = null;
        
        try {
        	prod = (Product) session.load(Product.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
                
        return prod;	
	}

	@Override
	public void addProduct(Product prod) {
		logger.info("[addProduct] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(prod);		
	}

	@Override
	public void editProduct(Product prod) {
		logger.info("[editProduct] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(prod);		
	}

	@Override
	public void deleteProduct(int selectedId) {
		logger.info("[deleteProduct] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, new Integer(selectedId));
        if(null != product){
            session.delete(product);
        }		
	}

}
