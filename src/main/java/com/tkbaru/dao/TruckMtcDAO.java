package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.TruckMaintenance;

public interface TruckMtcDAO {
	public List<TruckMaintenance> getAllMaintenance();
	public TruckMaintenance getMaintenanceById(int selectedId);
	public List<TruckMaintenance> getMaintenanceByIds(String selectedIdINClause);
	public void addMaintenance(TruckMaintenance mtc);
	public void editMaintenance(TruckMaintenance mtc);
	public void deleteMaintenance(int selectedId);
}
