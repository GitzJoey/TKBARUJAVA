package com.tkbaru.dao;

import java.util.ArrayList;
import java.util.List;

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
		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		return soList;
	}

}
