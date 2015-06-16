package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SmsOut;

public interface SmsOutDAO {
	public List<SmsOut> getAllSmsOutbox();
	public SmsOut getSmsOutboxById(int selectedId);
	public void addSmsOutbox(SmsOut sms);
	public void editSmsOutbox(SmsOut sms);
	public void deleteSmsOutbox(int selectedId);
}
