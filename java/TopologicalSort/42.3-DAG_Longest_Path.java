import java.util.*;
record Edge(int node, int weight) {
}

class TopologicalSort {
  public List<Integer> solve(List<List<Edge>> graph) {
    int V = graph.size();
    int[] inDegrees = new int[V];
    for (int node = 0; node < V; node++) {
      for (Edge edge : graph.get(node)) {
        inDegrees[edge.node()]++;
      }
    }
    List<Integer> degreeZero = new ArrayList<>();

    for (int node = 0; node < V; node++) {
      if (inDegrees[node] == 0) {
        degreeZero.add(node);
      }
    }

    List<Integer> topoOrder = new ArrayList<>();
    while (!degreeZero.isEmpty()) {
      int node = degreeZero.remove(degreeZero.size() - 1);
      topoOrder.add(node);
      for (Edge edge : graph.get(node)) {
        inDegrees[edge.node()]--;
        if (inDegrees[edge.node()] == 0) {
          degreeZero.add(edge.node());
        }
      }

    }

    if (topoOrder.size() < V) {
      return new ArrayList<>();
    }
    return topoOrder;
  }
}

class LongestPath {
  public int[] solve(List<List<Edge>> graph, int start) {
    TopologicalSort topoSort = new TopologicalSort();
    List<Integer> topoOrder = topoSort.solve(graph);

    int[] lengths = new int[graph.size()];
    Arrays.fill(lengths, Integer.MIN_VALUE);
    lengths[start] = 0;
    for (int node : topoOrder) {
      if (lengths[node] == Integer.MIN_VALUE)
        continue;
      for (Edge edge : graph.get(node)) {
        if (lengths[node] + edge.weight() > lengths[edge.node()]) {
          lengths[edge.node()] = lengths[node] + edge.weight();
        }
      }
    }

    return lengths;
  }
}
