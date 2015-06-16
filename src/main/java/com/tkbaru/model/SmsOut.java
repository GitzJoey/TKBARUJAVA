package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sms_out")
public class SmsOut {
	public SmsOut() {

	}

	@Id
	@GeneratedValue
	@Column(name = "sms_outbox_id")
	private int smsOutboxId;
	@Column(name = "to")
	private String to;
	@Column(name = "message")
	private String message;
	@Column(name = "created_date")
	private Date createdDate;

	public int getSmsOutboxId() {
		return smsOutboxId;
	}

	public void setSmsOutboxId(int smsOutboxId) {
		this.smsOutboxId = smsOutboxId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
		return "SmsSend [smsSendId=" + smsOutboxId + "]";
	}

}
