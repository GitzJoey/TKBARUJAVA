package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.TruckDAO;
import com.tkbaru.model.Truck;

@Service
public class TruckServiceImpl implements TruckService {

	@Autowired
	TruckDAO truckDao;
	
	@Override
	@Transactional
	public List<Truck> getAllTruck() {
		return truckDao.getAllTruck();
	}

	@Override
	@Transactional
	public Truck getTruckById(int id) {		
		return truckDao.getTruckById(id);
	}

	@Override
	@Transactional
	public void addTruck(Truck truck) {
		truckDao.addTruck(truck);		
	}

	@Override
	@Transactional
	public void editTruck(Truck truck) {
		truckDao.editTruck(truck);		
	}

	@Override
	@Transactional
	public void deleteTruck(int id) {
		truckDao.deleteTruck(id);		
	}

}
