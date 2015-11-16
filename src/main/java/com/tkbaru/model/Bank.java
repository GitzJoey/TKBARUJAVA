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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="tb_bank")
public class Bank {
	public Bank() {
		
	}
	
	@Id
	@Column(name="bank_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bankId;
	@Column(name="bank_code")
	private String bankCode;
	@Column(name="acc_num")
	private String accNumber;
	@Column(name="acc_holder_name")
	private String accHolderName;
	@Column(name="currency_code")
	private String currencyCode;
	@Column(name="trx_date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	@Column(name="trx_desc")
	private String transactionDesc;
	@Column(name="trx_branch")
	private String transactionBranch;
	@Column(name="trx_amt")
	private Long transactionAmount;
	@Column(name="trx_direction")
	private String transactionDirection;
	@Column(name="trx_balance")
	private Long transactionBalance;
	@Column(name="beginning_balance")
	private Long beginningBalance;
	@Column(name="credit_ttl_amt")
	private Long creditTotalAmount;
	@Column(name="debit_ttl_amt")
	private Long debitTotalAmount;
	@Column(name="ending_balance")
	private Long endingBalance;
	@Column(name="upload_filename")
	private String uploadFileName;
	@Column(name="saved_filename")
	private String savedFileName;
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
	
	@Transient
	private MultipartFile fileBinary;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public String getTransactionBranch() {
		return transactionBranch;
	}

	public void setTransactionBranch(String transactionBranch) {
		this.transactionBranch = transactionBranch;
	}

	public Long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDirection() {
		return transactionDirection;
	}

	public void setTransactionDirection(String transactionDirection) {
		this.transactionDirection = transactionDirection;
	}

	public Long getTransactionBalance() {
		return transactionBalance;
	}

	public void setTransactionBalance(Long transactionBalance) {
		this.transactionBalance = transactionBalance;
	}

	public Long getBeginningBalance() {
		return beginningBalance;
	}

	public void setBeginningBalance(Long beginningBalance) {
		this.beginningBalance = beginningBalance;
	}

	public Long getCreditTotalAmount() {
		return creditTotalAmount;
	}

	public void setCreditTotalAmount(Long creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

	public Long getDebitTotalAmount() {
		return debitTotalAmount;
	}

	public void setDebitTotalAmount(Long debitTotalAmount) {
		this.debitTotalAmount = debitTotalAmount;
	}

	public Long getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(Long endingBalance) {
		this.endingBalance = endingBalance;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
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

	public MultipartFile getFileBinary() {
		return fileBinary;
	}

	public void setFileBinary(MultipartFile fileBinary) {
		this.fileBinary = fileBinary;
	}

	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankCode=" + bankCode + ", accNumber=" + accNumber + ", accHolderName="
				+ accHolderName + ", currencyCode=" + currencyCode + ", transactionDate=" + transactionDate
				+ ", transactionDesc=" + transactionDesc + ", transactionBranch=" + transactionBranch
				+ ", transactionAmount=" + transactionAmount + ", transactionDirection=" + transactionDirection
				+ ", transactionBalance=" + transactionBalance + ", beginningBalance=" + beginningBalance
				+ ", creditTotalAmount=" + creditTotalAmount + ", debitTotalAmount=" + debitTotalAmount
				+ ", endingBalance=" + endingBalance + ", uploadFileName=" + uploadFileName + ", savedFileName="
				+ savedFileName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", fileBinary=" + fileBinary + "]";
	}

}
