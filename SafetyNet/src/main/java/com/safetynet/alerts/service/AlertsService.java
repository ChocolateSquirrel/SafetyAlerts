package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import com.safetynet.alerts.service.dto.ChildAlertDTO;
import com.safetynet.alerts.service.dto.FireStationDTO;
import com.safetynet.alerts.service.dto.FloodDTO;
import com.safetynet.alerts.service.dto.PersonInfoDTO;

@Service
public class AlertsService {
	private static final int MAJORITY = 18;

	private final PersonRepository personRepository;
	private final FireStationRepository fireStationRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	public AlertsService(PersonRepository personRepository, FireStationRepository fireStationRepository,
			MedicalRecordRepository medicalRecordRepository) {
		super();
		this.personRepository = personRepository;
		this.fireStationRepository = fireStationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public FireStationDTO getPeopleCoveredByAFireStation(String stationNumber) {
		List<Person> people = getListOfPeopleCoveredByAFireStation(Integer.parseInt(stationNumber));
		List<Person> adults = getListOfAdults(people);
		List<Person> children = getListOfChildren(people);
		List<FireStationDTO.Person> person1 = people.stream()
				.map(p -> new FireStationDTO.Person(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());
		FireStationDTO fireDto = new FireStationDTO(person1, adults.size(), children.size());
		return fireDto;
	}

	public List<ChildAlertDTO> getChildrenLivingAtAnAddress(String address) {
		List<Person> children = getListOfChildren(personRepository.findByAddress(address));
		List<ChildAlertDTO> childAlertList = new ArrayList<>();
		for (Person child : children) {
			String birthdate = getBirthdate(child);
			ChildAlertDTO childAlert = new ChildAlertDTO(child.getFirstName(), child.getLastName(),
					child.getAge(birthdate), getListOfPeopleLivingAtTheSameAddress(child));
			childAlertList.add(childAlert);
		}
		return childAlertList;
	}

	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(String stationNumber) {
		List<Person> people = getListOfPeopleCoveredByAFireStation(Integer.parseInt(stationNumber));
		List<String> phoneList = new ArrayList<>();
		for (Person person : people) {
			if (phoneList.contains(person.getPhone()))
				continue;
			phoneList.add(person.getPhone());
		}
		return phoneList;
	}
	

	public List<FloodDTO> getHomesCoveredByFireStations(String station_number) {
		List<FloodDTO> floodInfoList = new ArrayList<>();
		for (String address : getAddressesCoveredByAFireStation(Integer.parseInt(station_number))) {
			List<FloodDTO.Person> personFloodDtoList = getListOfPeopleLivingAt(address).stream()
					.map(p -> changeIntoFloodDTOPerson(p)).collect(Collectors.toList());
			FloodDTO floodDTO = new FloodDTO(address, personFloodDtoList);
			floodInfoList.add(floodDTO);
		}
		return floodInfoList;
	}
	
	public PersonInfoDTO getInfoForPerson(String firstName, String lastName) {
		Optional<Person> person = personRepository.findByIdentity(firstName, lastName);
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByIdentity(firstName, lastName);
		PersonInfoDTO personInfo = new PersonInfoDTO(person.get().getFirstName(), person.get().getLastName(), person.get().getAge(medicalRecord.get().getBirthdate()), person.get().getEmail(), medicalRecord.get().getMedications(), medicalRecord.get().getAllergies());
		return personInfo;
	}

	public List<String> getMailPeopleLivingInCity(String city) {
		return getListOfMail(personRepository.findByCity(city));
	}

	// ------------------------- Methods about Person -------------------------------//
	private List<Person> getListOfPeopleCoveredByAFireStation(int stationNumber) {
		List<Person> personsCoverredByFireStation = new ArrayList<>();
		for (String address : getAddressesCoveredByAFireStation(stationNumber)) {
			personsCoverredByFireStation.addAll(personRepository.findByAddress(address));
		}
		return personsCoverredByFireStation;
	}
	
	private List<Person> getListOfPeopleLivingAt(String address){
		return personRepository.findByAddress(address);
	}
	private List<Person> getListOfPeopleLivingAtTheSameAddress(Person person) {
		List<Person> livingAddress = personRepository.findByAddress(person.getAddress());
		livingAddress.remove(person);
		return livingAddress;
	}

	private List<String> getListOfMail(List<Person> personList) {
		List<String> emailList = new ArrayList<>();
		for (Person person : personList) {
			emailList.add(person.getEmail());
		}
		return emailList;
	}


	// ------------------------- Methods about Person and MedicalRecord --------------------//
	private List<Person> getListOfAdults(List<Person> peopleList) {
		List<Person> adultsList = new ArrayList<>();
		for (Person person : peopleList) {
			Optional<MedicalRecord> medicalRecordPerson = medicalRecordRepository.findByIdentity(person.getFirstName(),
					person.getLastName());
			int personAge = person.getAge(medicalRecordPerson.get().getBirthdate());
			if (personAge >= MAJORITY)
				adultsList.add(person);
		}
		return adultsList;
	}

	private List<Person> getListOfChildren(List<Person> peopleList) {
		List<Person> childrenList = new ArrayList<>();
		for (Person person : peopleList) {
			Optional<MedicalRecord> medicalRecordPerson = medicalRecordRepository.findByIdentity(person.getFirstName(),
					person.getLastName());
			int personAge = person.getAge(medicalRecordPerson.get().getBirthdate());
			if (personAge < MAJORITY)
				childrenList.add(person);
		}
		return childrenList;
	}

	private String getBirthdate(Person person) {
		Optional<MedicalRecord> medicalRecordPerson = medicalRecordRepository.findByIdentity(person.getFirstName(),
				person.getLastName());
		return medicalRecordPerson.get().getBirthdate();
	}
	
	private FloodDTO.Person changeIntoFloodDTOPerson(Person person){
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByIdentity(person.getFirstName(), person.getLastName());
		FloodDTO.Person personFloodDTO = new FloodDTO.Person(person.getFirstName(), person.getLastName(),
				person.getPhone(), person.getAge(medicalRecord.get().getBirthdate()),
				medicalRecord.get().getMedications(),
				medicalRecord.get().getAllergies());
		return personFloodDTO;
	}

	// -------------------------- Methods about FiresStation -----------------------//
	private List<FireStation> getFireStationsByNumber(int stationNumber) {
		return fireStationRepository.findByStationNumber(stationNumber);
	}

	private List<String> getAddressesCoveredByAFireStation(int stationNumber) {
		List<String> addressCoveredByStation = new ArrayList<>();
		for (FireStation fireStation : getFireStationsByNumber(stationNumber)) {
			addressCoveredByStation.add(fireStation.getAddress());
		}
		return addressCoveredByStation;
	}


}
