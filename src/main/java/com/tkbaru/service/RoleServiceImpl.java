package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.RoleDAO;
import com.tkbaru.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;
	
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
