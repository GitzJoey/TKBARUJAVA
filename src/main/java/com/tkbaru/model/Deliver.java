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
@Table(name="tb_deliver")
public class Deliver {
	public Deliver() {

	}

	@Id
	@Column(name="deliver_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer deliverId;
	@Column(name="deliver_date")
	@Temporal(TemporalType.DATE)
	private Date deliverDate;
	@Column(name="bruto")
	private Long bruto;
	@Column(name="net")
	private Long net;
	@Column(name="tare")
	private Long tare;
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
	public Integer getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(Integer deliverId) {
		this.deliverId = deliverId;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Long getBruto() {
		return bruto;
	}
	public void setBruto(Long bruto) {
		this.bruto = bruto;
	}
	public Long getNet() {
		return net;
	}
	public void setNet(Long net) {
		this.net = net;
	}
	public Long getTare() {
		return tare;
	}
	public void setTare(Long tare) {
		this.tare = tare;
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
	@Override
	public String toString() {
		return "Deliver [deliverId=" + deliverId + ", deliverDate=" + deliverDate + ", bruto=" + bruto + ", net=" + net
				+ ", tare=" + tare + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
