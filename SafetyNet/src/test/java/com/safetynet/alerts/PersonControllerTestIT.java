package com.safetynet.alerts;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.service.PersonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTestIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	private PersonService personService;
	
	//@Test

}
