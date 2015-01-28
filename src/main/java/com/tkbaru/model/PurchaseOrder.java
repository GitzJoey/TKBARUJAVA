package com.tkbaru.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_purchaseorder")
public class PurchaseOrder {
	public PurchaseOrder() {
		
	}
	
	private int poId;
	private String poCode;
}
