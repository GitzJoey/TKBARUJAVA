package com.tkbaru.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.ModemDAO;
import com.tkbaru.model.Modem;

@Service
public class ModemServiceImpl implements ModemService {

	@Autowired
	ModemDAO modemDAO;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public List<Modem> getAllModem() {

		return modemDAO.getAllModem();
	}

	@Override
	@Transactional
	public Modem getModemById(int selectedId) {

		return modemDAO.getModemById(selectedId);
	}

	@Override
	@Transactional
	public void addModem(Modem modem) {
		modemDAO.addModem(modem);
	}

	@Override
	@Transactional
	public void editModem(Modem modem) {

		modemDAO.editModem(modem);
	}

	@Override
	@Transactional
	public void deleteModem(int selectedId) {

		modemDAO.deleteModem(selectedId);
	}

}
