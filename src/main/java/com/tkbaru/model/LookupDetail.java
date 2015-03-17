package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_lookup_detail")
public class LookupDetail {
	public LookupDetail() {
		
	}

	@Id
	@Column(name="lookup_detail_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lookupDetailId;
	@Column(name="language_code")
	private String languageCode;
	@Column(name="lookup_val")
	private String lookupValue;
	@Column(name="lookup_alt_val")
	private String lookupAlternateValue;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name="lookup_id", nullable=false)
	private Lookup lookupEntity;

	@ManyToOne
	@JoinColumn(name="language_code", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup languageCodeLookup;
	
	public int getLookupDetailId() {
		return lookupDetailId;
	}

	public void setLookupDetailId(int lookupDetailId) {
		this.lookupDetailId = lookupDetailId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLookupValue() {
		return lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public String getLookupAlternateValue() {
		return lookupAlternateValue;
	}

	public void setLookupAlternateValue(String lookupAlternateValue) {
		this.lookupAlternateValue = lookupAlternateValue;
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

	public Lookup getLookupEntity() {
		return lookupEntity;
	}

	public void setLookupEntity(Lookup lookupEntity) {
		this.lookupEntity = lookupEntity;
	}

	public Lookup getLanguageCodeLookup() {
		return languageCodeLookup;
	}

	public void setLanguageCodeLookup(Lookup languageCodeLookup) {
		this.languageCodeLookup = languageCodeLookup;
	}

	@Override
	public String toString() {
		return "LookupDetail [lookupDetailId=" + lookupDetailId
				+ ", languageCode=" + languageCode + ", lookupValue="
				+ lookupValue + ", lookupAlternateValue="
				+ lookupAlternateValue + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}

}
