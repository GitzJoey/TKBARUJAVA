package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_sms_in")
public class SmsIn {
	public SmsIn() {

	}

	@Id
	@GeneratedValue
	@Column(name = "sms_in_id")
	private int smsInId;
	@Column(name = "sender")
	private String sender;
	@Column(name = "message")
	private String message;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public int getSmsInId() {
		return smsInId;
	}

	public void setSmsInId(int smsInId) {
		this.smsInId = smsInId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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
		return "SmsInbox [smsInboxId=" + smsInId + "]";
	}

}
