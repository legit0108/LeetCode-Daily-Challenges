// Solution-1: Brute force DFS or BFS
// Answer each query in O(nodes) time

// DFS

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Pair>> graph = createGraph(equations, values);
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<queries.size(); index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            String dest = query.get(1);
            
            ans[index] = evaluate(src, dest, graph, new HashSet<String>());
        }
        
        return ans;
    }
    
    private double evaluate(String node, String dest, HashMap<String, List<Pair>> graph, HashSet<String> visited){
        if(!graph.containsKey(node) || visited.contains(node)) return -1.0;
        if(node.equals(dest)) return 1.0;
        
        visited.add(node);
        
        double ans = -1.0;
        List<Pair> neighbors = graph.get(node);
        
        for(Pair pair: neighbors){
            String neighbor = pair.node;
            double weight = pair.weight;
            
            double currAns = evaluate(neighbor, dest, graph, visited);
            
            if(currAns!=-1){
                ans = currAns*weight;
                break;
            }
        }
        
        return ans;
    }
    
    private HashMap<String, List<Pair>> createGraph(List<List<String>> equations, double[] values){
        HashMap<String, List<Pair>> graph = new HashMap();
        
        for(int index=0; index<equations.size(); index++){
            List<String> equation = equations.get(index);
            double value = values[index];
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            if(var1.equals(var2)) continue;
            
            if(!graph.containsKey(var1)) graph.put(var1, new ArrayList());
            graph.get(var1).add(new Pair(var2, value));
            
            if(!graph.containsKey(var2)) graph.put(var2, new ArrayList());
            graph.get(var2).add(new Pair(var1, 1.0/value));
        }
        
        return graph;
    }
    
    private class Pair{
        String node;
        double weight;
        
        Pair(){}
        
        Pair(String node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }
}

// BFS

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Pair>> graph = createGraph(equations, values);
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<queries.size(); index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            String dest = query.get(1);
            
            ans[index] = evaluate(src, dest, graph, new HashSet<String>());
        }
        
        return ans;
    }
    
    private double evaluate(String node, String dest, HashMap<String, List<Pair>> graph, HashSet<String> visited){
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(node, 1.0));
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            node = pair.node;
            double value = pair.weight;
            
            if(!graph.containsKey(node) || visited.contains(node)) continue;
            if(node.equals(dest)) return value;
            
            visited.add(node);
            
            List<Pair> neighbors = graph.get(node);
            for(int index=0; index<neighbors.size(); index++){
                pair = neighbors.get(index);
                String neighbor = pair.node;
                double weight = pair.weight;
                
                if(!visited.contains(neighbor)) queue.add(new Pair(neighbor, value*weight));
            }
        }
        
        return -1.0;
    }
    
    private HashMap<String, List<Pair>> createGraph(List<List<String>> equations, double[] values){
        HashMap<String, List<Pair>> graph = new HashMap();
        
        for(int index=0; index<equations.size(); index++){
            List<String> equation = equations.get(index);
            double value = values[index];
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            if(var1.equals(var2)) continue;
            
            if(!graph.containsKey(var1)) graph.put(var1, new ArrayList());
            graph.get(var1).add(new Pair(var2, value));
            
            if(!graph.containsKey(var2)) graph.put(var2, new ArrayList());
            graph.get(var2).add(new Pair(var1, 1.0/value));
        }
        
        return graph;
    }
    
    private class Pair{
        String node;
        double weight;
        
        Pair(){}
        
        Pair(String node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Optimized BFS/DFS 
// The idea is to express every variable in some fraction of the 'root' of its group
// we then compute ratios via division of fractions of nodes belonging to the same group
// Each query is answered in constant time

// DFS

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Pair>> graph = createGraph(equations, values);
        HashMap<String, Double> valMap = new HashMap();
        HashMap<String, String> rootMap = new HashMap();
        HashSet<String> visited = new HashSet();
        
        for(String node: graph.keySet()){
            if(!visited.contains(node)) dfs(node, node, 1, valMap, rootMap, graph, visited); 
        }
        
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<queries.size(); index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            String dest = query.get(1);
            
            if(!graph.containsKey(src) || !graph.containsKey(dest) || !rootMap.get(src).equals(rootMap.get(dest))) ans[index] = -1;
            else ans[index] = valMap.get(src)/valMap.get(dest);
        }
        
        return ans;
    }
    
    private void dfs(String node, String root, double value, HashMap<String, Double> valMap, HashMap<String, String> rootMap, HashMap<String, List<Pair>> graph, HashSet<String> visited){
        valMap.put(node, value);
        rootMap.put(node, root);
        visited.add(node);
        
        List<Pair> neighbors = graph.get(node); 
        
        for(Pair pair: neighbors){
            node = pair.node;
            double weight = pair.weight;
            
            if(!visited.contains(node)) dfs(node, root, value/weight, valMap, rootMap, graph, visited);
        }
    }
    
    private HashMap<String, List<Pair>> createGraph(List<List<String>> equations, double[] values){
        HashMap<String, List<Pair>> graph = new HashMap();
        
        for(int index=0; index<equations.size(); index++){
            List<String> equation = equations.get(index);
            double value = values[index];
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            if(var1.equals(var2)) continue;
            
            if(!graph.containsKey(var1)) graph.put(var1, new ArrayList());
            graph.get(var1).add(new Pair(var2, value));
            
            if(!graph.containsKey(var2)) graph.put(var2, new ArrayList());
            graph.get(var2).add(new Pair(var1, 1.0/value));
        }
        
        return graph;
    }
    
    private class Pair{
        String node;
        double weight;
        
        Pair(){}
        
        Pair(String node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }
}

// BFS

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Pair>> graph = createGraph(equations, values);
        HashMap<String, Double> valMap = new HashMap();
        HashMap<String, String> rootMap = new HashMap();
        HashSet<String> visited = new HashSet();
        
        for(String node: graph.keySet()){
            if(!visited.contains(node)) bfs(node, valMap, rootMap, graph, visited); 
        }
        
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<queries.size(); index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            String dest = query.get(1);
            
            if(!graph.containsKey(src) || !graph.containsKey(dest) || !rootMap.get(src).equals(rootMap.get(dest))) ans[index] = -1;
            else ans[index] = valMap.get(src)/valMap.get(dest);
        }
        
        return ans;
    }
    
    private void bfs(String node, HashMap<String, Double> valMap, HashMap<String, String> rootMap, HashMap<String, List<Pair>> graph, HashSet<String> visited){
        String root = node;
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(node, 1.0));
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            node = pair.node;
            double value = pair.weight;
            
            if(visited.contains(node)) continue;
            
            visited.add(node);
            valMap.put(node, value);
            rootMap.put(node, root);
            
            List<Pair> neighbors = graph.get(node);
            for(int index=0; index<neighbors.size(); index++){
                pair = neighbors.get(index);
                String neighbor = pair.node;
                double weight = pair.weight;
                
                if(!visited.contains(neighbor)) queue.add(new Pair(neighbor, value/weight));
            }
        }
    }
    
    private HashMap<String, List<Pair>> createGraph(List<List<String>> equations, double[] values){
        HashMap<String, List<Pair>> graph = new HashMap();
        
        for(int index=0; index<equations.size(); index++){
            List<String> equation = equations.get(index);
            double value = values[index];
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            if(var1.equals(var2)) continue;
            
            if(!graph.containsKey(var1)) graph.put(var1, new ArrayList());
            graph.get(var1).add(new Pair(var2, value));
            
            if(!graph.containsKey(var2)) graph.put(var2, new ArrayList());
            graph.get(var2).add(new Pair(var1, 1.0/value));
        }
        
        return graph;
    }
    
    private class Pair{
        String node;
        double weight;
        
        Pair(){}
        
        Pair(String node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union-Find
// Each query is answered in amortized constant time

// The idea is to express every variable as a fraction of root of group
// We can then compute ratios between nodes belonging to the same group (division, just like optimized dfs/bfs)

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UnionFind dsu = new UnionFind(equations);
        
        for(int index=0; index<equations.size(); index++){
            List<String> equation = equations.get(index);
            dsu.union(equation.get(0), equation.get(1), values[index]);
        }
        
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<queries.size(); index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            String dest = query.get(1);
            
            Pair srcPair = dsu.find(src);
            Pair destPair = dsu.find(dest);
            
            if((srcPair==null || destPair==null) || !(srcPair.node.equals(destPair.node))) ans[index] = -1;
            else ans[index] = srcPair.value/destPair.value;
        }
        
        return ans;
    }
    
    private class UnionFind{
        HashMap<String, Pair> parent;
        HashMap<String, Integer> rank;
        
        UnionFind(List<List<String>> equations){
            parent = new HashMap();
            rank = new HashMap();
            
            for(List<String> equation: equations){
                String var1 = equation.get(0);
                String var2 = equation.get(1);
                
                parent.put(var1, new Pair(var1, 1));
                rank.put(var1, 1);
                
                parent.put(var2, new Pair(var2, 1));
                rank.put(var2, 1);
            }
        }
        
        void union(String node1, String node2, double weight){
            Pair pair1 = find(node1);
            Pair pair2 = find(node2);
            
            String root1 = pair1.node;
            String root2 = pair2.node;
            
            if(root1.equals(root2)) return;
            
            int rank1 = rank.get(root1);
            int rank2 = rank.get(root2);
            
            if(rank1<rank2){
                parent.put(root1, new Pair(root2, (1.0/pair1.value)*weight*pair2.value)); // we make root2 a parent of root1 through current edge 
            }else if(rank2<rank1){
                parent.put(root2, new Pair(root1, (1.0/pair2.value)*(1.0/weight)*pair1.value)); // we make root1 a parent of root2 through current edge 
            }else{
                parent.put(root1, new Pair(root2, (1.0/pair1.value)*weight*pair2.value));
                rank.put(root2, rank.get(root2)+1);
            }
        }
        
        // iterative find implementation
        // not only do we find root, 
        // we also set all the fractions with reference to root for all variables
        // from node to root path
        Pair find(String node){
            String initNode = node;
            double value = 1.0;
            
            while(parent.get(node)!=null && !parent.get(node).node.equals(node)){
                value = value*parent.get(node).value;
                node = parent.get(node).node;
            }
            
            if(parent.get(node)==null) return null;
            
            String root = node;
            Pair rootPair = new Pair(root, value);
            node = initNode;
            
            while(!node.equals(root)){
                String par = parent.get(node).node;
                parent.put(node, rootPair);
                node = par;
            }
            
            return rootPair;
        }
    }
    
    private class Pair{
        String node;
        double value;
        
        Pair(){}
        
        Pair(String node, double value){
            this.node = node;
            this.value = value;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Bonus solution: Floyd Warshall
// The cost of a path is the product of cost of all edges along the path instead of sum

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> idMap = new HashMap();
        int id = 0;
        int size = equations.size();
        
        for(int index=0; index<size; index++){
            List<String> equation = equations.get(index);
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            if(!idMap.containsKey(var1)) idMap.put(var1, id++);
            if(!idMap.containsKey(var2)) idMap.put(var2, id++);
        }
        
        int nodes = id;
        Double[][] dp = new Double[nodes][nodes];
        
        for(int index=0; index<size; index++){
            List<String> equation = equations.get(index);
            
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            
            Integer id1 = idMap.get(var1);
            Integer id2 = idMap.get(var2);
            double weight = values[index];
            
            if(id1!=null) dp[id1][id1] = 1.0;
            if(id1!=null && id2!=null){
                dp[id1][id2] = weight;
                dp[id2][id1] = 1.0/weight;
            }
            if(id2!=null) dp[id2][id2] = 1.0;
        }
        
        for(int via=0; via<nodes; via++){
            for(int src=0; src<nodes; src++){
                if(dp[src][via]!=null){
                    for(int dest=0; dest<nodes; dest++){
                        if(dp[via][dest]!=null){
                            double currCost = dp[src][via]*dp[via][dest];
                            Double costSoFar = dp[src][dest];
                            
                            if(costSoFar==null) dp[src][dest] = currCost;
                            else dp[src][dest] = Math.min(costSoFar, currCost);
                        }
                    }
                }
            }
        }
        
        int len = queries.size();
        double[] ans = new double[len];
        
        for(int index=0; index<len; index++){
            List<String> query = queries.get(index);
            String src = query.get(0);
            Integer srcId = idMap.get(src);
            
            String dest = query.get(1);
            Integer destId = idMap.get(dest);
            
            if(srcId==null || destId==null || dp[srcId][destId]==null) ans[index] = -1;
            else ans[index] = dp[srcId][destId];
        }
        
        return ans;
    }
}