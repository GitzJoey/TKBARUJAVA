package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_truck")
public class Truck {
	public Truck() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="truck_id")
	private int truckId;
	@Column(name="plate_number")
	private String plateNumber;
	@Column(name="kir_date")
	@Temporal(TemporalType.DATE)
	private Date kirDate;
	@Column(name="driver")
	private int driver;
	@Column(name="remarks")
	private String remarks;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup truckStatusLookup;

	@ManyToOne
	@JoinColumn(name="truck_type", referencedColumnName="lookup_key")
	private Lookup truckTypeLookup;

	@ManyToOne
	@JoinColumn(name="weight_type", referencedColumnName="lookup_key")
	private Lookup weightTypeLookup;

	public int getTruckId() {
		return truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Date getKirDate() {
		return kirDate;
	}

	public void setKirDate(Date kirDate) {
		this.kirDate = kirDate;
	}

	public int getDriver() {
		return driver;
	}

	public void setDriver(int driver) {
		this.driver = driver;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Lookup getTruckStatusLookup() {
		return truckStatusLookup;
	}

	public void setTruckStatusLookup(Lookup truckStatusLookup) {
		this.truckStatusLookup = truckStatusLookup;
	}

	public Lookup getTruckTypeLookup() {
		return truckTypeLookup;
	}

	public void setTruckTypeLookup(Lookup truckTypeLookup) {
		this.truckTypeLookup = truckTypeLookup;
	}

	public Lookup getWeightTypeLookup() {
		return weightTypeLookup;
	}

	public void setWeightTypeLookup(Lookup weightTypeLookup) {
		this.weightTypeLookup = weightTypeLookup;
	}

	@Override
	public String toString() {
		return "Truck [truckId=" + truckId + ", plateNumber=" + plateNumber + ", kirDate=" + kirDate + ", driver="
				+ driver + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", truckStatusLookup="
				+ truckStatusLookup + ", truckTypeLookup=" + truckTypeLookup + ", weightTypeLookup=" + weightTypeLookup
				+ "]";
	}

}
