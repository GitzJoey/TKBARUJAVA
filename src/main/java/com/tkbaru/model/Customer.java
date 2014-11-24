package com.tkbaru.model;

import java.util.List;

public class Customer {
	public Customer() {
		
	}
	
	private int customerId;
	private String storeName;
	private String storeAddress;
	private String storeRemarks;
	private String storeStatus;
	
	private List<Person> picList;
	private List<BankAccount> bankAccList;
	
}
