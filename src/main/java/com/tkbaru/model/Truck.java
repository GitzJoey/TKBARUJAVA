package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_truck")
public class Truck {

	public Truck() {}
	
	@Id
	@GeneratedValue
	@Column(name="truck_id")
	private int truckId;
	
	@Column(name="truck_type")
	private String truckType;
	
	@Column(name="weight_type")	
	private String weightType;
	
	@Column(name="plate_number")
	private String plateNumber;
	
	@Column(name="kir_date")
	@Temporal(TemporalType.DATE)
	private Date kirDate;
	
	@Column(name="driver")
	private int driver;
	
	@Column(name="remarks")
	private String remarks;
	
	public int getTruckId() {
		return truckId;
	}
	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public String getWeightType() {
		return weightType;
	}
	public void setWeightType(String weightType) {
		this.weightType = weightType;
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
	
	@Override
	public String toString() {
		return "Truck [truckId=" + truckId + ", truckType=" + truckType
				+ ", weightType=" + weightType + ", plateNumber=" + plateNumber
				+ ", kirDate=" + kirDate + ", driver=" + driver + ", remarks="
				+ remarks + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + truckId;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		if (truckId != other.truckId)
			return false;
		return true;
	}
		
}
