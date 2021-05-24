package com.safetynet.alerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class FireStationDTO {
	private List<Person> people;
	private Integer adultCount;
	private Integer childCount;
	
	public FireStationDTO() {}
	
	public FireStationDTO(List<Person> people, Integer adultCount, Integer childCount) {
		super();
		this.people = people;
		this.adultCount = adultCount;
		this.childCount = childCount;
	}
	
	@Data
	public static class Person{
		private String firstName;
		private String lastName;
		private String address;
		private String phone;
		
		public Person() {}
		
		public Person(String firstName, String lastName, String address, String phone) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.phone = phone;
		}
		
		
	}
	
	

	

}
