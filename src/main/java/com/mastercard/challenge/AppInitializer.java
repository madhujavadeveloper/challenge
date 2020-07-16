package com.mastercard.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.mastercard.challenge.entity.CityData;
import com.mastercard.challenge.util.*;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements ApplicationRunner {

	public static final String fileName = "city.txt";

	/**
	 * Initialize the application.
	 * 
	 * 1) Read contents of city.txt as a stream() which reads line by line. 2)
	 * Create a graph where each city is a vertex and add an edge between city
	 * if they are connected. 3) Build an indexMap which holds city name as
	 * string and vertex index as integer.
	 */

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<String> cityList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(e -> cityList.add(e));
		} catch (IOException e) {
			System.out.println("Cannot find input file.\nException : " + e + "\n");
			System.exit(0);
		}

		Graph g = new Graph(cityList.size() * 2);
		Map<String, Integer> indexMap = CityData.getCityIndexMap();

		cityList.forEach((c) -> {
			int i1, i2;
			String[] list = c.split(",");
			if (indexMap.get(list[0].trim()) != null)
				i1 = indexMap.get(list[0].trim());
			else {
				i1 = indexMap.size();
				indexMap.put(list[0].trim(), i1);
			}
			if (indexMap.get(list[1].trim()) != null)
				i2 = indexMap.get(list[1].trim());
			else {
				i2 = indexMap.size();
				indexMap.put(list[1].trim(), i2);
			}
			g.addEdge(i1, i2);
		});

		CityData.setGraph(g);
	}
}
