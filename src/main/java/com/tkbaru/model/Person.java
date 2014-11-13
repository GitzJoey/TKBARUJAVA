package com.tkbaru.model;

import java.util.List;

public class Person {
	public Person() {
		
	}
	
	private int personId;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	
	private List<PhoneList> phoneList;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public List<PhoneList> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneList> phoneList) {
		this.phoneList = phoneList;
	}
	
}
