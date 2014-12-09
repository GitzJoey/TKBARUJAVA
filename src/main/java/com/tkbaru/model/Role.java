package com.tkbaru.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_role")
public class Role {
	public Role() {
		
	}
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	@Column(name="role_name")
	private String roleName;
	
	@SuppressWarnings("unchecked")
	@OneToMany(mappedBy="roleEnt")
	List<RoleFunction> roleFunctionList = LazyList.decorate(new ArrayList<RoleFunction>(), FactoryUtils.instantiateFactory(RoleFunction.class));
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
