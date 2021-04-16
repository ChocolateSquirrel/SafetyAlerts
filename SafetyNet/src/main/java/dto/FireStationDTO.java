package dto;

import java.util.List;

import com.safetynet.alerts.model.Person;

import lombok.Data;

@Data
public class FireStationDTO {
	private List<Person> people;
	private Integer adultCount;
	private Integer childCount;
	
	public FireStationDTO(List<Person> people, Integer adultCount, Integer childCount) {
		super();
		this.people = people;
		this.adultCount = adultCount;
		this.childCount = childCount;
	}

	

}
