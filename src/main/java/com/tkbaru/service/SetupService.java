package com.tkbaru.service;

public interface SetupService {
	public boolean checkDBConnection();
	public boolean generateInitData();
	public String encryptString(String s);
}
