// Solution-1: DFS

// TC: O(n)
// SC: O(n)

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int edges = connections.length;
        if(edges<n-1) return -1;
        
        ArrayList<Integer>[] graph = createGraph(n, connections);
        boolean[] visited = new boolean[n];
        
        int connectedComponents = 0;
        for(int node=0; node<n; node++){
            if(!visited[node]){
                connectedComponents++;
                dfs(node, graph, visited);
            }
        }
        
        return connectedComponents-1;
    }
    
    private ArrayList<Integer>[] createGraph(int nodes, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();

        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private void dfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
        visited[node] = true;
        
        ArrayList<Integer> neighbours = graph[node];
        
        for(int neighbour: neighbours){
            if(!visited[neighbour]) dfs(neighbour, graph, visited);
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

// TC: O(n)
// SC: O(n)

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int edges = connections.length;
        if(edges<n-1) return -1;
        
        ArrayList<Integer>[] graph = createGraph(n, connections);
        boolean[] visited = new boolean[n];
        
        int connectedComponents = 0;
        for(int node=0; node<n; node++){
            if(!visited[node]){
                connectedComponents++;
                bfs(node, graph, visited);
            }
        }
        
        return connectedComponents-1;
    }
    
    private ArrayList<Integer>[] createGraph(int nodes, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();

        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private void bfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(node);
        
        while(queue.size()>0){
            node = queue.remove();
            
            if(visited[node]) continue;
            visited[node] = true;
        
            ArrayList<Integer> neighbours = graph[node];
            for(int neighbour: neighbours){
                if(!visited[neighbour]) queue.add(neighbour);
            }
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union-Find

// TC: O(edges) amortized
// SC: O(n)

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int edges = connections.length;
        if(edges<n-1) return -1;
        
        UnionFind dsu = new UnionFind(n);
        int connectedComponents = n;
        
        for(int[] edge: connections){
            int node1 = edge[0];
            int node2 = edge[1];
            
            if(dsu.find(node1)!=dsu.find(node2)){ 
                dsu.union(node1, node2);
                connectedComponents--;
            }
        }
        
        return connectedComponents-1;
    }
    
    private class UnionFind{
        private int[] parent;
        private int[] rank;
        private int nodes;
        
        private UnionFind(){
            
        }
        
        private UnionFind(int nodes){
            this.nodes = nodes;
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
        }
        
        private int find(int node){
            int initNode = node;
            
            while(node!=parent[node]) node = parent[node];
            
            int root = node;
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
        
        private void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2) connect(root1, root2);
            else if(rank2<rank1) connect(root2, root1);
            else{ 
                connect(root1, root2);
                rank[root2]++;  
            }
        }
        
        private void connect(int root1, int root2){
            parent[root1] = root2;
        }
    }
}