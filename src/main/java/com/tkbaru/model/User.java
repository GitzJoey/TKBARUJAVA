package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 3890210367688787057L;

	public User() {
		personEntity = new Person();
		roleEntity = new Role();
	}
	
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userStatus;
	
	private Integer roleId;
	private Role roleEntity;
	
	private Integer personId;
	private Person personEntity;

	private Integer storeId;
	private Store storeEntity;

	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	
	private Lookup statusLookup;
	
	private String userType;
	private String allowLogin;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(Role roleEntity) {
		this.roleEntity = roleEntity;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Person getPersonEntity() {
		return personEntity;
	}
	public void setPersonEntity(Person personEntity) {
		this.personEntity = personEntity;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Store getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(Store storeEntity) {
		this.storeEntity = storeEntity;
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
	public Lookup getStatusLookup() {
		return statusLookup;
	}
	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAllowLogin() {
		return allowLogin;
	}
	public void setAllowLogin(String allowLogin) {
		this.allowLogin = allowLogin;
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
