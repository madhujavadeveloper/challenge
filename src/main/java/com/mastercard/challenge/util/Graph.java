package com.mastercard.challenge.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {
	public static Map<String, Integer> cityIndexMap = new HashMap<>();

	int vertexCount;
	List<List<Integer>> adjacencyMatrix;

	public Graph(int V) {
		this.vertexCount = V;
		adjacencyMatrix = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjacencyMatrix.add(new ArrayList<>());
		}
	}

	public void addEdge(int v, int w) {
		adjacencyMatrix.get(v).add(w);
		adjacencyMatrix.get(w).add(v);
	}

	/**
	 * check if an edge exists between source and destination vertices.
	 * 
	 * @param source
	 * @param destination
	 * @return true or false
	 */

	public boolean isConnected(int source, int destination) {
		vertexCount = adjacencyMatrix.size();

		if (source == destination)
			return true;

		boolean[] visited = new boolean[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			visited[i] = false;

		Queue<Integer> queue = new LinkedList<>();

		visited[source] = true;
		queue.add(source);

		while (!queue.isEmpty()) {
			source = queue.peek();
			queue.poll();

			for (int i = 0; i < adjacencyMatrix.get(source).size(); ++i) {
				if (adjacencyMatrix.get(source).get(i) == destination)
					return true;
				if (!visited[adjacencyMatrix.get(source).get(i)]) {
					visited[adjacencyMatrix.get(source).get(i)] = true;
					queue.add(adjacencyMatrix.get(source).get(i));
				}
			}
		}
		return false;
	}
}
