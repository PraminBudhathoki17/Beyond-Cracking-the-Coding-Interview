import java.util.*;

record Edge(int node, int weight) {}

class TopologicalSort {
    public List<Integer> solve(List<List<Edge>> graph) {
        int v = graph.size();
        int[] indegrees = new int[v];

        for (int node = 0; node < v; node++) {
            for (Edge edge : graph.get(node)) {
                indegrees[edge.node()]++;
            }
        }

        List<Integer> degreeZero = new ArrayList<>();
        for (int node = 0; node < v; node++) {
            if (indegrees[node] == 0) {
                degreeZero.add(node);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();
        while (!degreeZero.isEmpty()) {
            int node = degreeZero.remove(degreeZero.size() - 1);
            topologicalOrder.add(node);
            for (Edge edge : graph.get(node)) {
                indegrees[edge.node()]--;
                if (indegrees[edge.node()] == 0) {
                    degreeZero.add(edge.node());
                }
            }
        }

        if (topologicalOrder.size() < v) {
            return new ArrayList<>(); // Cycle detected
        }

        return topologicalOrder;
    }
}

class ShortestPath {
    public List<Integer> solve(List<List<Edge>> graph, int start, int goal) {
        List<Integer> topologicalOrder = new TopologicalSort().solve(graph);

        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        distances.put(start, 0);

        for (int node : topologicalOrder) {
            if (!distances.containsKey(node)) continue;
            for (Edge edge : graph.get(node)) {
                int neighbor = edge.node();
                int weight = edge.weight();
                int newDist = distances.get(node) + weight;

                if (!distances.containsKey(neighbor) || newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, node);
                }
            }
        }

        if (!distances.containsKey(goal)) {
            return List.of(); // No path found
        }

        List<Integer> path = new ArrayList<>();
        for (int at = goal; at != start; at = predecessors.get(at)) {
            path.add(at);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
