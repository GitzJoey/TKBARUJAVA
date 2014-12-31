package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.PhoneList;

public interface PhoneListService {
	public List<PhoneList> getPhoneListById(int personId);

}
