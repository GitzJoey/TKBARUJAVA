package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.common.Converter;
import com.tkbaru.dao.TruckMtcDAO;
import com.tkbaru.model.TruckMaintenance;

@Service
public class TruckMtcServiceImpl implements TruckMtcService {

	@Autowired
	TruckMtcDAO truckMtcDAO;

	@Autowired
	ServletContext servletContext;
	
	@Override
	@Transactional
	public List<TruckMaintenance> getAllTruckMaintenance() {
		
		return truckMtcDAO.getAllMaintenance();
	}

	@Override
	@Transactional
	public TruckMaintenance getTruckMaintenanceById(int selectedId) {
		
		return truckMtcDAO.getMaintenanceById(selectedId);
	}

	@Override
	@Transactional
	public void addTruckMaintenance(TruckMaintenance mtc) {
		try {
			truckMtcDAO.addMaintenance(mtc);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void editTruckMaintenance(TruckMaintenance mtc) {
		
		truckMtcDAO.editMaintenance(mtc);
	}

	@Override
	@Transactional
	public void deleteTruckMaintenance(int selectedId) {

		truckMtcDAO.deleteMaintenance(selectedId);		
	}

	@Override
	@Transactional
	public List<TruckMaintenance> getTruckMaintenanceByIds(String selectedIds) {		
		if (selectedIds.length() == 0) return new ArrayList<TruckMaintenance>();
		
		return truckMtcDAO.getMaintenanceByIds(Converter.convertToINClause(selectedIds));
	}

}
