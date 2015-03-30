package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 3890210367688787057L;

	public User() {
		personEntity = new Person();
		roleEntity = new Role();
	}
	
	private int userId;
	private String userName;
	private String userPassword;
	private String userStatus;
	
	private int roleId;
	private Role roleEntity;
	
	private int personId;
	private Person personEntity;

	private int storeId;
	private Store storeEntity;

	private int createdBy;
	private Date createdDate;
	private int updatedBy;
	private Date updatedDate;
	
	private Lookup statusLookup;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Role getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(Role roleEntity) {
		this.roleEntity = roleEntity;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public Person getPersonEntity() {
		return personEntity;
	}
	public void setPersonEntity(Person personEntity) {
		this.personEntity = personEntity;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Store getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(Store storeEntity) {
		this.storeEntity = storeEntity;
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
	public Lookup getStatusLookup() {
		return statusLookup;
	}
	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userStatus="
				+ userStatus + ", roleId=" + roleId + ", roleEntity="
				+ roleEntity + ", personId=" + personId + ", personEntity="
				+ personEntity + ", storeId=" + storeId + ", storeEntity="
				+ storeEntity + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}
}
