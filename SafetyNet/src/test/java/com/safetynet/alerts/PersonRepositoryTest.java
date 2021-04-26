package com.safetynet.alerts;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.consumer.DataHandler;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {
	
	private static PersonRepository personRepository;
	
	@Mock
	private DataHandler dataHandler;
	@Mock
	private List<Person> persons;
	
	@BeforeEach
	private void setUpPerTest() {
		personRepository = new PersonRepository(dataHandler);
	}
	
	@Test
	public void addTest() {
		when(persons.size()).thenReturn(1);
		Person personToAdd = new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com");		
		
		personRepository.add(personToAdd);
		
		assertEquals(2, persons.size());
		verify(dataHandler, times(1)).save();
	}
	
	@Test
	public void updateTest() {
		when(persons.size()).thenReturn(1);
		Person personToUpdate = new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com");		
		
		personRepository.update(personToUpdate);
		
		assertEquals(1, persons.size());
		verify(dataHandler, times(1)).save();
	}
	
	@Test
	public void deleteTest() {
		when(persons.size()).thenReturn(2);
		Person personToDelete = new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com");		
		
		personRepository.delete(personToDelete);
		
		assertEquals(1, persons.size());
		verify(dataHandler, times(1)).save();
	}
	
}
