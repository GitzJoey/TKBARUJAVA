package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_warehouse")
public class Warehouse {
	public Warehouse() {
		
	}
	
	@Id
	@Column(name="warehouse_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int warehouseId;
	@Column(name="name")
	private String warehouseName;
	@Column(name="location")
	private String warehouseLocation;
	@Column(name="remarks")
	private String warehouseRemarks;
	@Column(name="status")
	private String warehouseStatus;
	
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseLocation() {
		return warehouseLocation;
	}
	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}
	public String getWarehouseRemarks() {
		return warehouseRemarks;
	}
	public void setWarehouseRemarks(String warehouseRemarks) {
		this.warehouseRemarks = warehouseRemarks;
	}
	public String getWarehouseStatus() {
		return warehouseStatus;
	}
	public void setWarehouseStatus(String warehouseStatus) {
		this.warehouseStatus = warehouseStatus;
	}
	@Override
	public String toString() {
		return "warehouse [warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", warehouseLocation=" + warehouseLocation
				+ ", warehouseRemarks=" + warehouseRemarks
				+ ", warehouseStatus=" + warehouseStatus + "]";
	}
}
