package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.functors.FalsePredicate;

@Entity
@Table(name="tb_user")
public class User {
	public User() {

	}
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@Column(name="user_name")
	private String userName;
	@Transient
	private String userPassword;
	@Column(name="status")
	private String userStatus;
	
	@Column(name="role_id")
	private int roleId;
	private RoleFunction roleFunctionEntity;
	@Column(name="person_id")
	private int personId;
	@OneToOne
	@JoinColumn(name="person_id", insertable=false, updatable=false)
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
