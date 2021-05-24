package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.model.MedicalRecord;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class MedicalRecordControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	private static List<String> medications;
	private static List<String> allergies;
	
	@BeforeEach
	private void setUpPertest() {
		medications = new ArrayList();
		allergies = new ArrayList();
	}
	
	@Test
	public void whenValidInput_Get_thenReturns200() throws Exception {
		mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
	}
	
	@Test
	public void whenValidInput_Post_thenReturns200() throws Exception {
		allergies.add("mosquito");
		MedicalRecord mr = new MedicalRecord("Jean", "Dupond", "01/01/01", medications, allergies);
		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(mr))).andExpect(status().isOk());
		MedicalRecord medicalRecord = medicalRecordRepository.findByIdentity("Jean", "Dupond");
		assertEquals(mr, medicalRecord);
	}
	
	@Test
	public void whenValidInput_Put_thenReturns200() throws Exception {
		allergies.add("mosquito");
		MedicalRecord mr = new MedicalRecord("Tenley", "Boyd", "12/12/12", medications, allergies);
		mockMvc.perform(put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(mr))).andExpect(status().isOk());
		MedicalRecord medicalRecord = medicalRecordRepository.findByIdentity("Tenley", "Boyd");
		assertEquals("12/12/12", medicalRecord.getBirthdate());
		assertTrue(medicalRecord.getAllergies().contains("mosquito"));
	}
	
	@Test
	public void whenInvalidInput_Put_thenReturnsNotFound() throws Exception {
		MedicalRecord mr = new MedicalRecord("Suzanne", "Boyle", "01/01/01", medications, allergies);
		mockMvc.perform(put("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(mr)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void whenValidInput_Delete_thenReturns200() throws Exception {
		MedicalRecord mr = new MedicalRecord("Lily", "Cooper", "03/06/1994", medications, allergies);
		mockMvc.perform(delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(mr))).andExpect(status().isOk());
		boolean answer = medicalRecordRepository.findAll().contains(mr);
		assertEquals(false, answer);
	}
	
	@Test
	public void whenInvalidInput_Delete_thenReturnsNotFound() throws Exception {
		MedicalRecord mr = new MedicalRecord("Suzanne", "Boyle", "01/01/01", medications, allergies);
		mockMvc.perform(delete("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonStream.serialize(mr)))
				.andExpect(status().isNotFound());
	}
	

}
