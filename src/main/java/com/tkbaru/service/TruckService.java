package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.Truck;

public interface TruckService {
	public List<Truck> getAllTruck();
	public Truck getTruckById(int id);
	public void addTruck(Truck truck);
	public void editTruck(Truck truck);
	public void deleteTruck(int id);

}
