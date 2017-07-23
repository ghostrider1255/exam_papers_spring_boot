package com.sreepapers.app.web.services;

import java.util.List;

import com.sreepapers.app.web.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	
}
