package dto;

import java.util.List;

import lombok.Data;

@Data
public class PhoneAlertDTO {
	private List<String> phoneNumbers;

	public PhoneAlertDTO(List<String> phoneNumbers) {
		super();
		this.phoneNumbers = phoneNumbers;
	}

}
