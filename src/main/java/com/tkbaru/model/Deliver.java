package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_deliver")
public class Deliver {
	public Deliver() {

	}

	@Id
	@Column(name = "deliver_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliverId;
	@Column(name = "deliver_date")
	@Temporal(TemporalType.DATE)
	private Date deliverDate;
	@Column(name = "bruto")
	private int bruto;
	@Column(name = "net")
	private int net;
	@Column(name = "tare")
	private int tare;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public int getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(int deliverId) {
		this.deliverId = deliverId;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public int getBruto() {
		return bruto;
	}
	public void setBruto(int bruto) {
		this.bruto = bruto;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public int getTare() {
		return tare;
	}
	public void setTare(int tare) {
		this.tare = tare;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Deliver [deliverId=" + deliverId + ", deliverDate=" + deliverDate + ", bruto=" + bruto + ", net=" + net
				+ ", tare=" + tare + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
