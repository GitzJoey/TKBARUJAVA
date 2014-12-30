package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.RoleDAO;
import com.tkbaru.model.Role;
import com.tkbaru.model.RoleFunction;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public RoleFunction getRoleFunctionByUserId(int userId) {
				
		return roleDAO.getRoleFunctionByUserId(userId);
	}

	@Override
	public List<RoleFunction> getSummaryRoleList() {
		
		return roleDAO.getSummaryRoleList();
	}

	@Override
	public RoleFunction getRoleFunctionById(int roleId) {
		
		return roleDAO.getRoleFunctionById(roleId);
	}

	@Override
	public void addNewRoleFunction(RoleFunction role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRoleFunction(RoleFunction role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleFunction(int roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Role> getAllRole() {

		return roleDAO.getAllRole();
	}

	@Override
	@Transactional
	public Role getRoleById(int selectedId) {

		return roleDAO.getRoleById(selectedId);
	}

	@Override
	@Transactional
	public void addRole(Role role) {

		roleDAO.addRole(role);
	}

	@Override
	@Transactional
	public void editRole(Role role) {

		roleDAO.editRole(role);
	}

	@Override
	@Transactional
	public void deleteRole(int selectedId) {

		roleDAO.deleteRole(selectedId);
	}
}
