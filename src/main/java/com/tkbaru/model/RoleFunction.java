package com.tkbaru.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_rolefunction")
public class RoleFunction {
	public RoleFunction() {
		
	}
	
	@Id
	@Column(name="rolefunction_id")
	private int roleFunctionId;
	@Column(name="role_id")
	private int roleId;
	@Transient
	private String roleName;
	@Column(name="function_id")
	private int functionId;
	
	private List<Function> functionList;
	
	public int getRoleFunctionId() {
		return roleFunctionId;
	}

	public void setRoleFunctionId(int roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}

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
		return "RoleFunction [roleId=" + roleId + ", roleName=" + roleName + ", functionList=" + functionList + "]";
	}
}
