package com.tkbaru.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Supplier;

@Repository
public class SupplierDAOImpl implements SupplierDAO {

	private static final Logger logger = LoggerFactory.getLogger(SupplierDAOImpl.class);

	@Autowired
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    private BasicDataSource dbcpDataSource;
    public void setDbcpDataSource(BasicDataSource dbcpDataSource) {
    	this.dbcpDataSource = dbcpDataSource;
    }
    
	@Override
	public Supplier getSupplierById(int selectedId) {
        Session session = this.sessionFactory.getCurrentSession();     
        Supplier supp = (Supplier) session.load(Supplier.class, new Integer(selectedId));
        logger.info("Supplier loaded successfully, Supplier details = " + supp.toString());
        return supp;	
	}

	@Override
	public void addNewSupplier(Supplier supplier) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(supplier);
        logger.info("Supplier added successfully, Supplier Details = " + supplier.toString());
		
	}

	@Override
	public void editSupplier(Supplier supplier) {
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(supplier);
	    logger.info("Supplier updated successfully, Supplier Details = " + supplier.toString());		
	}

	@Override
	public List<Supplier> getAllSupplier() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Supplier> supplierList = session.createQuery("FROM Supplier").list();
	
		logger.info("" + supplierList.get(0).getBankAccList().get(0).getBankAccDetail().toString());
		return supplierList;
	}

	@Override
	public void deleteSupplier(int selectedId) {
        Session session = this.sessionFactory.getCurrentSession();
        Supplier supplier = (Supplier) session.load(Supplier.class, new Integer(selectedId));
        if(null != supplier){
            session.delete(supplier);
        }
        logger.info("Supplier deleted successfully, Supplier details = " + supplier.toString());		
	}

}
