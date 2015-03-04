package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Truck;

@Repository
@SuppressWarnings("unchecked")
public class TruckDAOImpl implements TruckDAO {

	private static final Logger logger = LoggerFactory.getLogger(TruckDAOImpl.class);
        
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<Truck> getAllTruck() {
		logger.info("[getAllTruck] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		List<Truck> truckList = session.createQuery("FROM Truck").list();
			
		return truckList;
	}

	@Override
	public Truck getTruckById(int id) {
		logger.info("[getTruckById] id = {}", id);
		Truck truck = null;
		Session session = this.sessionFactory.getCurrentSession();
		truck = (Truck) session.get(Truck.class, id);
		return truck;
	}

	@Override
	public void addTruck(Truck truck) {
		logger.info("[addTruck] " + "");
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(truck);				
	}

	@Override
	public void editTruck(Truck truck) {
		logger.info("[editTruck] " + "");
        Session session = this.sessionFactory.getCurrentSession();
        session.update(truck);				
	}

	@Override
	public void deleteTruck(int id) {
		logger.info("[deleteTruck] id = {}", id);
		
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = (Truck) session.load(Truck.class, id);
        if(null != truck){
            session.delete(truck);
        }				
	}	
}
