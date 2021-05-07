package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.model.FireStation;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SafetyNetApplicationTest.class)
public class FireStationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FireStationRepository fireStationRepository;
	
	@Test
	public void whenValidInput_Get_thenReturns200() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());
	}
	
	@Test
	public void whenValidInput_Post_thenReturns200() throws Exception {
		FireStation fs = new FireStation("Rue des fleurs", "6");
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(fs))).andExpect(status().isOk());
		FireStation fireStation = fireStationRepository.findAFireStation("Rue des fleurs", "6");
		assertEquals(fs, fireStation);
	}
	
	@Test
	public void whenValidInput_Put_thenReturns200() throws Exception {
		FireStation fs = new FireStation("834 Binoc Ave", "4");
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(fs))).andExpect(status().isOk());
		int stationNumber = fireStationRepository.findByAddress("834 Binoc Ave");
		assertEquals(4, stationNumber);
	}
	
	@Test
	public void whenInvalidInput_Put_thenReturns500() {
		FireStation fs = new FireStation("rue du mur", "2");
		Exception ex = assertThrows(NestedServletException.class, () -> mockMvc
				.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(fs))));
		assertEquals("Request processing failed; nested exception is java.lang.RuntimeException: No fireStation for address: " + fs.getAddress(), ex.getMessage());
	}
	
	@Test
	public void whenValidInput_Delete_thenReturns200() throws Exception {
		FireStation fs = new FireStation("112 Steppes Pl","3");
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(fs))).andExpect(status().isOk());
	}
	
	@Test
	public void whenInvalidInput_Delete_thenReturns500() {
		FireStation fs = new FireStation("rue du mur", "2");
		Exception ex = assertThrows(NestedServletException.class, () -> mockMvc
				.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(fs))));
		assertEquals("Request processing failed; nested exception is java.lang.RuntimeException: No fireStation for address: " + fs.getAddress(), ex.getMessage());
	}
	
}