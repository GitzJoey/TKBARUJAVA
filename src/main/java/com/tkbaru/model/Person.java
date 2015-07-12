package com.tkbaru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="tb_person")
@SuppressWarnings("unchecked")
public class Person {
	public Person() {
		
	}

	@Id
	@Column(name="person_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int personId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="addr_1")
	private String addressLine1;
	@Column(name="addr_2")
	private String addressLine2;
	@Column(name="addr_3")
	private String addressLine3;
	@Column(name="email")
	private String emailAddr;
	@Column(name="photo_path")
	private String photoPath;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;

	@Transient
	private MultipartFile imageBinary;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_person_phonelist", joinColumns={ @JoinColumn(name="person_id", referencedColumnName="person_id") }, inverseJoinColumns={ @JoinColumn(name="phonelist_id", referencedColumnName="phonelist_id") })
	private List<PhoneList> phoneList = LazyList.decorate(new ArrayList<PhoneList>(), FactoryUtils.instantiateFactory(PhoneList.class));

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
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

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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

	public MultipartFile getImageBinary() {
		return imageBinary;
	}

	public void setImageBinary(MultipartFile imageBinary) {
		this.imageBinary = imageBinary;
	}

	public List<PhoneList> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneList> phoneList) {
		this.phoneList = phoneList;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", addressLine3="
				+ addressLine3 + ", emailAddr=" + emailAddr + ", photoPath="
				+ photoPath + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", imageBinary=" + imageBinary + ", phoneList="
				+ phoneList + "]";
	}

}
