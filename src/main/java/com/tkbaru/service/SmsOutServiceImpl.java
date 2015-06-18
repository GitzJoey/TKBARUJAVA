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
	SmsOutDAO smsOutDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<SmsOut> getAllSmsOutbox() {

		return smsOutDAO.getAllSmsOutbox();
	}

	@Override
	@Transactional
	public SmsOut getSmsOutboxById(int selectedId) {

		return smsOutDAO.getSmsOutboxById(selectedId);
	}

	@Override
	@Transactional
	public void addSmsOutbox(SmsOut smsOutbox) {
		smsOutDAO.addSmsOutbox(smsOutbox);
	}

	@Override
	@Transactional
	public void editSmsOutbox(SmsOut smsOutbox) {

		smsOutDAO.editSmsOutbox(smsOutbox);
	}

	@Override
	@Transactional
	public void deleteSmsOutbox(int selectedId) {

		smsOutDAO.deleteSmsOutbox(selectedId);
	}

}
