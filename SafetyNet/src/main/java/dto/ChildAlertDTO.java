package dto;

import java.util.List;

import com.safetynet.alerts.model.Person;

import lombok.Data;

@Data
public class ChildAlertDTO {
	private String firstName;
	private String lastName;
	private String age;
	private List<Person> peopleLivingSamePlace;
	
	public ChildAlertDTO(String firstName, String lastName, String age, List<Person> peopleLivingSamePlace) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.peopleLivingSamePlace = peopleLivingSamePlace;
	}
	
	
	

}
