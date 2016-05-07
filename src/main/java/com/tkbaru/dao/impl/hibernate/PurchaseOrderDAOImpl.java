package com.tkbaru.dao.impl.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.PurchaseOrderDAO;
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

		logger.info("[getAllPurchaseOrder] " + "PurchaseOrder : " + purchaseOrderList.size());

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

		logger.info("[getPurchaseOrderById] " + "PurchaseOrder loaded successfully, PurchaseOrder details = " + po.toString());

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

		logger.info("[getPurchaseOrderByIds] " + "PurchaseOrder : " + purchaseOrderList.size());

		return purchaseOrderList;
	}

	@Override
	public void savePayment(PurchaseOrder payment) {
		logger.info("[savePayment] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		session.update(payment);
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByStatus(String status) {
		logger.info("[getPurchaseOrderByStatus] " + "status: " + status);

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder po WHERE po.poStatusLookup.lookupKey = :status ").setString("status", status).list();
		
		logger.info("[getPurchaseOrderByStatus] " + "PurchaseOrder Count: " + purchaseOrderList.size());

		return purchaseOrderList;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByStatus(int warehouseId, String status) {
		logger.info("[getPurchaseOrderByWarehouseId] " + "warehouseId: "+ warehouseId + ", poStatus: " + status);

		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrderList = session.createQuery("FROM PurchaseOrder po WHERE po.warehouseEntity.warehouseId = :warehouseId AND po.poStatusLookup.lookupKey = :status ").setInteger("warehouseId", warehouseId).setString("status", status).list();
		
		logger.info("[getPurchaseOrderByWarehouseId] " + "PurchaseOrder Count: " + purchaseOrderList.size());

		return purchaseOrderList;
	}

	@Override
	public boolean isExistingPOCode(String poCode) {
		logger.info("[isExistingPOCode] " + "poCode: " + poCode);

		Session session = this.sessionFactory.getCurrentSession();
		boolean exist = (long)session.createQuery("SELECT COUNT(*) FROM PurchaseOrder WHERE poCode = :poCode").setString("poCode", poCode).uniqueResult() > 0;
		
		logger.info("[isExistingPOCode] " + "poCode " + poCode + " is exists: " + exist);

		return exist;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByWarehouseIdByShippingDate(int warehouseId, Date startDate, Date endDate) {
		logger.info("[getPurchaseOrderByWarehouseIdByShippingDate] " + "warehouseId: "+ warehouseId + ", startDate: " + startDate + ", endDate: " + endDate);

		Map<String, Object> parameterNameAndValues = new HashMap<String, Object>();
		parameterNameAndValues.put("warehouseId", warehouseId);
		parameterNameAndValues.put("startDate", startDate);
		parameterNameAndValues.put("endDate", endDate);
		
		Session session = this.sessionFactory.getCurrentSession();
	
		Query q = session.createQuery("FROM PurchaseOrder po WHERE po.warehouseEntity.warehouseId = :warehouseId AND po.shippingDate BETWEEN :startDate AND :endDate ");

		for (Entry<String, Object> e : parameterNameAndValues.entrySet()) {
		    q.setParameter(e.getKey(), e.getValue());
		}

		List<PurchaseOrder> purchaseOrderList = q.list();
		
		logger.info("[getPurchaseOrderByWarehouseIdByShippingDate] " + "PurchaseOrder Count: " + purchaseOrderList.size());

		return purchaseOrderList;
	}

	@Override
	public List<PurchaseOrder> getAllUnfinishedPurchaseOrder() {
		logger.info("[getAllUnfinishedPurchaseOrder] " + "");

		Session session = this.sessionFactory.getCurrentSession();
	
		Query q = session.createQuery("FROM PurchaseOrder po WHERE po.poStatusLookup.lookupKey <> 'L013_C'");

		List<PurchaseOrder> purchaseOrderList = q.list();
		
		logger.info("[getAllUnfinishedPurchaseOrder] " + "Unfinished PurchaseOrder Count: " + purchaseOrderList.size());

		return purchaseOrderList;
	}
}
