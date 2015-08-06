package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.RoleDAO;
import com.tkbaru.model.Function;
import com.tkbaru.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	FunctionService functionManager;
	
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

	@Override
	@Transactional
	public void generateDefaultRoles() {

		List<Function> allF = functionManager.getAllFunctions();
		
		//Create Admin Role
		Role r1 = new Role("ADMIN");
		r1.setRoleStatus("L001_A");
		r1.setFunctionList(allF);
		
		List<Function> partF = new ArrayList<Function>();
		for (Function f : allF) {
			if (f.getFunctionCode().contains("F_ADM")) continue;
			partF.add(f);
		}
		
		//Create Non Admin Role
		Role r2 = new Role("NONADMIN");
		r2.setRoleStatus("L001_A");
		r2.setFunctionList(partF);

		addRole(r1);
		addRole(r2);
	}
}
