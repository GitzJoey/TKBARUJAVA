package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.SalesOrderDAO;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.SalesOrderCopy;

@Repository
@SuppressWarnings("unchecked")
public class SalesOrderDAOImpl implements SalesOrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(SalesOrderDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	
	@Override
	public List<SalesOrder> getAwaitingPaymentSales(int selectedCustomerId) {
		logger.info("[getAwaitingPaymentSales] " + "selectedCustomerId: " + selectedCustomerId);

		Session session = this.sessionFactory.getCurrentSession();
		List<SalesOrder> soList = session.createQuery("FROM SalesOrder s WHERE s.salesStatusLookup.lookupKey = :status AND s.customerEntity.customerId = :customerId ").setString("status", "L016_WP").setInteger("customerId", selectedCustomerId).list();
		
		logger.info("[getAwaitingPaymentSales] SalesOrder Count: " + soList.size());
		
		return soList;
	}

	@Override
	public List<SalesOrder> getSalesOrderByStatus(String statusCode) {
		logger.info("[getSalesOrderByStatus] " + "status: " + statusCode);

		Session session = this.sessionFactory.getCurrentSession();
		List<SalesOrder> soList = session.createQuery("FROM SalesOrder s WHERE s.salesStatusLookup = :status ").setString("status", statusCode).list();
	
		logger.info("SalesOrder Count: " + soList.size());
		
		return soList;
	}
	
	@Override
	public List<SalesOrder> getSalesOrderBySalesCode(String salesCode) {
		logger.info("[getSalesOrderBySalesCode] " + "salesCode: " + salesCode);

		Session session = this.sessionFactory.getCurrentSession();
		List<SalesOrder> soList = session.createQuery("FROM SalesOrder s WHERE s.salesCode LIKE :salesCode").setParameter("salesCode", "%"+salesCode.toUpperCase()+"%").list();
		
		logger.info("SalesOrder Count: " + soList.size());
		
		return soList;
	}

	@Override
	public SalesOrder getSalesOrderById(int selectedId) {
		logger.info("[getSalesOrderById] " + "selectedId: " + selectedId);

		Session session = this.sessionFactory.getCurrentSession();
		SalesOrder so = null;

		try {
			so = (SalesOrder) session.load(SalesOrder.class, new Integer(selectedId));
		} catch (Exception err) {
			logger.info(err.getMessage());
		}

		logger.info("SalesOrder loaded successfully, SalesOrder details = " + so.toString());

		return so;
	}

	@Override
	public void addSalesOrder(SalesOrder so) {
		logger.info("[addSalesOrder] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		session.persist(so);
	}

	@Override
	public void editSalesOrder(SalesOrder so) {
		logger.info("[editSalesOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		
		session.update(so);
	}

	@Override
	public void addSalesOrderCopy(SalesOrderCopy cp) {
		logger.info("[addSalesOrder] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		session.persist(cp);
	}

	@Override
	public void editSalesOrderCopy(SalesOrderCopy cp) {
		logger.info("[editSalesOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		
		session.update(cp);
	}

	@Override
	public void deleteSalesOrder(int selectedId) {
		logger.info("[deleteSalesOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		
		SalesOrder purchaseOrder = (SalesOrder) session.load(SalesOrder.class, new Integer(selectedId));
		
		if (null != purchaseOrder) {
			session.delete(purchaseOrder);
		}
	}

	@Override
	public boolean isExistingSalesCode(String salesCode) {
		logger.info("[isExistingSalesCode] " + "salesCode: " + salesCode);

		Session session = this.sessionFactory.getCurrentSession();
		boolean exist = (long)session.createQuery("SELECT COUNT(*) FROM SalesOrder WHERE salesCode = :salesCode").setString("salesCode", salesCode).uniqueResult() > 0;
		
		logger.info("salesCode " + salesCode + " is exists: " + exist);

		return exist;
	}

}
