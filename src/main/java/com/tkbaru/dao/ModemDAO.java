package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Modem;

public interface ModemDAO {
	public List<Modem> getAllModem();
	public Modem getModemById(int selectedId);
	public void addModem(Modem modem);
	public void editModem(Modem modem);
	public void deleteModem(int selectedId);
}
