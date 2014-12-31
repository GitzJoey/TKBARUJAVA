package com.tkbaru.service;

import com.tkbaru.model.Person;

public interface PersonService {
	public Person getPersonEntityById(int personId);
	public void addPerson(Person person);
	public void editPerson(Person person);
}
