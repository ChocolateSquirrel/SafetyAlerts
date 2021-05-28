package com.safetynet.alerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonInfoDTO {
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String mail;
	private List<String> medicaments;
	private List<String> allergies;
	
	public PersonInfoDTO() {}
	
	public PersonInfoDTO(String firstName, String lastName, String address, int age, String mail, List<String> medicaments,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.mail = mail;
		this.medicaments = medicaments;
		this.allergies = allergies;
	}
	
	
	

}
