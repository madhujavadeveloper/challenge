package com.mastercard.challenge.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void connected_endpoint_default() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected", String.class))
				.contains("no");
	}
	
	@Test
	public void connectedCities_return_yes() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=Philadelphia", String.class))
				.contains("yes");
	}
	
	@Test
	public void notConnectedCities_return_no() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=Albany", String.class))
				.contains("no");
	}
	
	@Test
	public void connected_invalid_cities_return_no() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=New Jersey", String.class))
				.contains("no");
	}

}