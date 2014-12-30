package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Role;
import com.tkbaru.model.RoleFunction;

public interface RoleService {
	public RoleFunction getRoleFunctionByUserId(int userId);
	public RoleFunction getRoleFunctionById(int roleId);
	public List<RoleFunction> getSummaryRoleList();
	public void addNewRoleFunction(RoleFunction role);
	public void editRoleFunction(RoleFunction role);
	public void deleteRoleFunction(int roleId);
	
	public List<Role> getAllRole();
	public Role getRoleById(int selectedId);
	public void addRole(Role role);
	public void editRole(Role role);
	public void deleteRole(int selectedId);
}
