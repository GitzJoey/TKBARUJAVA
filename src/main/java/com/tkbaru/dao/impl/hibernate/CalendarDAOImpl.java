package com.tkbaru.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.dao.CalendarDAO;
import com.tkbaru.model.Calendar;

@Repository
@SuppressWarnings("unchecked")
public class CalendarDAOImpl implements CalendarDAO {
	private static final Logger logger = LoggerFactory.getLogger(CalendarDAOImpl.class);
	
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public List<Calendar> getAllCalendarByUserId(int userId) {
		logger.info("[getAllCalendarByUserId] " + "userId: " + userId);
		
		Session session = this.sessionFactory.getCurrentSession();		
		
		List<Calendar> calendarList = session.createQuery("FROM Calendar WHERE userId = :id").setInteger("id", userId).list();

		logger.info("[getAllCalendarByUserId] " + "Calendar Count: " + calendarList.size());

		return calendarList;
	}

	@Override
	public void addCalendar(int userId, Calendar calendar) {
		logger.info("[addCalendar] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(calendar);
        
        logger.info("[addCalendar] " + "Calendar added successfully, Calendar Details = " + calendar.toString());		
	}

	@Override
	public void editCalendar(int userId, Calendar calendar) {
		logger.info("[editCalendar] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(calendar);
	    
	    logger.info("[editCalendar] " + "Calendar updated successfully, Calendar Details = " + calendar.toString());
	}

	@Override
	public void deleteCalendar(int userId, int selectedId) {
		logger.info("[deleteCalendar] " + "userId: " + userId + ", selectedId: " + selectedId);
		
        Session session = this.sessionFactory.getCurrentSession();
        Calendar cal = (Calendar) session.load(Calendar.class, new Integer(selectedId));
        if(null != cal){
            session.delete(cal);
        }
        
        logger.info("[deleteCalendar] " + "Calendar deleted successfully, Calendar details = " + cal.toString());
	}

	@Override
	public Calendar getEventsById(int selectedId) {
		logger.info("[getEventsById] " + "selectedId: " + selectedId);
        
		Session session = this.sessionFactory.getCurrentSession();
		Calendar cal = null;
        
        try {
        	cal = (Calendar) session.load(Calendar.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("[getEventsById] " + "Calendar loaded successfully, Calendar details = " + cal.toString());
                
        return cal;
	}

}
