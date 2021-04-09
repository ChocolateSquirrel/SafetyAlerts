package com.safetynet.alerts.consumer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository {
	private final DataHandler dataHandler;
	private List<Person> persons;
	
	public PersonRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		persons = dataHandler.getData().getPersons();
	}
	
	public List<Person> findAll(){
		return persons;
	}
	


}
