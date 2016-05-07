package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.WarehouseDAO;
import com.tkbaru.model.Warehouse;

@Repository
@SuppressWarnings("unchecked")
public class WarehouseDAOImpl implements WarehouseDAO {
	private static final Logger logger = LoggerFactory.getLogger(WarehouseDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	
	@Override
	public List<Warehouse> getAllWarehouse() {
		logger.info("[getAllWarehouse] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Warehouse> whList = session.createQuery("FROM Warehouse").list();
	
		logger.info("[getAllWarehouse] " + "Warehouse Count: " + whList.size());
		
		return whList;
	}

	@Override
	public Warehouse getWarehouseById(int selectedId) {
		logger.info("[getWarehouseById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Warehouse w = null;
        
        try {
        	w = (Warehouse) session.load(Warehouse.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("[getWarehouseById] " + "Warehouse loaded successfully, Warehouse details = " + w.toString());
        
        return w;
	}

	@Override
	public void addWarehouse(Warehouse warehouse) {
		logger.info("[addWarehouse] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        session.persist(warehouse);
        
        logger.info("[addWarehouse] " + "Warehouse added successfully, Warehouse Details = " + warehouse.toString());		
	}

	@Override
	public void editWarehouse(Warehouse warehouse) {
		logger.info("[editWarehouse] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	 
		session.update(warehouse);
	    
	    logger.info("[editWarehouse] " + "Warehouse updated successfully, Warehouse Details = " + warehouse.toString());			
	}

	@Override
	public void deleteWarehouse(int selectedId) {
		logger.info("[deleteWarehouse] " + "selectedId: " + selectedId);
		
        Session session = this.sessionFactory.getCurrentSession();
        Warehouse warehouse = (Warehouse) session.load(Warehouse.class, new Integer(selectedId));
        if(null != warehouse){
            session.delete(warehouse);
        }
        
        logger.info("[deleteWarehouse] " + "Warehouse deleted successfully, Warehouse details = " + warehouse.toString());
	}
}
