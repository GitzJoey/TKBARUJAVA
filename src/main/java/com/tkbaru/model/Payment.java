package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = -3391425491677198471L;

	public Payment() {

	}

	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@Column(name = "payment_type")
	private String paymentType;
	@Column(name = "payment_date")
	private Date paymentDate;
	@Column(name = "total_amount")
	private long totalAmount;
	@Column(name = "bank_code")
	private String bankCode;
	@Column(name = "effective_date")
	private Date effectiveDate;
	@Column(name = "is_linked")
	private boolean isLinked;
	@Column(name = "status")
	private String paymentStatus;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "payment_type", referencedColumnName = "lookup_key", unique = true, insertable = false, updatable = false)
	private Lookup paymentTypeLookup;

	@ManyToOne
	@JoinColumn(name = "status", referencedColumnName = "lookup_key", unique = true, insertable = false, updatable = false)
	private Lookup paymentStatusLookup;

	@ManyToOne
	@JoinColumn(name = "bank_code", referencedColumnName = "lookup_key", unique = true, insertable = false, updatable = false)
	private Lookup bankCodeLookup;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isLinked() {
		return isLinked;
	}

	public void setLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public Lookup getPaymentTypeLookup() {
		return paymentTypeLookup;
	}

	public void setPaymentTypeLookup(Lookup paymentTypeLookup) {
		this.paymentTypeLookup = paymentTypeLookup;
	}

	public Lookup getPaymentStatusLookup() {
		return paymentStatusLookup;
	}

	public void setPaymentStatusLookup(Lookup paymentStatusLookup) {
		this.paymentStatusLookup = paymentStatusLookup;
	}

	public Lookup getBankCodeLookup() {
		return bankCodeLookup;
	}

	public void setBankCodeLookup(Lookup bankCodeLookup) {
		this.bankCodeLookup = bankCodeLookup;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType="
				+ paymentType + ", paymentDate=" + paymentDate
				+ ", totalAmount=" + totalAmount + ", bankCode=" + bankCode
				+ ", effectiveDate=" + effectiveDate + ", isLinked=" + isLinked
				+ ", paymentStatus=" + paymentStatus + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
