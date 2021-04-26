package com.safetynet.alerts.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonInfoDTO {
	private String firstName;
	private String lastName;
	private int age;
	private String mail;
	private List<String> medicaments;
	private List<String> allergies;
	
	public PersonInfoDTO(String firstName, String lastName, int age, String mail, List<String> medicaments,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mail = mail;
		this.medicaments = medicaments;
		this.allergies = allergies;
	}
	
	
	

}
