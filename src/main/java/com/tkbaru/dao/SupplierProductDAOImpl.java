package com.tkbaru.dao;

import com.tkbaru.model.SupplierProduct;
import java.util.List;
import javax.persistence.NoResultException;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Erwin
 */
public class SupplierProductDAOImpl implements SupplierProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(SupplierProductDAOImpl.class);
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public List<SupplierProduct> getAllProductBySupplier(int supplierId) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            List<SupplierProduct> supplierProductList = session.createQuery("FROM SupplierProduct sp WHERE sp.supplierId = :supplierId")
                    .setParameter("supplierId", supplierId)
                    .list();
            return supplierProductList;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<SupplierProduct> getAllProductByProduct(int productId) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            List<SupplierProduct> supplierProductList = session.createQuery("FROM SupplierProduct sp WHERE sp.productId = :productId")
                    .setParameter("productId", productId)
                    .list();
            return supplierProductList;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public SupplierProduct getSupplierProductById(int id) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            SupplierProduct supplierProduct = null;
            try {
                supplierProduct = (SupplierProduct) session.load(SupplierProduct.class, new Integer(id));
            } catch (Exception err) {
                logger.info(err.getMessage());
            }
            logger.debug("SupplierProduct loaded successfully, SupplierProduct details = " + supplierProduct.toString());
            return supplierProduct;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void addProduct(SupplierProduct supplierProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(supplierProduct);
        logger.debug("Supplier Product saved successfully, Supplier Product details = " + supplierProduct.toString());
    }

    @Override
    public void editProduct(SupplierProduct supplierProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(supplierProduct);
        logger.debug("Supplier Product updated successfully, Supplier Product details = " + supplierProduct.toString());
    }

    @Override
    public void deleteProduct(int selectedId) {
        Session session = this.sessionFactory.getCurrentSession();
        SupplierProduct supplierProduct = (SupplierProduct) session.load(SupplierProduct.class, new Integer(selectedId));
        if(null != supplierProduct){
            session.delete(supplierProduct);
            logger.debug("Supplier Product deleted successfully, Supplier details = " + supplierProduct.toString());
        } else {
            logger.debug("Failed to delete Supplier Product, Supplier details = " + supplierProduct.toString());
        }
    }

}
