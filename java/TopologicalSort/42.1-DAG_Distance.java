import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

record Edge(int node, int weight) {
}

class TopologicalSort{
    public List<Integer> solve(List<List<Edge>> graph){
      int v = graph.size();
      int[] indegrees = new int[v];
  
      for(int node = 0; node < v ; node++){
          for(Edge edge:graph.get(node)){
  	          indegrees[edge.node()]++;
          }
      }
  
      List<Integer> degreezero = new ArrayList<>();
      
      for(int node =0; node < v; node++){
          if(indegrees[node] == 0){
        	  degreezero.add(node);
          }
      }
  
      List<Integer> topologicalorder = new ArrayList<>();
  
      while (!degreezero.isEmpty()) {
          int node = degreezero.remove(degreezero.size() - 1);
          topologicalorder.add(node);
          for (Edge edge : graph.get(node)) {
            indegrees[edge.node()]--;
            if (indegrees[edge.node()] == 0) {
                degreezero.add(edge.node());
            }
        }
      }
  
      if(topologicalorder.size()<v){
          return new ArrayList<>();
      }
  
      return topologicalorder;
    }
}

class Distance{
    public double[] solve(List<List<Edge>>graph, int start){
        List<Integer> topologicalOrder = new TopologicalSort().solve(graph);
        double[] distances = new double[graph.size()];
        Arrays.fill(distances,Double.POSITIVE_INFINITY);
        distances[start] = 0;
        for(int node: topologicalOrder){
            if (distances[node] == Double.POSITIVE_INFINITY){
                continue;
            }
            for(Edge edge: graph.get(node)){
                if(distances[node]+edge.weight() < distances[edge.node()]){
                    distances[edge.node()] = distances[node] + edge.weight();
                }
            }
        }
        return distances;
    }
}
