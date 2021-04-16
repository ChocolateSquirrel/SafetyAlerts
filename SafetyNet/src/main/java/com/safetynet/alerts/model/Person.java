package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
public class Person {
	
	private static final String BIRTHDATE_PATTERN = "MM/dd/yyyy";
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;

	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	public Person() {
		super();
	}
	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}
	
	public int getAge(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BIRTHDATE_PATTERN);
		LocalDate birth = LocalDate.parse(birthdate, formatter);
		LocalDate now = LocalDate.now();
		return Period.between(birth, now).getYears();
	}

	

}
