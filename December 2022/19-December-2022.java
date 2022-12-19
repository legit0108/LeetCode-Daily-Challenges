// Solution - 1 : DFS

// Let V be the number of vertices, E be the number of edges
// TC : O(V + E)
// SC : O(V + E)

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        
        return dfs(source, destination, graph, new boolean[n]);
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean dfs(int node, int destination, ArrayList<Integer>[] graph, boolean[] visited){
        if(node == destination) return true;
        
        visited[node] = true;
        
        for(int neighbour : graph[node]){
            if(!visited[neighbour]){
                boolean reachedDestination = dfs(neighbour, destination, graph, visited);
                if(reachedDestination) return true;
            }
        }
        
        return false;
    }
}

// Solution - 2 : Iterative DFS

// Let V be the number of vertices, E be the number of edges
// TC : O(V + E)
// SC : O(V + E)

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        
        return iterativeDfs(source, destination, graph, new boolean[n]);
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean iterativeDfs(int source, int destination, ArrayList<Integer>[] graph, boolean[] visited){
        Stack<Integer> stack = new Stack();
        stack.push(source);
       
        while(stack.size()>0){
            int node = stack.pop();
            
            if(node == destination) return true;
            
            if(visited[node]) continue;
            visited[node] = true;
            
            for(int neighbour : graph[node]){
                if(!visited[neighbour]) stack.push(neighbour);
            }
        }
        
        return false;
    }
}

// Solution - 3 : BFS

// Let V be the number of vertices, E be the number of edges
// TC : O(V + E)
// SC : O(V + E)

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        
        return bfs(source, destination, graph, new boolean[n]);
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean bfs(int source, int destination, ArrayList<Integer>[] graph, boolean[] visited){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(source);
       
        while(queue.size()>0){
            int node = queue.remove();
            
            if(node == destination) return true;
            
            if(visited[node]) continue;
            visited[node] = true;
            
            for(int neighbour : graph[node]){
                if(!visited[neighbour]) queue.add(neighbour);
            }
        }
        
        return false;
    }
}

// Solution - 4 : Union-Find

// Let V be the number of vertices, E be the number of edges
// TC : O(E)
// SC : O(V)

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int parent[] = new int[n];
        int rank[] = new int[n];
        
        for(int node=0; node<n; node++){
            parent[node] = node;
            rank[node] = 1;
        }
        
        for(int edge[] : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            union(node1, node2, parent, rank);
        }
        
        return find(source, parent) == find(destination, parent);
    }
    
    private void union(int node1, int node2, int parent[], int rank[]){
        int leader1 = find(node1, parent);
        int leader2 = find(node2, parent);
        
        if(leader1 == leader2) return;
        
        if(rank[leader1]<rank[leader2]){
            parent[leader1] = leader2;
        }else if(rank[leader2]<rank[leader1]){
            parent[leader2] = leader1;
        }else{
            parent[leader1] = leader2;
            rank[leader2]++;
        }
    }
    
    private int find(int node, int parent[]){
        if(parent[node] == node) return node;
        
        return parent[node] = find(parent[node], parent);
    }
}