package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_role")
@SuppressWarnings("unchecked")
public class Role {
	public Role() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="role_id")	
	private int roleId;
	@Column(name="name")
	private String roleName;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_role_function", 
			joinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")},
			inverseJoinColumns={@JoinColumn(name="function_id", referencedColumnName="function_id")})
	List<Function> functionList = LazyList.decorate(new ArrayList<Function>(), FactoryUtils.instantiateFactory(Function.class));
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Function> getFunctionList() {
		return functionList;
	}
	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", functionList=" + functionList + "]";
	}
}
