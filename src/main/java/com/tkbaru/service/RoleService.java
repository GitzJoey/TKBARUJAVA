package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Role;

public interface RoleService {
	public List<Role> getAllRole();
	public Role getRoleById(int selectedId);
	public void addRole(Role role);
	public void editRole(Role role);
	public void deleteRole(int selectedId);
	public void generateDefaultRoles();
}
