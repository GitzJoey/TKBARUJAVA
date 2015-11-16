package com.tkbaru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoginContext implements Serializable {
	private static final long serialVersionUID = 6867902303108888160L;

	public LoginContext() {
		
	}
	
	private User userLogin;

	private List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
	private List<SalesOrder> soList = new ArrayList<SalesOrder>();
	
	public User getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}
	public List<PurchaseOrder> getPoList() {
		return poList;
	}
	public void setPoList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}
	public List<SalesOrder> getSoList() {
		return soList;
	}
	public void setSoList(List<SalesOrder> soList) {
		this.soList = soList;
	}
	@Override
	public String toString() {
		return "LoginContext [userLogin=" + userLogin + ", poList=" + poList + ", soList=" + soList + "]";
	}
}

