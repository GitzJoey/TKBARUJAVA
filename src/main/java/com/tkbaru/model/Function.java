package com.tkbaru.model;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_function")
public class Function implements Serializable {
	private static final long serialVersionUID = 1106158575923022422L;

	public Function() {
		
	}

	public Function(String functionCode,
					String menuName,
					String menuIcon,
					String urlLink,
					Integer orderNum,
					Integer parentFunctionId) {
		this.functionCode = functionCode;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.urlLink = urlLink;
		this.orderNum = orderNum;
		this.parentFunctionId = parentFunctionId;
		this.createdBy = 0;
		this.createdDate = new Date();
	}
	
	@Id
	@GeneratedValue
	@Column(name="function_id")
	private Integer functionId;
	@Column(name="function_code")
	private String functionCode;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_icon")
	private String menuIcon;
	@Column(name="url")
	private String urlLink;
	@Column(name="order_num")
	private Integer orderNum;
	@Column(name="parent_function_id")
	private Integer parentFunctionId;
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
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_function_id", insertable=false, updatable=false)
    private Function function;
 
    @OneToMany(mappedBy="function", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Function> subFunctions = new ArrayList<Function>();

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(Integer parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
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
		return "Function [functionId=" + functionId + ", functionCode=" + functionCode + ", menuName=" + menuName
				+ ", menuIcon=" + menuIcon + ", urlLink=" + urlLink + ", orderNum=" + orderNum + ", parentFunctionId="
				+ parentFunctionId + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", subFunctions=" + subFunctions + "]";
	}
    
}
