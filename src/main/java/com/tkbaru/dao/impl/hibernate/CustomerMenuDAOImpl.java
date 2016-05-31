package com.tkbaru.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.CustomerMenuDAO;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.TruckVendor;

@Repository
@SuppressWarnings("unchecked")
public class CustomerMenuDAOImpl implements CustomerMenuDAO {
	private static final Logger logger = LoggerFactory.getLogger(CustomerMenuDAOImpl.class);
	
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<SalesOrder> getSalesOrderWithDeliverId() {
		logger.info("[getAllSalesOrder] " + "");
		
		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		Session session = this.sessionFactory.getCurrentSession();
		/*
		List<Object[]> q = session.createQuery("SELECT so FROM SalesOrder so "
														+ "JOIN so.itemsList it "
														+ "JOIN it.deliverList d "
														+ "WHERE d.deliverId IS NOT NULL"
														+ " AND d.bruto IS NOT NULL").list();
		for (Object[] result : q) {
			SalesOrder so = (SalesOrder) result[0];
		    soList.add(so);
		}
`		*/
		soList = session.createQuery("SELECT DISTINCT so FROM SalesOrder so "
				+ "JOIN so.itemsList it "
				+ "JOIN it.deliverList d "
				+ "WHERE d.deliverId IS NOT NULL"
				+ " AND d.bruto IS NOT NULL").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		logger.info("[getAllSalesOrder] " + "SalesOrder Count: " + soList.size());
		return soList;
			
	}
	
	@Override
	public void editDeliver(SalesOrder salesOrder) {
		logger.info("[editSalesOrderr] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    
		session.update(salesOrder);
	    
	    logger.info("[editSalesOrder] " + "SalesOrder updated successfully, SalesOrder Details = " + salesOrder.toString());			
	}
	
}
    

