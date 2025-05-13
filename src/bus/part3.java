package bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class part3 {

	public static int[] dijkstra(List<ArrayList<int[]>> graph, int start, int[] prev) {
		int n = graph.size();
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int u = current[0];
			int d = current[1];

			if (d > dist[u])
				continue;

			for (int[] neighbor : graph.get(u)) {
				int v = neighbor[0];
				int weight = neighbor[1];
				if (dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
					prev[v] = u;
					pq.add(new int[] { v, dist[v] });
				}
			}
		}
		return dist;
	}

	public static List<Integer> reconstructPath(int target, int[] prev) {
		List<Integer> path = new ArrayList<>();
		for (int at = target; at != -1; at = prev[at]) {
			path.add(at);
		}
		Collections.reverse(path);
		return path;
	}
}
