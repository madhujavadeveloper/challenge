package com.mastercard.challenge.controller;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mastercard.challenge.service.RouteService;

@WebMvcTest(RouteController.class)
public class RouteControllerMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RouteService service;

	@Test
	public void connected_cities_return_yes() throws Exception {
		when(service.checkConnected("Boston", "Philadelphia")).thenReturn("yes");
		this.mockMvc.perform(get("/connected?origin=Boston&destination=Philadelphia")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("yes")));
	}
	

	@Test
	public void not_connected_cities_return_no() throws Exception {
		when(service.checkConnected("Boston", "Albany")).thenReturn("no");
		this.mockMvc.perform(get("/connected?origin=Boston&destination=Albany")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("no")));
	}
}
