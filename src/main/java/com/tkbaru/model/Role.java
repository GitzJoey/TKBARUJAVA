package com.tkbaru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@Entity
@Table(name="tb_role")
@SuppressWarnings("unchecked")
public class Role implements Serializable {
	private static final long serialVersionUID = 2049060061621060086L;

	public Role() {
		
	}

	public Role(String roleName) {
		this.roleName = roleName;
		this.createdBy = 0;
		this.createdDate = new Date();
	}
	
	@Id
	@GeneratedValue
	@Column(name="role_id")	
	private Integer roleId;
	@Column(name="name")
	private String roleName;
	@Column(name="created_by")
	private Integer createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private Integer updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public String getAllFunctionIdInString() {
		String r = "";
		for (Function f:this.functionList) { r += String.valueOf(f.getFunctionId()) + ","; }
		
		if (r.length() == 0) return r;
		
		r = r.substring(0, r.length() - 1);
		return r;
	}	

	public List<Function> getAllRootFunctions() {
		List<Function> fL = new ArrayList<Function>();
	
		for (Function f:this.functionList) { 
			if (f.getParentFunctionId() == null) {
				fL.add(f);
			}
		}
		
		return fL;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="tb_role_function", 
			joinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")},
			inverseJoinColumns={@JoinColumn(name="function_id", referencedColumnName="function_id")})
	List<Function> functionList = LazyList.decorate(new ArrayList<Function>(), FactoryUtils.instantiateFactory(Function.class));

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup roleStatusLookup;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public Lookup getRoleStatusLookup() {
		return roleStatusLookup;
	}

	public void setRoleStatusLookup(Lookup roleStatusLookup) {
		this.roleStatusLookup = roleStatusLookup;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", functionList="
				+ functionList + ", roleStatusLookup=" + roleStatusLookup + "]";
	}

}
