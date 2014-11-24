package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.RoleFunction;

public interface RoleDAO {
	public RoleFunction getRoleFunctionByUserId(int userId);
	public RoleFunction getRoleFunctionById(int userId);
	public List<RoleFunction> getSummaryRoleList();
}
