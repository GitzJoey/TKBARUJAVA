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
@Table(name="tb_truck_maintenance")
public class TruckMaintenance {
	public TruckMaintenance() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="truck_maintenance_id")
	private Integer truckMaintenanceId;
	@Column(name="truck_id")
	private Integer truckId;
	@Column(name="cost")
	private Integer cost;
	@Column(name="odometer")
	private Integer odometer;
	@Column(name="remarks")
	private String remarks;
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
	@JoinColumn(name="maintenance_type", referencedColumnName="lookup_key")
	private Lookup maintenanceTypeLookup;

	public Integer getTruckMaintenanceId() {
		return truckMaintenanceId;
	}

	public void setTruckMaintenanceId(Integer truckMaintenanceId) {
		this.truckMaintenanceId = truckMaintenanceId;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getOdometer() {
		return odometer;
	}

	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}

	public Lookup getMaintenanceTypeLookup() {
		return maintenanceTypeLookup;
	}

	public void setMaintenanceTypeLookup(Lookup maintenanceTypeLookup) {
		this.maintenanceTypeLookup = maintenanceTypeLookup;
	}

	public Integer getTruckId() {
		return truckId;
	}

	public void setTruckId(Integer truckId) {
		this.truckId = truckId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	@Override
	public String toString() {
		return "Truck [truckId=" + truckId + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", maintenanceTypeLookup="
				+ maintenanceTypeLookup +  "]";
	}

}
