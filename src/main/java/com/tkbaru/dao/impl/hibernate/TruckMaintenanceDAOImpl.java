package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.TruckMtcDAO;
import com.tkbaru.model.TruckMaintenance;

@Repository
@SuppressWarnings("unchecked")
public class TruckMaintenanceDAOImpl implements TruckMtcDAO {
	private static final Logger logger = LoggerFactory.getLogger(TruckMaintenanceDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override	
	public List<TruckMaintenance> getAllMaintenance() {
		logger.info("[getAllMaintenance] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<TruckMaintenance> mtcList = session.createQuery("FROM TruckMaintenance").list();
	
		logger.info("Maintenance Count: " + mtcList.size());
		
		return mtcList;
	}

	@Override
	public TruckMaintenance getMaintenanceById(int selectedId) {
		logger.info("[getMaintenanceById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		TruckMaintenance mtc = null;
        
        try {
        	mtc = (TruckMaintenance) session.load(TruckMaintenance.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Maintenance loaded successfully, Maintenance details = " + mtc.toString());
                
        return mtc;	
	}

	@Override
	public void addMaintenance(TruckMaintenance prod) {
		logger.info("[addMaintenance] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        session.persist(prod);		
	}

	@Override
	public void editMaintenance(TruckMaintenance prod) {
		logger.info("[editMaintenance] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    
		session.update(prod);		
	}

	@Override
	public void deleteMaintenance(int selectedId) {
		logger.info("[deleteMaintenance] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        TruckMaintenance mtc = (TruckMaintenance) session.load(TruckMaintenance.class, new Integer(selectedId));
        
        if(null != mtc){
            session.delete(mtc);
        }		
	}

	@Override
	public List<TruckMaintenance> getMaintenanceByIds(String selectedIdINClause) {
		logger.info("[getMaintenanceByIds] " + "selectedIdINClause: " + selectedIdINClause);
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<TruckMaintenance> mtcList = session.createQuery("FROM TruckMaintenance WHERE truckMaintenanceId IN " + selectedIdINClause).list();
	
		logger.info("Maintenance Count : " + mtcList.size());
		
		return mtcList;
	}


}
