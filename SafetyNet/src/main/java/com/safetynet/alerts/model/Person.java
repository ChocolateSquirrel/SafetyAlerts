package com.safetynet.alerts.model;

import lombok.Data;

@Data
public class Person {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;


	public Person(String firstName2, String lastName2, String address2, String city2, String zip2, String phone2,
			String email2) {
	}
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}
	
}
