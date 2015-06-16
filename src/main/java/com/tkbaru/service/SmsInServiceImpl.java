package com.tkbaru.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.SmsInDAO;
import com.tkbaru.model.SmsIn;

@Service
public class SmsInServiceImpl implements SmsInService {

	@Autowired
	SmsInDAO smsOutboxDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<SmsIn> getAllSmsInbox() {

		return smsOutboxDAO.getAllSmsInbox();
	}

	@Override
	@Transactional
	public SmsIn getSmsInboxById(int selectedId) {

		return smsOutboxDAO.getSmsInboxById(selectedId);
	}

	@Override
	@Transactional
	public void addSmsInbox(SmsIn smsOutbox) {
		smsOutboxDAO.addSmsInbox(smsOutbox);
	}

	@Override
	@Transactional
	public void editSmsInbox(SmsIn smsOutbox) {

		smsOutboxDAO.editSmsInbox(smsOutbox);
	}

	@Override
	@Transactional
	public void deleteSmsInbox(int selectedId) {

		smsOutboxDAO.deleteSmsInbox(selectedId);
	}

}
