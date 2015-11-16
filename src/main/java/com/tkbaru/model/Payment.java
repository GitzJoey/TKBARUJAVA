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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID=-3391425491677198471L;

	public Payment() {

	}

	@Id
	@Column(name="payment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer paymentId;
	@Column(name="payment_date")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	@Column(name="total_amount")
	private Long totalAmount;
	@Column(name="effective_date")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;
	@Column(name="is_linked")
	private Boolean isLinked;
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

	@ManyToOne
	@JoinColumn(name="payment_type", referencedColumnName="lookup_key")
	private Lookup paymentTypeLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store paymentStoreEntity;

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup paymentStatusLookup;

	@ManyToOne
	@JoinColumn(name="bank_code", referencedColumnName="lookup_key")
	private Lookup bankCodeLookup;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
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

	public Lookup getPaymentTypeLookup() {
		return paymentTypeLookup;
	}

	public void setPaymentTypeLookup(Lookup paymentTypeLookup) {
		this.paymentTypeLookup = paymentTypeLookup;
	}

	public Store getPaymentStoreEntity() {
		return paymentStoreEntity;
	}

	public void setPaymentStoreEntity(Store paymentStoreEntity) {
		this.paymentStoreEntity = paymentStoreEntity;
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
		return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", totalAmount=" + totalAmount
				+ ", effectiveDate=" + effectiveDate + ", isLinked=" + isLinked + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", paymentTypeLookup=" + paymentTypeLookup + ", paymentStoreEntity=" + paymentStoreEntity
				+ ", paymentStatusLookup=" + paymentStatusLookup + ", bankCodeLookup=" + bankCodeLookup + "]";
	}

}
