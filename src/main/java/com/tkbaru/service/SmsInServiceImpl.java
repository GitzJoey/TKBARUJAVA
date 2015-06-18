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
	SmsInDAO smsInDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<SmsIn> getAllSmsIn() {

		return smsInDAO.getAllSmsIn();
	}

	@Override
	@Transactional
	public SmsIn getSmsInById(int selectedId) {

		return smsInDAO.getSmsInById(selectedId);
	}

	@Override
	@Transactional
	public void addSmsIn(SmsIn smsOutbox) {
		smsInDAO.addSmsIn(smsOutbox);
	}

	@Override
	@Transactional
	public void editSmsIn(SmsIn smsOutbox) {

		smsInDAO.editSmsIn(smsOutbox);
	}

	@Override
	@Transactional
	public void deleteSmsIn(int selectedId) {

		smsInDAO.deleteSmsIn(selectedId);
	}

}
