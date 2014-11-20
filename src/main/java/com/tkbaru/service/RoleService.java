package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.RoleFunction;

public interface RoleService {
	public RoleFunction getRoleFunctionByUserId(int userId);
	public List<RoleFunction> getSummaryRoleList();
}
