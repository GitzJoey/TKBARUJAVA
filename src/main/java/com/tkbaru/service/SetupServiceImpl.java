package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.dao.SetupDAO;

@Service
public class SetupServiceImpl implements SetupService {

	@Autowired
	SetupDAO setupDAO;
	
	@Override
	public boolean checkDBConnection() {
		
		return setupDAO.checkDBConnection();
	}

}
