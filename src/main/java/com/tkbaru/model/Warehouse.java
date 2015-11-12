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

@Entity
@Table(name="tb_warehouse")
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1398316374262489320L;

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
	private Lookup warehouseStatusLookup;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store warehouseStoreEntity;

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

	public Lookup getWarehouseStatusLookup() {
		return warehouseStatusLookup;
	}

	public void setWarehouseStatusLookup(Lookup warehouseStatusLookup) {
		this.warehouseStatusLookup = warehouseStatusLookup;
	}

	public Store getWarehouseStoreEntity() {
		return warehouseStoreEntity;
	}

	public void setWarehouseStoreEntity(Store warehouseStoreEntity) {
		this.warehouseStoreEntity = warehouseStoreEntity;
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", warehouseLocation="
				+ warehouseLocation + ", warehouseRemarks=" + warehouseRemarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", warehouseStatusLookup=" + warehouseStatusLookup + ", warehouseStoreEntity=" + warehouseStoreEntity
				+ "]";
	}

}
