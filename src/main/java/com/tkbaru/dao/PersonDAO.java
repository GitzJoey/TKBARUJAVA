package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Person;
import com.tkbaru.model.PhoneList;

public interface PersonDAO {
	public Person getPersonEntityById(int personId);
	public List<PhoneList> getPhoneListById(int personId);
	public void addPerson(Person person);
	public void editPerson(Person person);
}
