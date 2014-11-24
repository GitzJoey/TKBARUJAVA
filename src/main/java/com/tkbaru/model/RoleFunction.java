package com.tkbaru.model;

import java.util.List;

public class RoleFunction {
	public RoleFunction() {
		
	}
	
	private int roleId;
	private String roleName;
	
	private List<Function> functionList;

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
