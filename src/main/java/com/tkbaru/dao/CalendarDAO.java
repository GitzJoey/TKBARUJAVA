package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Calendar;

public interface CalendarDAO {
	public List<Calendar> getAllCalendarByUserId(int userId);
	public Calendar getEventsById(int selectedId);
	public void addCalendar(int userId, Calendar calendar);
	public void editCalendar(int userId, Calendar calendar);
	public void deleteCalendar(int userId, int selectedId);

}
