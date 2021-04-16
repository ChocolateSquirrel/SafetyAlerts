package com.safetynet.alerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonService personService;

//	@Test
//	public void whenValidInput_thenReturns200() throws Exception {
//		Person p = new Person("Jean", "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
//				"jean.dupont@mail.com");
//
//		mockMvc.perform(post("/person")
//				.contentType("classpath:data.json"))
//		.andExpect(status().isOk());
//	}
	
//	@Test
//	public void whenNullFirstOrLastNameInput_thenReturns400() throws Exception {
//		Person p = new Person(null, "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
//				"jean.dupont@mail.com");
//
//		mockMvc.perform(post("/person")
//				.contentType("classpath:data.json"))
//		.andExpect(status().isBadRequest());
//	}
	
//	@Test 
//	public void whenValidInput_addPerson_thenReturnsPerson() throws Exception {
//		Person p = new Person("Jean", "Dupont", "rue des Fleurs", "Londres", "123456", "06070809",
//				"jean.dupont@mail.com");
//
//		mockMvc.perform(post("person")
//				.contentType("classpath:data.json")
//				.param("ps", p)
//				.content(JsonStream.serialize(objectMapper.writeValueAsString(p))))
//		.andExpect(status().isOk());
//	}
}
