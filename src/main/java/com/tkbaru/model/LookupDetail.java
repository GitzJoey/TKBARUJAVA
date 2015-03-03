package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name="lookup_id", nullable=false)
	private Lookup lookupEntity;

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

	public Lookup getLookupEntity() {
		return lookupEntity;
	}

	public void setLookupEntity(Lookup lookupEntity) {
		this.lookupEntity = lookupEntity;
	}

	@Override
	public String toString() {
		return "LookupDetail [lookupDetailId=" + lookupDetailId
				+ ", languageCode=" + languageCode + ", lookupValue="
				+ lookupValue + ", lookupAlternateValue="
				+ lookupAlternateValue + "]";
	}
	
}
