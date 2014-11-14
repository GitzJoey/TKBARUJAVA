package com.tkbaru.dao.role;

import com.tkbaru.model.RoleFunction;

public interface RoleDAO {
	public RoleFunction getRoleFunctionByUserId(int userId);
}
