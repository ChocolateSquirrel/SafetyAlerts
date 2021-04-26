package com.safetynet.alerts;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.service.AlertsService;

@ExtendWith(MockitoExtension.class)
public class AlertsServiceTest {

	private static AlertsService alertsService;;
	
	@Mock
	private static PersonRepository personRepository;
	@Mock
	private static FireStationRepository fireStationRepository;
	@Mock
	private static MedicalRecordRepository medicalRecordRepository;
	
//	@Test
//	public void getPeopleCoverredByAFireStationTest() {
//		//GIVEN
//		Person adult1 = new Person();
//		Person adult2 = new Person();
//		
//		when(personRepository.findByAddress(any(String.class))).thenReturn();
//		//ACT
//		
//		//THEN
//	}
}
