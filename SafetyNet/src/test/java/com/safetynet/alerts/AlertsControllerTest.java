package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.jsoniter.JsonIterator;
import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FireStationDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.FloodDTOInfo;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.Person;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class AlertsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Test
	@Tag("firestation")
	public void whenValidInput_GetPeopleCoveredByAFireStation() throws Exception {
		FireStationDTO.Person person = new FireStationDTO.Person("Tony", "Cooper", "112 Steppes Pl", "841-874-6874");
		String result = mockMvc.perform(get("/firestation?stationNumber=3")).andReturn().getResponse().getContentAsString();
		FireStationDTO resultDTO = JsonIterator.deserialize(result, FireStationDTO.class);
		assertEquals(6, resultDTO.getAdultCount());
		assertEquals(2, resultDTO.getChildCount());
		assertEquals(8, resultDTO.getPeople().size());
		assertTrue(resultDTO.getPeople().contains(person));
	}
	
	@Test
	@Tag("childAlert")
	public void whenOneChildLivingAt_GetChildrenLivingAt() throws Exception {
		List<Person> house = new ArrayList<>();
		Person adult1 = new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com");
		Person adult2 = new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com");
		house.add(adult1);
		house.add(adult2);
		String result = mockMvc.perform(get("/childAlert?address=947 E. Rose Dr")).andReturn().getResponse().getContentAsString();
		ChildAlertDTO[] resultDTO = JsonIterator.deserialize(result, ChildAlertDTO[].class);
		assertEquals(1, resultDTO.length);
		assertEquals(7, resultDTO[0].getAge());
		assertEquals("Kendrik", resultDTO[0].getFirstName());
		assertEquals("Stelzer", resultDTO[0].getLastName());
		assertTrue(resultDTO[0].getPeopleLivingSamePlace().containsAll(house));
	}
	
	@Test
	@Tag("childAlert")
	public void whenMoreThanOneChildLivingAt_GetChildrenLivingAt() throws Exception {
		String result = mockMvc.perform(get("/childAlert?address=1509 Culver St")).andReturn().getResponse().getContentAsString();
		ChildAlertDTO[] resultDTO = JsonIterator.deserialize(result, ChildAlertDTO[].class);
		assertEquals(2, resultDTO.length);
	}

	@Test 
	@Tag("childAlert")
	public void whenNoChildLivingAt_GetChildrenLivingAt() throws Exception {
		String result = mockMvc.perform(get("/childAlert?address=112 Steppes Pl")).andReturn().getResponse().getContentAsString();
		ChildAlertDTO[] resultDTO = JsonIterator.deserialize(result, ChildAlertDTO[].class);
		assertEquals(0, resultDTO.length);
	}
	
	@Test
	@Tag("phoneAlert")
	public void whenOnePhoneNumberForManyPeople_GetPhoneNumberOfPeopleCoveresByFireStation() throws Exception {
		String result = mockMvc.perform(get("/phoneAlert?firestation=1")).andReturn().getResponse().getContentAsString();
		String[] resultList = JsonIterator.deserialize(result, String[].class);
		assertEquals(1, resultList.length);
		assertEquals("841-874-7784", resultList[0]);
	}
	
	@Test
	@Tag("phoneAlert")
	public void whenManyPhoneNumbers_GetPhoneNumberOfPeopleCoveresByFireStation() throws Exception {
		String result = mockMvc.perform(get("/phoneAlert?firestation=3")).andReturn().getResponse().getContentAsString();
		String[] resultList = JsonIterator.deserialize(result, String[].class);
		assertEquals(6, resultList.length);
	}
	
	@Test
	@Tag("fire")
	public void whenValidInput_GetPeopleLivingAtThisAddress() throws Exception {
		List<String> medications1 = new ArrayList<>();
		List<String> allergies1 = new ArrayList<>();
		medications1.add("ibupurin:200mg");
		medications1.add("hydrapermazol:400mg");
		allergies1.add("nillacilan");
		FireDTO.Person person1 = new FireDTO.Person("Brian", "Stelzer", "841-874-7784", 45, medications1, allergies1);
		
		String result = mockMvc.perform(get("/fire?address=947 E. Rose Dr")).andReturn().getResponse().getContentAsString();
		FireDTO resultDTO = JsonIterator.deserialize(result, FireDTO.class);
		
		assertEquals(3, resultDTO.getPersonLivingHere().size());
		assertEquals(1, resultDTO.getStationNumber());
		assertTrue(resultDTO.getPersonLivingHere().contains(person1));
	}
	
	@Test
	@Tag("flood/stations")
	public void whenOneFireStation_GetHomesCoveredByDiversesFireStations() throws Exception {
		List<String> medications1 = new ArrayList<>();
		List<String> allergies1 = new ArrayList<>();
		medications1.add("ibupurin:200mg");
		medications1.add("hydrapermazol:400mg");
		allergies1.add("nillacilan");
		FloodDTOInfo.Person person1 = new FloodDTOInfo.Person("Brian", "Stelzer", "841-874-7784", 45, medications1, allergies1);
		
		List<String> medications2 = new ArrayList<>();
		List<String> allergies2 = new ArrayList<>();
		FloodDTOInfo.Person person2 = new FloodDTOInfo.Person("Shawna", "Stelzer", "841-874-7784", 40, medications2, allergies2);
		
		List<String> medications3 = new ArrayList<>();
		List<String> allergies3 = new ArrayList<>();
		medications3.add("noxidian:100mg");
		medications3.add("pharmacol:2500mg");
		FloodDTOInfo.Person person3 = new FloodDTOInfo.Person("Kendrik", "Stelzer", "841-874-7784", 7, medications3, allergies3);
		
		List<FloodDTOInfo.Person> peopleAtHome = new ArrayList<>();
		peopleAtHome.add(person1);
		peopleAtHome.add(person2);
		peopleAtHome.add(person3);
		FloodDTOInfo floodInfo = new FloodDTOInfo("947 E. Rose Dr", peopleAtHome);
		
		String result = mockMvc.perform(get("/flood/stations?stations=1")).andReturn().getResponse().getContentAsString();
		FloodDTO[] resultDTO = JsonIterator.deserialize(result, FloodDTO[].class);
		assertEquals(1, resultDTO.length);
		assertEquals(1, resultDTO[0].getStationNumber());
		assertTrue(resultDTO[0].getInfoFloodList().contains(floodInfo));
	}
	
	@Test
	@Tag("flood/stations")
	public void whenManyFireStations_GetHomesCoveredByDiversesFireStations() throws Exception {
		String result = mockMvc.perform(get("/flood/stations?stations=1,2")).andReturn().getResponse().getContentAsString();
		FloodDTO[] resultDTO = JsonIterator.deserialize(result, FloodDTO[].class);
		assertEquals(2, resultDTO.length);
	}
	
	
	@Test
	@Tag("personInfo")
	public void whenValidInput_GetInfo() throws Exception {
		List<String> medications1 = new ArrayList<>();
		List<String> allergies1 = new ArrayList<>();
		medications1.add("ibupurin:200mg");
		medications1.add("hydrapermazol:400mg");
		allergies1.add("nillacilan");
		String result = mockMvc.perform(get("/personInfo?firstName=Brian&lastName=Stelzer")).andReturn().getResponse().getContentAsString();
		PersonInfoDTO resultDTO = JsonIterator.deserialize(result, PersonInfoDTO.class);
		assertEquals(45, resultDTO.getAge());
		assertEquals(medications1, resultDTO.getMedicaments());
		assertEquals(allergies1, resultDTO.getAllergies());
		assertEquals("bstel@email.com", resultDTO.getMail());
		assertEquals("947 E. Rose Dr", resultDTO.getAddress());
	}
	
	@Test
	@Tag("communityEmail")
	public void whenValidInput_GetEmailOfPersonInCity() throws Exception {
		String result = mockMvc.perform(get("/communityEmail?city=Culver")).andReturn().getResponse().getContentAsString();
		String[] resultDTO = JsonIterator.deserialize(result, String[].class);
		assertEquals(14, resultDTO.length);
	}
	
	
	
}
