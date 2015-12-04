package com.tkbaru.dao.impl.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.PriceDAO;
import com.tkbaru.model.Price;

@Repository
@SuppressWarnings("unchecked")
public class PriceDAOImpl implements PriceDAO {
	private static final Logger logger = LoggerFactory.getLogger(PriceDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	@Override
	public List<Price> getAllPriceForDate(Date inputDate) {
		logger.info("[getAllPriceForDate] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();		
		
		List<Price> priceList = session.createQuery("FROM Price").list();
	
		logger.info("Price for Date " + new SimpleDateFormat("dd MMM yyyy").format(inputDate) + " Count: " + priceList.size());		
		
		return priceList;
	}
    
}
