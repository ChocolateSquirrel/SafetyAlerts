package com.safetynet.alerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class FireDTO {
	private List<Person> personLivingHere;
	private int stationNumber;
	
	public FireDTO() {}
	
	public FireDTO(List<Person> personLivingHere, int stationNumber) {
		super();
		this.personLivingHere = personLivingHere;
		this.stationNumber = stationNumber;
	}

	@Data
	public static class Person{
		private String firstName;
		private String lastName;
		private String phone;
		private int age;
		private List<String> medicaments;
		private List<String> allergies;
		
		public Person() {}
		
		public Person(String firstName, String lastName, String phone, int age, List<String> medicaments,
				List<String> allergies) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.age = age;
			this.medicaments = medicaments;
			this.allergies = allergies;
		}
		
		
	}

}
