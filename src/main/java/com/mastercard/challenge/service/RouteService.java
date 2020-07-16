package com.mastercard.challenge.service;

import org.springframework.stereotype.Service;

import com.mastercard.challenge.entity.CityData;
import com.mastercard.challenge.util.Graph;

@Service
public class RouteService {
	public String checkConnected(String origin, String destination) {

		Graph graph = CityData.getGraph();
		int originIndex = CityData.getIndex(origin);
		int destinationIndex = CityData.getIndex(destination);
		
		if (originIndex < 0 || destinationIndex < 0)
			return "no";

		boolean connected = graph.isConnected(originIndex, destinationIndex);

		return connected ? "yes" : "no";
	}
}
