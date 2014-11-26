package com.tkbaru.model;

public class User {
	public User() {
		this.roleFunctionEntity = new RoleFunction();
		this.personEntity = new Person();
	}
	
	private int userId;
	private String userName;
	private String userPassword;
	private String userStatus;
	
	private int roleId;
	private RoleFunction roleFunctionEntity;
	private int personId;
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
	public RoleFunction getRoleFunctionEntity() {
		return roleFunctionEntity;
	}
	public void setRoleFunctionEntity(RoleFunction roleFunctionEntity) {
		this.roleFunctionEntity = roleFunctionEntity;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public Person getPersonEntity() {
		if (personEntity == null) return new Person();
		return personEntity;
	}
	public void setPersonEntity(Person personEntity) {
		this.personEntity = personEntity;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userStatus="
				+ userStatus + ", roleId=" + roleId + ", roleFunctionEntity=" + roleFunctionEntity + ", personId="
				+ personId + ", personEntity=" + personEntity + "]";
	}
}
