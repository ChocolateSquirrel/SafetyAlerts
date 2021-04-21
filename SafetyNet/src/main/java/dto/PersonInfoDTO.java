package dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonInfoDTO {
	private String firstName;
	private String lastName;
	private String mail;
	private List<String> medicaments;
	private List<String> allergies;
	
	public PersonInfoDTO(String firstName, String lastName, String mail, List<String> medicaments,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.medicaments = medicaments;
		this.allergies = allergies;
	}
	
	

}
