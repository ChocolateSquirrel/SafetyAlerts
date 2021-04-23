package dto;

import java.util.List;

import lombok.Data;

@Data
public class FloodDTO {
	private String address;
	private List<Person> peopleAtHome;

	public FloodDTO(String address, List<Person> peopleAtHome) {
		super();
		this.address = address;
		this.peopleAtHome = peopleAtHome;
	}

	@Data
	public static class Person {
		private String firstName;
		private String lastName;
		private String phone;
		private int age;
		private List<String> medicaments;
		private List<String> allergies;

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
