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
import javax.persistence.Transient;

@Entity
@Table(name="tb_lookup")
public class Lookup implements Serializable {
	private static final long serialVersionUID = 8477754720076849619L;

	public Lookup() {
		
	}

	public Lookup(String lookupCategory,
					String lookupKey,
					Integer orderNum,
					String localeMessageCodes,
					String lookupValue,
					String lookupMaintainability) {
		this.lookupCategory = lookupCategory;
		this.lookupKey = lookupKey;
		this.orderNum = orderNum;
		this.localeMessageCodes = localeMessageCodes;
		this.lookupValue = lookupValue;
		this.lookupMaintainability = lookupMaintainability;
		this.createdBy = 0;
		this.createdDate = new Date();		
	}
	
	@Id
	@Column(name="lookup_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lookupId;
	@Column(name="category")
	private String lookupCategory;
	@Column(name="lookup_key")
	private String lookupKey;
	@Column(name="order_num")
	private Integer orderNum;
	@Column(name="status")
	private String lookupStatus;
	@Column(name="loc_msg_code")
	private String localeMessageCodes;
	@Column(name="val")
	private String lookupValue;
	@Column(name="maintainable")
	private String lookupMaintainability;
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
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)	
	private Lookup statusLookup;

	@ManyToOne
	@JoinColumn(name="maintainable", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup maintainabilityLookup;

	@Transient
	public String getI18nLookupValue() {
		return this.localeMessageCodes;
	}

	public Integer getLookupId() {
		return lookupId;
	}

	public void setLookupId(Integer lookupId) {
		this.lookupId = lookupId;
	}

	public String getLookupCategory() {
		return lookupCategory;
	}

	public void setLookupCategory(String lookupCategory) {
		this.lookupCategory = lookupCategory;
	}

	public String getLookupKey() {
		return lookupKey;
	}

	public void setLookupKey(String lookupKey) {
		this.lookupKey = lookupKey;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getLookupStatus() {
		return lookupStatus;
	}

	public void setLookupStatus(String lookupStatus) {
		this.lookupStatus = lookupStatus;
	}

	public String getLocaleMessageCodes() {
		return localeMessageCodes;
	}

	public void setLocaleMessageCodes(String localeMessageCodes) {
		this.localeMessageCodes = localeMessageCodes;
	}

	public String getLookupValue() {
		return lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public String getLookupMaintainability() {
		return lookupMaintainability;
	}

	public void setLookupMaintainability(String lookupMaintainability) {
		this.lookupMaintainability = lookupMaintainability;
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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	public Lookup getMaintainabilityLookup() {
		return maintainabilityLookup;
	}

	public void setMaintainabilityLookup(Lookup maintainabilityLookup) {
		this.maintainabilityLookup = maintainabilityLookup;
	}

	@Override
	public String toString() {
		return "Lookup [lookupId=" + lookupId + ", lookupCategory=" + lookupCategory + ", lookupKey=" + lookupKey
				+ ", orderNum=" + orderNum + ", lookupStatus=" + lookupStatus + ", localeMessageCodes="
				+ localeMessageCodes + ", lookupValue=" + lookupValue + ", lookupMaintainability="
				+ "lookupMaintainability" + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", statusLookup=" + "statusLookup"
				+ ", maintainabilityLookup=" + "maintainabilityLookup" + "]";
	}
	
}
