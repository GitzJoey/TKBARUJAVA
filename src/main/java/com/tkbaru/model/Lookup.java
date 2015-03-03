package com.tkbaru.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_lookup")
public class Lookup {
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
	
	@OneToMany(mappedBy="lookupEntity", cascade=CascadeType.ALL)
	private List<LookupDetail> lookupDetail;

	@Transient
	private String languageCode;
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
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

	public List<LookupDetail> getLookupDetail() {
		return lookupDetail;
	}

	public void setLookupDetail(List<LookupDetail> lookupDetail) {
		this.lookupDetail = lookupDetail;
	}

	@Override
	public String toString() {
		return "Lookup [lookupId=" + lookupId + ", lookupCategory="
				+ lookupCategory + ", lookupKey=" + lookupKey + ", orderNum="
				+ orderNum + ", lookupStatus=" + lookupStatus
				+ ", lookupMaintainability=" + lookupMaintainability
				+ ", lookupDetail=" + lookupDetail + "]";
	}

}
