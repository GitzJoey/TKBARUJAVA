package com.tkbaru.model;

import java.util.List;

public class Function {
	public Function() {
		
	}
	
	private int functionId;
	private String functionCode;
	private String module;
	private String moduleIcon;
	private String menuName;
	private String menuIcon;
	private String urlLink;
	private int orderNum;
	private int deepLevel;
	
	private List<Action> allowedAction;

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

	public int getDeepLevel() {
		return deepLevel;
	}

	public void setDeepLevel(int deepLevel) {
		this.deepLevel = deepLevel;
	}

	public List<Action> getAllowedAction() {
		return allowedAction;
	}

	public void setAllowedAction(List<Action> allowedAction) {
		this.allowedAction = allowedAction;
	}
	
}
