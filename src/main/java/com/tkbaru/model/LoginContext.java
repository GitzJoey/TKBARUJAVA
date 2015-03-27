package com.tkbaru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "session")
public class LoginContext implements Serializable {
	private static final long serialVersionUID = 6867902303108888160L;

	public LoginContext() {

	}

	private User userLogin;
	private String selectedMenu;
	private List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();

	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public List<PurchaseOrder> getPoList() {
		return poList;
	}

	public void setPoList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}

	@Override
	public String toString() {
		return "LoginContext [userLogin=" + userLogin + ", selectedMenu="
				+ selectedMenu + "]";
	}

}
