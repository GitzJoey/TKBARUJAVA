package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.SmsIn;

public interface SmsInService {
	public List<SmsIn> getAllSmsIn();
	public SmsIn getSmsInById(int selectedId);
	public void addSmsIn(SmsIn sms);
	public void editSmsIn(SmsIn sms);
	public void deleteSmsIn(int selectedId);

}
