package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.Person;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void whenValidInput_Get_thenReturns200() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}

	@Test
	public void whenValidInput_Post_thenReturns200() throws Exception {
		Person p = new Person("Jean", "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
				"jean.dupont@mail.com");
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(p)))
				.andExpect(status().isOk());
		Person person = personRepository.findByIdentity("Jean", "Dupont");
		assertEquals(p, person);
	}

	@Test
	public void whenNullFirstOrLastNameInput_Post_thenReturns400() throws Exception {
		Person p = new Person(null, "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
				"jean.dupont@mail.com");
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(p)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenValidInput_Put_thenReturns200() throws Exception {
		Person p = new Person("John", "Boyd", "1509 Culver St", "New York", "97451", "841-874-6512",
				"jaboyd@email.com");
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(p)))
				.andExpect(status().isOk());
		Person person = personRepository.findByIdentity("John", "Boyd");
		assertEquals("New York", person.getCity());
	}

	@Test
	public void whenInvalidInput_Put_thenReturnsNotFound() throws Exception {
		Person p = new Person("Jacques", "Dupont", "1509 Culver St", "New York", "97451", "841-874-6512",
				"jaboyd@email.com");
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(p)))
		.andExpect(status().isNotFound());
	}

	@Test
	public void whenValidInput_Delete_thenReturns200() throws Exception {
		Person p = new Person("John", "Boyd", "1509 Culver St", "New York", "97451", "841-874-6512",
				"jaboyd@email.com");
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(p)))
				.andExpect(status().isOk());
		boolean answer = personRepository.findAll().contains(p);
		assertEquals(false, answer);
	}

	@Test
	public void whenInvalidInput_Delete_thenReturns500() throws Exception {
		Person p = new Person("Suzanne", "Dupont", "1509 Culver St", "New York", "97451", "841-874-6512",
				"jaboyd@email.com");
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(p)))
		.andExpect(status().isNotFound());
	}

}
