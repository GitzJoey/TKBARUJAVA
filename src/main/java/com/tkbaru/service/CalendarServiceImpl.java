package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.CalendarDAO;
import com.tkbaru.model.Calendar;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarDAO calendarDAO;
	
	@Override
	@Transactional
	public List<Calendar> getAllCalendarByUserId(int userId) {

		return calendarDAO.getAllCalendarByUserId(userId);
	}

	@Override
	@Transactional
	public void addCalendar(int userId, Calendar calendar) {

		calendarDAO.addCalendar(userId, calendar);
	}

	@Override
	@Transactional
	public void editCalendar(int userId, Calendar calendar) {

		calendarDAO.editCalendar(userId, calendar);
	}

	@Override
	@Transactional
	public void deleteCalendar(int userId, int selectedId) {

		deleteCalendar(userId, selectedId);
	}

	@Override
	public Calendar getEventsById(int selectedId) {
		// TODO Auto-generated method stub
		return null;
	}

}
