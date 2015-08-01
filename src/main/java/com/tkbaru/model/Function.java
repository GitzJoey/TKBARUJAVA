package com.tkbaru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_function")
public class Function {
	public Function() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="function_id")
	private int functionId;
	@Column(name="function_code")
	private String functionCode;
	@Column(name="module")
	private String module;
	@Column(name="module_icon")
	private String moduleIcon;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_icon")
	private String menuIcon;
	@Column(name="url")
	private String urlLink;
	@Column(name="order_num")
	private int orderNum;
	@Column(name="parent_function_id")
	private Integer parentFunctionId;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_function_id", insertable=false, updatable=false)
    private Function function;
 
    @OneToMany(mappedBy="function", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Function> subFunctions = new ArrayList<Function>();
	
	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(Integer parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public List<Function> getSubFunctions() {
		return subFunctions;
	}

	public void setSubFunctions(List<Function> subFunctions) {
		this.subFunctions = subFunctions;
	}

	@Override
	public String toString() {
		return "Function [functionId=" + functionId + ", functionCode=" + functionCode + ", module=" + module
				+ ", moduleIcon=" + moduleIcon + ", menuName=" + menuName + ", menuIcon=" + menuIcon + ", urlLink="
				+ urlLink + ", orderNum=" + orderNum + ", parentFunctionId=" + parentFunctionId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", subFunctions="+ subFunctions +"]";
	}
	
}
