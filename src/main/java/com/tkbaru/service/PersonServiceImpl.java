package com.tkbaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

}
