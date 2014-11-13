package com.tkbaru.model;

public class User {
	public User() {
		
	}
	
	private int userId;
	private String userName;
	private String userPassword;
	private int roleId;
	private String userRole;
	private Person personEntity;
	
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Person getPersonEntity() {
		return personEntity;
	}
	public void setPersonEntity(Person personEntity) {
		this.personEntity = personEntity;
	}
}
