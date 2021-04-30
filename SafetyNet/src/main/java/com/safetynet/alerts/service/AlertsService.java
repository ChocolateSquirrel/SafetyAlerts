package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import dto.ChildAlertDTO;
import dto.FireDTO;
import dto.FireStationDTO;
import dto.FloodDTO;
import dto.FloodDTOInfo;
import dto.PersonInfoDTO;

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
		return children.stream()
				.map(c -> new ChildAlertDTO(c.getFirstName(), c.getLastName(),
					c.getAge(getBirthdate(c)), getListOfPeopleLivingAtTheSameAddress(c)))
				.collect(Collectors.toList());
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

	public FireDTO getPeopleLivingAtThisAddress(String address) {
		int stationNumber = getFireStationNumberForThisAddress(address);
		return new FireDTO(getHomesForThisAddress(address), stationNumber);
	}
	
	public List<FloodDTO> getHomesCoveredByDiversesFireStations(List<String> fireStationList){
		return fireStationList.stream().map(fs -> 
			new FloodDTO(Integer.parseInt(fs), getHomesCoveredByAFireStation(Integer.parseInt(fs)))
		).collect(Collectors.toList());
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
		 return personList.stream().map(p -> p.getEmail()).collect(Collectors.toList());
	}
	
	private List<FireDTO.Person> getHomesForThisAddress(String address){
		List<Person> people = getListOfPeopleLivingAt(address);
		return people.stream().map(p -> changeIntoFireDTOPerson(p)).collect(Collectors.toList());			
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
	
	private FloodDTOInfo.Person changeIntoFloodDTOPerson(Person person){
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByIdentity(person.getFirstName(), person.getLastName());
		FloodDTOInfo.Person personFloodDTO = new FloodDTOInfo.Person(person.getFirstName(), person.getLastName(),
				person.getPhone(), person.getAge(medicalRecord.get().getBirthdate()),
				medicalRecord.get().getMedications(),
				medicalRecord.get().getAllergies());
		return personFloodDTO;
	}
	
	private FireDTO.Person changeIntoFireDTOPerson(Person person){
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByIdentity(person.getFirstName(), person.getLastName());
		FireDTO.Person personFireDTO = new FireDTO.Person(person.getFirstName(),
				person.getLastName(),
				person.getPhone(),
				person.getAge(medicalRecord.get().getBirthdate()),
				medicalRecord.get().getMedications(),
				medicalRecord.get().getAllergies());
		return personFireDTO;
	}

	// -------------------------- Methods about FiresStation -----------------------//
	private List<FireStation> getFireStationsByNumber(int stationNumber) {
		return fireStationRepository.findByStationNumber(stationNumber);
	}
	
	private int getFireStationNumberForThisAddress(String address) {
		return fireStationRepository.findByAddress(address);
	}

	private List<String> getAddressesCoveredByAFireStation(int stationNumber) {
		return getFireStationsByNumber(stationNumber).stream().map(fs -> fs.getAddress()).collect(Collectors.toList());
	}
	
	private List<FloodDTOInfo> getHomesCoveredByAFireStation(int station_number) {
		List<FloodDTOInfo> floodInfoList = new ArrayList<>();
		for (String address : getAddressesCoveredByAFireStation(station_number)) {
			List<FloodDTOInfo.Person> personFloodDtoList = getListOfPeopleLivingAt(address).stream()
					.map(p -> changeIntoFloodDTOPerson(p)).collect(Collectors.toList());
			FloodDTOInfo floodDTO = new FloodDTOInfo(address, personFloodDtoList);
			floodInfoList.add(floodDTO);
		}
		return floodInfoList;
	}
}
