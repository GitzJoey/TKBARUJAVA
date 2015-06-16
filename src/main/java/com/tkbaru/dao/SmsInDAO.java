package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.SmsIn;

public interface SmsInDAO {
	public List<SmsIn> getAllSmsInbox();
	public SmsIn getSmsInboxById(int selectedId);
	public void addSmsInbox(SmsIn sms);
	public void editSmsInbox(SmsIn sms);
	public void deleteSmsInbox(int selectedId);
}
