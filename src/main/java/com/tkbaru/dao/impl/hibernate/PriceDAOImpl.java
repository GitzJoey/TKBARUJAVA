package com.tkbaru.dao.impl.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
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
		
		List<Price> priceList = session.createQuery("FROM Price a WHERE a.inputDate = :d").setTimestamp("d", inputDate).list();
	
		logger.info("[getAllPriceForDate] " + "Price for Date " + new SimpleDateFormat("dd-MMM-yyyy hh:mm").format(inputDate) + " Count: " + priceList.size());		
		
		return priceList;
	}
	
	@Override
	public void addPrice(Price price) {		
		logger.info("[addPrice] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        
        session.persist(price);        
	}

	@Override
	public List<Price> getLatestRetailPrice(int stocksId, int priceLevelId) {
		logger.info("[getLatestRetailPriceByStocksId] " + "stocksId: " + stocksId + ", priceLevelId: " + priceLevelId);
		
		Session session = this.sessionFactory.getCurrentSession();
		
		DetachedCriteria cr = DetachedCriteria.forClass(Price.class, "pr")
				.createCriteria("pr.stocksEntity", "se", JoinType.INNER_JOIN)
				.createCriteria("se.productEntity", "prd", JoinType.INNER_JOIN)
				.setProjection(Projections.max("pr.inputDate"))
				.add(Restrictions.eq("se.stocksId", stocksId));
				
		Criteria crit = session.createCriteria(Price.class, "p")
				.createCriteria("p.stocksEntity", "s", JoinType.INNER_JOIN)
				.createCriteria("p.priceLevelEntity", "pl", JoinType.INNER_JOIN)
				.add(Restrictions.eq("s.stocksId", stocksId))
				.add(Restrictions.eq("pl.priceLevelId", priceLevelId))
				.add(Subqueries.propertyEq("p.inputDate", cr));
		
		List<Price> priceList = crit.list();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		for (Price p:priceList) {
			logger.info("[getLatestRetailPriceByStocksId] " + 
							"stocksId: " + stocksId + 
							", productId: " + p.getStocksEntity().getProductEntity().getProductId() + 
							", priceLevelId: " + p.getPriceLevelEntity().getPriceLevelId() +
							", inputDate: " + df.format(p.getInputDate()));
		}
		
		return priceList;
	}

}
