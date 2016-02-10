package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.TruckMaintenance;

public interface TruckMtcService {
	public List<TruckMaintenance> getAllTruckMaintenance();
	public TruckMaintenance getTruckMaintenanceById(int selectedId);
	public List<TruckMaintenance> getTruckMaintenanceByIds(String selectedIds);
	public void addTruckMaintenance(TruckMaintenance truckMaintenance);
	public void editTruckMaintenance(TruckMaintenance truckMaintenance);
	public void deleteTruckMaintenance(int selectedId);

}
