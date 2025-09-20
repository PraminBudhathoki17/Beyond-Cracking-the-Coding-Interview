class TopologicalSort{
    public List<Integer> solve(List<List<Integer>> graph){
        int v = graph.size();
        int[] inDegrees = new int[v];
        for(int node=0;node < v;node++){
            for(int nbr: graph.get(node)){
                indegree[nbr]++;
            }
        }

        int[] degreeZero = new ArrayList<>();
        for(int node=0;node < v;node++ ){
            if(indegree[node]==0){
               degreeZero.add(node);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();
        while(!degreeZero.isEmpty()){
            int node = degreeZero.remove(degreeZero.size()-1);
            topologicalOrder.add(node);
            for(int nbr: graph.get(node)){
                indegree[nbr]--;
                if(indegree[nbr]==0){
                    degreeZero.add(node);
                }
            }
        }
        return topologicalOrder;
    }
}

class PathCount{
    public int[] solve(List<List<Integer>> graph, int start){
        List<Integer> topoOrder = new TopologicalSort().solve(graph);

        int[] counts = new int[graph.size()];
        counts[start] = 1;
        for (int node : topoOrder) {
            for (int nbr : graph.get(node)) {
                counts[nbr] += counts[node];
            }
        }   
         return counts;
    }
}
