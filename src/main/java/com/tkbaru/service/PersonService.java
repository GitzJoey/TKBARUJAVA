package com.tkbaru.service;

import com.tkbaru.model.Person;

public interface PersonService {
	public Person getPersonEntityById(int personId);

	public Person getPersonById(int selectedId);
	public int addPerson(Person person);
	public void editPerson(Person person);
	public void deletePerson(int selectedId);

}
