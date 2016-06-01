package com.tkbaru.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.CustomerMenuDAO;
import com.tkbaru.model.SalesOrder;

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
		logger.info("[getSalesOrderWithDeliverId] " + "");
		
		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		Session session = this.sessionFactory.getCurrentSession();

		DetachedCriteria dcriteria = DetachedCriteria.forClass(SalesOrder.class, "so")
				.createAlias("so.itemsList", "i", JoinType.INNER_JOIN)
				.createAlias("i.deliverList", "d", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.isNotNull("d.deliverId"))
				.add(Restrictions.isNotNull("d.bruto"))
				.add(Restrictions.isNull("d.net"))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("so.salesId").as("salesId"))
						.add(Projections.groupProperty("so.salesCode").as("salesCode"))
				);

		soList = session.createCriteria(SalesOrder.class, "so")
				.add(Subqueries.propertiesIn(new String[] { "salesId",  "salesCode" }, dcriteria)).list();

		String salesOrderReturned = "";
		for (int i=0; i<soList.size(); i++) {
			if (i==soList.size()-1) {
				salesOrderReturned += soList.get(i).getSalesCode();
			} else {
				salesOrderReturned += soList.get(i).getSalesCode() + ", ";
			}
		}
		
		logger.info("[getSalesOrderWithDeliverId] " + "SalesOrder Count: " + soList.size() + "( " + salesOrderReturned + " )");
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
    

