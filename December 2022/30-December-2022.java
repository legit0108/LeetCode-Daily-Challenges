// All the solutions run in exponential time and space since we want to compute all paths from source to destination

// Solution-1: DFS

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> allPaths = new ArrayList();
        
        dfs(0, graph.length-1, graph, new ArrayList<Integer>(), allPaths);
        
        return allPaths;
    }
    
    private void dfs(int node, int dest, int[][] graph, List<Integer> path, List<List<Integer>> allPaths){
        path.add(node);
        
        if(node==dest){
            allPaths.add(new ArrayList(path));
        }else{
            for(int neighbour: graph[node]){
                dfs(neighbour, dest, graph, path, allPaths);
            }
        }
        
        path.remove(path.size()-1);
    }
}

// Solution-2: BFS

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return bfs(graph);
    }
    
    private List<List<Integer>> bfs(int[][] graph){
        int nodes = graph.length;
        int[] indegree = new int[nodes];
        
        for(int node=0; node<nodes; node++){
            for(int neighbor: graph[node]){
                indegree[neighbor]++;
            }
        }
        
        Queue<Integer> queue = new ArrayDeque();
        HashMap<Integer, List<List<Integer>>> allPathsTillNode = new HashMap();
        
        queue.add(0);
        allPathsTillNode.put(0, new ArrayList<List<Integer>>());
        List<Integer> initPath = new ArrayList<Integer>();
        initPath.add(0);
        allPathsTillNode.get(0).add(initPath);
        
        while(queue.size()>0){
            int node = queue.remove();
            
            List<List<Integer>> pathsTillNode = allPathsTillNode.get(node);
            
            for(int neighbor: graph[node]){
                if(!allPathsTillNode.containsKey(neighbor)){
                    allPathsTillNode.put(neighbor, new ArrayList<List<Integer>>());
                }
                
                List<List<Integer>> pathsTillNeighbor = allPathsTillNode.get(neighbor);
                
                for(List<Integer> path: pathsTillNode){
                    path = new ArrayList(path);
                    path.add(neighbor);
                    pathsTillNeighbor.add(path);
                }
                
                indegree[neighbor]--;
                if(indegree[neighbor]==0) queue.add(neighbor);
            }
        }
        
        return allPathsTillNode.get(nodes-1);
    }
}

// Solution-3: Iterative DFS

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return iterativeDfs(graph);
    }
    
    private List<List<Integer>> iterativeDfs(int[][] graph){
        int nodes = graph.length;
        int[] indegree = new int[nodes];
        
        for(int node=0; node<nodes; node++){
            for(int neighbor: graph[node]){
                indegree[neighbor]++;
            }
        }
        
        Stack<Integer> stack = new Stack();
        HashMap<Integer, List<List<Integer>>> allPathsTillNode = new HashMap();
        
        stack.push(0);
        allPathsTillNode.put(0, new ArrayList<List<Integer>>());
        List<Integer> initPath = new ArrayList<Integer>();
        initPath.add(0);
        allPathsTillNode.get(0).add(initPath);
        
        while(stack.size()>0){
            int node = stack.pop();
            
            List<List<Integer>> pathsTillNode = allPathsTillNode.get(node);
            
            for(int neighbor: graph[node]){
                if(!allPathsTillNode.containsKey(neighbor)){
                    allPathsTillNode.put(neighbor, new ArrayList<List<Integer>>());
                }
                
                List<List<Integer>> pathsTillNeighbor = allPathsTillNode.get(neighbor);
                
                for(List<Integer> path: pathsTillNode){
                    path = new ArrayList(path);
                    path.add(neighbor);
                    pathsTillNeighbor.add(path);
                }
                
                indegree[neighbor]--;
                if(indegree[neighbor]==0) stack.push(neighbor);
            }
        }
        
        return allPathsTillNode.get(nodes-1);
    }
}

// Solution-4: DP, DP works because we are given a DAG, so there are no cyclic paths

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return getAllPaths(0, graph.length-1, graph, new HashMap<Integer, List<List<Integer>>>());
    }
    
    private List<List<Integer>> getAllPaths(int node, int dest, int[][] graph, HashMap<Integer, List<List<Integer>>> dp){
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        
        if(node==dest){
            List<Integer> path = new ArrayList<Integer>();
            path.add(node);
            paths.add(path);
            return paths;
        }
        
        if(dp.get(node)!=null) return dp.get(node);
        
        for(int neighbor: graph[node]){
            List<List<Integer>> allPathsFromNeighbor = getAllPaths(neighbor, dest, graph, dp);
            
            for(List<Integer> path: allPathsFromNeighbor){
                path = new ArrayList<Integer>(path);
                path.add(0, node);
                paths.add(path);
            }
        }
        
        dp.put(node, paths);
        return dp.get(node);
    }
}