package com.safetynet.alerts;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	private static PersonService personService;
	
	@Mock
	private static PersonRepository personRepository;
	@Mock
	private static FireStationRepository fireStationRepository;
	@Mock
	private static MedicalRecordRepository medicalRecordRepository;
	
	@BeforeEach
	private void setUpPerTest() {
		personService = new PersonService(personRepository, fireStationRepository, medicalRecordRepository);
	}
	
	@Test
	public void addPersonTest() {
		personService.addPerson(new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com"));
		verify(personRepository, times(1)).add(any(Person.class));
	}
	
	@Test
	public void updatePersonTest() {
		personService.updatePerson(new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com"));
		verify(personRepository, times(1)).update(any(Person.class));
	}
	
	@Test
	public void deletePersonTest() {
		personService.deletePerson(new Person("Jean", "Dupont", "rue des fleurs", "NY", "123456", "0102030405", "jean@dupont.com"));
		verify(personRepository, times(1)).delete(any(Person.class));
	}
	
	@Test
	public void getAllPersonTest() {
		personService.getAllPersons();
		verify(personRepository, times(1)).findAll();
	}
	
	@Test
	public void getPersonByAddressTest() {
		personService.getPersonByAddress("");
		verify(personRepository, times(1)).findByAddress(any(String.class));
	}
	
	@Test
	public void getPersonByLastNameTest() {
		personService.getPersonByLastName("");
		verify(personRepository, times(1)).findByLastName(any(String.class));
	}
	
//	@Test
//	public void getPersonByIdentityTest() {
//		personService.getPersonByIdentity("", "");
//		verify(personRepository, times(1)).findByIdentity(any(String.class), any(String.class));
//	}

}
