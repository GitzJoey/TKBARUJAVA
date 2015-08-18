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
@Table(name = "tb_sms_out")
public class SmsOut {
	public SmsOut() {

	}

	@Id
	@GeneratedValue
	@Column(name = "sms_out_id")
	private int smsOutId;
	@Column(name = "recipient")
	private String recipient;
	@Column(name = "message")
	private String message;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public int getSmsOutId() {
		return smsOutId;
	}

	public void setSmsOutId(int smsOutId) {
		this.smsOutId = smsOutId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
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
		return "SmsSend [smsSendId=" + smsOutId + "]";
	}

}
