package com.tkbaru.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.SmsOutDAO;
import com.tkbaru.model.SmsOut;

@Service
public class SmsOutServiceImpl implements SmsOutService {

	@Autowired
	SmsOutDAO smsOutboxDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<SmsOut> getAllSmsOutbox() {

		return smsOutboxDAO.getAllSmsOutbox();
	}

	@Override
	@Transactional
	public SmsOut getSmsOutboxById(int selectedId) {

		return smsOutboxDAO.getSmsOutboxById(selectedId);
	}

	@Override
	@Transactional
	public void addSmsOutbox(SmsOut smsOutbox) {
		smsOutboxDAO.addSmsOutbox(smsOutbox);
	}

	@Override
	@Transactional
	public void editSmsOutbox(SmsOut smsOutbox) {

		smsOutboxDAO.editSmsOutbox(smsOutbox);
	}

	@Override
	@Transactional
	public void deleteSmsOutbox(int selectedId) {

		smsOutboxDAO.deleteSmsOutbox(selectedId);
	}

}
