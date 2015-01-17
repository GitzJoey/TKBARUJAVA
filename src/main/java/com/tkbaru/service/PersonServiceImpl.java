package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.PersonDAO;
import com.tkbaru.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDAO personDAO;
	
	@Override
	public Person getPersonEntityById(int personId) {
		
		return personDAO.getPersonEntityById(personId);
	}

	@Override
	@Transactional
	public Person getPersonById(int selectedId) {
		
		return personDAO.getPersonById(selectedId);
	}

	@Override
	@Transactional
	public int addPerson(Person person) {
		
		return personDAO.addPerson(person);
	}

	@Override
	@Transactional
	public void editPerson(Person person) {
		
		personDAO.editPerson(person);
	}

	@Override
	@Transactional
	public void deletePerson(int selectedId) {
		
		personDAO.deletePerson(selectedId);
	}

}
