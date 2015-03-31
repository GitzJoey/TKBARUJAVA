package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_lookup")
public class Lookup implements Serializable {
	private static final long serialVersionUID = 8477754720076849619L;
	public Lookup() {
		
	}

	@Id
	@Column(name="lookup_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lookupId;
	@Column(name="category")
	private String lookupCategory;
	@Column(name="lookup_key")
	private String lookupKey;
	@Column(name="order_num")
	private int orderNum;
	@Column(name="status")
	private String lookupStatus;
	@Column(name="maintainable")
	private String lookupMaintainability;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;

	@OneToMany(mappedBy="lookupEntity", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LookupDetail> lookupDetail;

	@Transient
	private String languageCode = "L010_EN";
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)	
	private Lookup statusLookup;

	@ManyToOne
	@JoinColumn(name="maintainable", referencedColumnName="lookup_key", unique=true, insertable=false, updatable=false)
	private Lookup maintainabilityLookup;

	public String getLanguageCode() {
		return this.languageCode;		
	}
	
	public String getLookupValue() {
		if (this.lookupDetail.size() == 0) return "";
		
		for (LookupDetail ld:this.lookupDetail) {
			if (ld.getLanguageCode().equals(this.languageCode)) {
				return ld.getLookupValue();
			}
		}
		return "";
	}

	public String getLookupAlternateValue() {
		if (this.lookupDetail.size() == 0) return "";

		for (LookupDetail ld:this.lookupDetail) {
			if (ld.getLanguageCode().equals(this.languageCode)) {
				return ld.getLookupAlternateValue();
			}
		}
		return "";
	}
	
	public int getLookupId() {
		return lookupId;
	}
	public void setLookupId(int lookupId) {
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
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getLookupStatus() {
		return lookupStatus;
	}
	public void setLookupStatus(String lookupStatus) {
		this.lookupStatus = lookupStatus;
	}
	public String getLookupMaintainability() {
		return lookupMaintainability;
	}
	public void setLookupMaintainability(String lookupMaintainability) {
		this.lookupMaintainability = lookupMaintainability;
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
	public List<LookupDetail> getLookupDetail() {
		return lookupDetail;
	}
	public void setLookupDetail(List<LookupDetail> lookupDetail) {
		this.lookupDetail = lookupDetail;
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
		return "Lookup [lookupId=" + lookupId + ", lookupCategory="
				+ lookupCategory + ", lookupKey=" + lookupKey + ", orderNum="
				+ orderNum + ", lookupStatus=" + lookupStatus
				+ ", lookupMaintainability=" + lookupMaintainability
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lookupDetail=" + lookupDetail + ", languageCode="
				+ languageCode + "]";
	}
}
