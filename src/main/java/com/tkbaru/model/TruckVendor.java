package com.tkbaru.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_vendor_truck")
public class TruckVendor implements Serializable {
	private static final long serialVersionUID = -1891607902788452322L;

	public TruckVendor() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vendor_truck_id")
	private Integer vendorTruckId;
	@Column(name="vendor_truck_name")
	private String vendorTruckName;
	@Column(name="vendor_truck_address")
	private String vendorTruckAddress;
	@Column(name="npwp_number")
	private String npwpNumber;
	@Column(name="account")
	private Integer accNum;
	@Column(name="remarks")
	private String remarks;

	@ManyToOne
	@JoinColumn(name="bank", referencedColumnName="lookup_key")
	private Lookup truckVendorBankLookup;

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup truckVendorStatusLookup;

	public Integer getVendorTruckId() {
		return vendorTruckId;
	}

	public void setVendorTruckId(Integer vendorTruckId) {
		this.vendorTruckId = vendorTruckId;
	}

	public String getVendorTruckName() {
		return vendorTruckName;
	}

	public void setVendorTruckName(String vendorTruckName) {
		this.vendorTruckName = vendorTruckName;
	}

	public String getVendorTruckAddress() {
		return vendorTruckAddress;
	}

	public void setVendorTruckAddress(String vendorTruckAddress) {
		this.vendorTruckAddress = vendorTruckAddress;
	}

	public String getNpwpNumber() {
		return npwpNumber;
	}

	public void setNpwpNumber(String npwpNumber) {
		this.npwpNumber = npwpNumber;
	}

	public Integer getAccNum() {
		return accNum;
	}

	public void setAccNum(Integer accNum) {
		this.accNum = accNum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Lookup getTruckVendorBankLookup() {
		return truckVendorBankLookup;
	}

	public void setTruckVendorBankLookup(Lookup truckVendorBankLookup) {
		this.truckVendorBankLookup = truckVendorBankLookup;
	}

	public Lookup getTruckVendorStatusLookup() {
		return truckVendorStatusLookup;
	}

	public void setTruckVendorStatusLookup(Lookup truckVendorStatusLookup) {
		this.truckVendorStatusLookup = truckVendorStatusLookup;
	}

	@Override
	public String toString() {
		return "TruckVendor [vendorTruckId=" + vendorTruckId + ", vendorTruckName=" + vendorTruckName
				+ ", vendorTruckAddress=" + vendorTruckAddress + ", npwpNumber=" + npwpNumber + ", accNum=" + accNum
				+ ", remarks=" + remarks + ", truckVendorBankLookup=" + "truckVendorBankLookup"
				+ ", truckVendorStatusLookup=" + "truckVendorStatusLookup" + "]";
	}
	
}

