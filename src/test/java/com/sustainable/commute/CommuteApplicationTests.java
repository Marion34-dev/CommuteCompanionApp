package com.sustainable.commute;

import com.sustainable.commute.controllers.CustomErrorController;
import com.sustainable.commute.services.ArrivalInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomErrorController.class)
public class CommuteApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Testing service ArrivalInfo
	@Test
	public void testTimeToStationDisplay() {
		ArrivalInfo arrivalInfo = new ArrivalInfo();

		// Test case when timeToStationMinutes is 0
		arrivalInfo.setTimeToStationMinutes(0);
		assertEquals("arrived", arrivalInfo.getTimeToStationDisplay());

		// Test case when timeToStationMinutes is greater than 0
		arrivalInfo.setTimeToStationMinutes(5);
		assertEquals("5", arrivalInfo.getTimeToStationDisplay());
	}

	// Testing error controller
	@Test
	public void testHandleError() throws Exception {
		mockMvc.perform(get("/error"))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}
}
