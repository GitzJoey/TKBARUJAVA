package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.RoleDAO;
import com.tkbaru.model.RoleFunction;

public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public RoleFunction getRoleFunctionByUserId(int userId) {
				
		return roleDAO.getRoleFunctionById(userId);
	}

	@Override
	public List<RoleFunction> getSummaryRoleList() {
		
		return roleDAO.getSummaryRoleList();
	}

}
