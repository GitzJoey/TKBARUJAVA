package com.tkbaru.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_supplier_pic")
public class SupplierPIC {

	public SupplierPIC() {
	}

	@Id
	@Column(name = "supplier_pic_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierPICId;
	@Column(name = "supplier_id")
	private int supplierId;
	@Column(name = "person_id")
	private int personId;
	@Column(name = "status")
	private String picRemarks;

	@ManyToOne
	@JoinColumn(name = "supplier_id", insertable = false, updatable = false)
	private Supplier supplierEnt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", insertable = false, updatable = false)
	private Person personDetail;

	public Person getPersonDetail() {
		return personDetail;
	}

	public int getSupplierPICId() {
		return supplierPICId;
	}

	public void setSupplierPICId(int supplierPICId) {
		this.supplierPICId = supplierPICId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPicRemarks() {
		return picRemarks;
	}

	public void setPicRemarks(String picRemarks) {
		this.picRemarks = picRemarks;
	}

	@Override
	public String toString() {
		return "SupplierPIC [supplierPICId=" + supplierPICId + ", personId="
				+ personId + ", picRemarks=" + picRemarks + "]";
	}
}
