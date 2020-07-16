package com.mastercard.challenge.entity;

import java.util.HashMap;
import java.util.Map;

import com.mastercard.challenge.util.Graph;

public class CityData {
	public static Map<String, Integer> cityIndexMap = new HashMap<>();
	public static Graph graph = null;

	public static Map<String, Integer> getCityIndexMap() {
		return cityIndexMap;
	}

	public static void setCityIndexMap(Map<String, Integer> cityIndexMap) {
		CityData.cityIndexMap = cityIndexMap;
	}

	public static Graph getGraph() {
		return graph;
	}

	public static void setGraph(Graph g) {
		CityData.graph = g;
	}

	public static int getIndex(String city) {
		int result = -1;
		if (cityIndexMap.get(city) != null)
			result = cityIndexMap.get(city);
		return result;	
    }
}
