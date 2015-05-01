package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.PurchaseOrder;

@Repository
@SuppressWarnings("unchecked")
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrder() {
		logger.info("[getAllPurchaseOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder").list();

		for (PurchaseOrder po : purchaseOrderList) {
			logger.info("PurchaseOrder : " + po.toString());
		}

		return purchaseOrderList;
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(int selectedId) {
		logger.info("[getPurchaseOrderById] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		PurchaseOrder po = null;

		try {
			po = (PurchaseOrder) session.load(PurchaseOrder.class, new Integer(selectedId));
		} catch (Exception err) {
			logger.info(err.getMessage());
		}

		logger.info("PurchaseOrder loaded successfully, PurchaseOrder details = " + po.toString());

		return po;
	}

	@Override
	public void addPurchaseOrder(PurchaseOrder po) {
		logger.info("[addPurchaseOrder] " + "");
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(po);
	}

	@Override
	public void editPurchaseOrder(PurchaseOrder po) {
		logger.info("[editPurchaseOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		session.update(po);
	}

	@Override
	public void deletePurchaseOrder(int selectedId) {
		logger.info("[deletePurchaseOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
		PurchaseOrder purchaseOrder = (PurchaseOrder) session.load(PurchaseOrder.class, new Integer(selectedId));
		if (null != purchaseOrder) {
			session.delete(purchaseOrder);
		}
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByIds(String selectedIdINClause) {
		logger.info("[getPurchaseOrderByIds] " + "selectedIdINClause: " + selectedIdINClause);

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder").list();

		for (PurchaseOrder po : purchaseOrderList) {
			logger.info("PurchaseOrder : " + po.toString());
		}

		return purchaseOrderList;
	}

	@Override
	public void savePayment(PurchaseOrder payment) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(payment);
		
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status) {
		logger.info("[getPurchaseOrderByStatus] " + "status: " + status);

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder where poStatus = :status ").setString("status", status).list();
		
		for (PurchaseOrder po : purchaseOrderList) {
			logger.info("PurchaseOrder : " + po.toString());
		}

		return purchaseOrderList;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByStatus(int warehouseId, String status) {
		logger.info("[getPurchaseOrderByWarehouseId] " + "warehouseId: "+ warehouseId + "poStatus: " + status);

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder where warehouseId = :warehouseId and poStatus = :status ").setInteger("warehouseId", warehouseId).setString("status", status).list();
		
		for (PurchaseOrder po : purchaseOrderList) {
			logger.info("PurchaseOrder : " + po.toString());
		}

		return purchaseOrderList;
	}

}
