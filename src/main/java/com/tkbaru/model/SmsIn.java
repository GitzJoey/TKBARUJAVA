package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sms_in")
public class SmsIn {
	public SmsIn() {

	}

	@Id
	@GeneratedValue
	@Column(name = "sms_inbox_id")
	private int smsInboxId;
	@Column(name = "from")
	private String from;
	@Column(name = "message")
	private String message;
	@Column(name = "created_date")
	private Date createdDate;

	public int getSmsInboxId() {
		return smsInboxId;
	}

	public void setSmsInboxId(int smsInboxId) {
		this.smsInboxId = smsInboxId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "SmsInbox [smsInboxId=" + smsInboxId + "]";
	}

}
