package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Supplier;

@Repository
@SuppressWarnings("unchecked")
public class SupplierDAOImpl implements SupplierDAO {
	private static final Logger logger = LoggerFactory.getLogger(SupplierDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public Supplier getSupplierById(int selectedId) {
		logger.info("[getSupplierById] " + "selectedId: " + selectedId);
		
        Session session = this.sessionFactory.getCurrentSession();     
        Supplier supp = null;
        try {
        	supp = (Supplier) session.load(Supplier.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }

        logger.info("Supplier loaded successfully, Supplier details = " + supp.toString());

        return supp;	
	}

	@Override
	public void addNewSupplier(Supplier supplier) {
		logger.info("[addNewSupplier] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(supplier);
	}

	@Override
	public void editSupplier(Supplier supplier) {
		logger.info("[editSupplier] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(supplier);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		logger.info("[getAllSupplier] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Supplier> supplierList = session.createQuery("FROM Supplier").list();

		for(Supplier supp:supplierList) {
			logger.info("Supplier : " + supp.toString());
		}
		return supplierList;
	}

	@Override
	public void deleteSupplier(int selectedId) {
		logger.info("[deleteSupplier] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Supplier supplier = (Supplier) session.load(Supplier.class, new Integer(selectedId));
        if(null != supplier){
            session.delete(supplier);
        }
	}

}
