package com.tkbaru.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="tb_calendar")
public class Calendar {
	public Calendar() {
		
	}
	
	@Transient
	private String DATE_PATTERN = "yyyy-MM-dd";
	
	@Id
	@Column(name="calendar_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarId;
	@Column(name="user_id")
	private int userId;
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name="event_title")
	private String eventTitle;
	@Column(name="ext_url")
	private String extURL;
	@Column(name="created_by")
	private Integer createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private Integer updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public String startDateToString() {
		if (startDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			return sdf.format(startDate);
		} else {
			return "";
		}
	}
	
	public String startDateToString(String datePattern) {
		if (startDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			return sdf.format(startDate);
		} else {
			return "";
		}
	}
	
	public String endDateToString() {
		if (endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			return sdf.format(endDate);
		} else {
			return "";
		}
	}
	
	public String endDateToString(String datePattern) {
		if (endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			return sdf.format(endDate);
		} else {
			return "";
		}		
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getExtURL() {
		return extURL;
	}

	public void setExtURL(String extURL) {
		this.extURL = extURL;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
