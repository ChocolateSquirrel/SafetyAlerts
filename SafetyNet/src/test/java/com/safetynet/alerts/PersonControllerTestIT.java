package com.safetynet.alerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.Person;



@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class PersonControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void whenValidInput_Post_thenReturns200() throws Exception {
		Person p = new Person("Jean", "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
				"jean.dupont@mail.com");

		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(p))	
				)
		.andExpect(status().isOk());
		Optional<Person> person = personRepository.findByIdentity("Jean", "Dupont");
		assertThat(person).isPresent();
	}
	
	@Test
	public void whenNullFirstOrLastNameInput_Post_thenReturns400() throws Exception {
		Person p = new Person(null, "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
				"jean.dupont@mail.com");

		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(p))
				)
		.andExpect(status().isBadRequest());
	}
	


}
