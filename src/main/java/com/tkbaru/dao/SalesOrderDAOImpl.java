package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.SalesOrder;

@Repository
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
		List<SalesOrder> soList = session.createQuery("FROM SalesOrder where salesStatus = :status and customerId = :customerId ").setString("status", "L016_WP").setInteger("customerId", selectedCustomerId).list();
		
		for (SalesOrder so : soList) {
			logger.info("SalesOrder : " + so.toString());
		}

		
		return soList;
	}

	@Override
	public List<SalesOrder> getSalesOrderByStatus(String statusCode) {
		logger.info("[getSalesOrderByStatus] " + "status: " + statusCode);

		Session session = this.sessionFactory.getCurrentSession();
		List<SalesOrder> soList = session.createQuery("FROM SalesOrder where salesStatus = :status ").setString("status", statusCode).list();
		
		for (SalesOrder so : soList) {
			logger.info("SalesOrder : " + so.toString());
		}

		
		return soList;
	}

	@Override
	public SalesOrder getSalesOrderById(int selectedId) {
		logger.info("[getSalesOrderById] " + "");

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
	public void deleteSalesOrder(int selectedId) {
		logger.info("[deleteSalesOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		SalesOrder purchaseOrder = (SalesOrder) session.load(SalesOrder.class, new Integer(selectedId));
		if (null != purchaseOrder) {
			session.delete(purchaseOrder);
		}
		
	}

}
