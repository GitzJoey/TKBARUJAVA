package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Product;

import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

@Repository
@SuppressWarnings("unchecked")
public class ProductDAOImpl implements ProductDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
        
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
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
        
        logger.info("Product loaded successfully, Product details = " + prod.toString());
                
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

    @Override
    public List<Product> getAllProductBySupplierId(int supplierId) {
		List<Product> result = new ArrayList<Product>();
		String sqlquery = 
				"SELECT tbp.product_id,       		"+
				"       tbp.product_type,			"+
                "       tbp.short_code,				"+
				"		tbp.product_name,       	"+
				"       tbp.product_description,	"+
                "       tbp.unit,             		"+
                "       tbp.in_kg,             		"+
                "       tbp.image_path             	"+
				"FROM tb_product tbp          		"+
				"WHERE tbp.supplier_id = ?        	";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery, new Object[] { supplierId });
		for (Map<String, Object> row : rows) {
			Product p = new Product();
			p.setProductId(Integer.valueOf(String.valueOf(row.get("product_id"))));
                        p.setProductType(String.valueOf(row.get("product_type")));
                        p.setShortCode(String.valueOf(row.get("short_code")));
                        p.setProductName(String.valueOf(row.get("product_name")));
                        p.setProductDesc(String.valueOf(row.get("product_description")));
                        p.setUnit(String.valueOf(row.get("unit")));
                        p.setInKilo(Integer.valueOf(String.valueOf(row.get("in_kg"))));
                        p.setImagePath(String.valueOf(row.get("in_kg")));
			result.add(p);
		}

		return result;
    }

	@Override
	public List<Product> getProductByIds(String selectedIdINClause) {
		logger.info("[getProductByIds] " + "selectedIdINClause: " + selectedIdINClause);
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<Product> productList = session.createQuery("FROM Product").list();
	
		for(Product prod:productList) {
			logger.info("Product : " + prod.toString());
		}
		
		return productList;
	}

}
