package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Truck;

public interface TruckDAO {
	public List<Truck> getAllTruck();
	public Truck getTruckById(int id);
	public void addTruck(Truck truck);
	public void editTruck(Truck truck);
	public void deleteTruck(int id);
}
