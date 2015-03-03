package com.tkbaru.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_purchaseorder")
public class PurchaseOrder {
	public PurchaseOrder() {
		
	}
	
	private int poId;
	private String poCode;
	private Date poCreatedDate;
	private Date shippingDate;
	private int supplierId;
	
}
