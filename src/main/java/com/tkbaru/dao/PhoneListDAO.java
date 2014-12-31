package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.PhoneList;

public interface PhoneListDAO {
	public List<PhoneList> getPhoneListById(int personId);
}
