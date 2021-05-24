package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.dto.FireStationDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class AlertsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Test
	public void whenValidInput_GetPeopleCoveredByAFireStation_thenReturns200() throws Exception {
		String result =mockMvc.perform(get("/firestation?stationNumber=1")).andReturn().getResponse().getContentAsString();
		FireStationDTO resultDTO = JsonIterator.deserialize(result, FireStationDTO.class);
		assertEquals(1, 1);
	}
	
}
