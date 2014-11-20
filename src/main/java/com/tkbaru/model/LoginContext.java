package com.tkbaru.model;

public class LoginContext {
	public LoginContext() {
		
	}
	
	private User userLogin;
	private String selectedMenu;
	
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
	
}
