package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Role;
import com.tkbaru.model.RoleFunction;

public interface RoleDAO {
	public RoleFunction getRoleFunctionByUserId(int userId);
	public RoleFunction getRoleFunctionById(int userId);
	public List<RoleFunction> getSummaryRoleList();
	
	public List<Role> getAllRole();
	public Role getRoleById(int selectedId);
	public void addRole(Role role);
	public void editRole(Role role);
	public void deleteRole(int selectedId);
}
