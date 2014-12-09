package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Function;
import com.tkbaru.model.RoleFunction;

public interface RoleService {
	public RoleFunction getRoleFunctionByUserId(int userId);
	public RoleFunction getRoleFunctionById(int roleId);
	public List<RoleFunction> getSummaryRoleList();
	public void addNewRoleFunction(RoleFunction role);
	public void editRoleFunction(RoleFunction role);
	public void deleteRoleFunction(int roleId);
}
