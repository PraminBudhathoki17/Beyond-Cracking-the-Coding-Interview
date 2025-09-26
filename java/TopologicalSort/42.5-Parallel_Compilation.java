class TopologicalSort{
    public List<Integer> solve(List<List<Integer>> graph){
        int v = graph.size();
        int[] indegrees = new int[v];
        for(int node= 0:node< v;node++){
            for(int nbr:graph.get(node)){
                indegrees[nbr]++;
            }
        }

        List<Integer>  degreeZero = new ArrayList<>();
        for(int node =0; node < v; node++){
            if(indegrees[node]==0){
                degreeZero.add(node);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();

        while(!degreeZero.isEmpty()){
            int node = degreezero.remove(degreeZero.size()-1);
            topologicalOrder.add(node);
            for(int nbr: graph.get(node)){
                indegrees[nbr]--;
                if(indegrees[nbr] == 0){
                    degreeZero.add(node);
                }
            }
        }
        if(topologicalOrder.size()<v){
            return new ArrayList<>();
        }
        return topologicalOrder;
    }
}

class CompileTime{
    public int solve (int[] seconds, List<List<Integer>> imports){
        int v = seconds.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int pkg = 0; pkg < V; pkg++) {
            for (int importedPkg : imports.get(pkg)) {
                graph.get(importedPkg).add(pkg);
            }
        }

        List<Integer> topoOrder = new TopologicalSort().solve(graph); // Recipe 1.
        Map<Integer, Integer> durations = new HashMap<>();
        for (int node : topoOrder) {
            if (!durations.containsKey(node)) {
                durations.put(node, seconds[node]);
            }
            for (int nbr : graph.get(node)) {
                if (!durations.containsKey(nbr)) {
                    durations.put(nbr, 0);
                }
             durations.put(nbr, Math.max(
                durations.get(nbr),
                seconds[nbr] + durations.get(node)));
            }
        }
        return Collections.max(durations.values());
      }
    }

