// Idea: Nodes in separate connected components are unreachable from each other

// All the solutions run in O(n) time and space (Union-Find is amortized O(n))


// Solution-1: DFS

class Solution {
    public long countPairs(int n, int[][] edges) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        
        long pairs = 0;
        long sum = 0;
        boolean[] visited = new boolean[n];
        
        for(int node=0; node<n; node++){
            if(!visited[node]){
                long count = dfs(node, graph, visited);
                pairs+=sum*count;
                sum+=count;
            }
        }
        
        return pairs;
    }
    
    private long dfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
        visited[node] = true;
        long count = 0;
        
        ArrayList<Integer> neighbours = graph[node];
        for(int neighbour: neighbours){
            if(!visited[neighbour]) count+=dfs(neighbour, graph, visited);
        }
        
        return count+1;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
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
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

class Solution {
    public long countPairs(int n, int[][] edges) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        
        long pairs = 0;
        long sum = 0;
        boolean[] visited = new boolean[n];
        
        for(int node=0; node<n; node++){
            if(!visited[node]){
                long count = bfs(node, graph, visited);
                pairs+=sum*count;
                sum+=count;
            }
        }
        
        return pairs;
    }
    
    private long bfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(node);
        long count = 0;
        
        while(queue.size()>0){
            node = queue.remove();
            
            if(visited[node]) continue;
            visited[node] = true;
            
            count++;
        
            ArrayList<Integer> neighbours = graph[node];
            for(int neighbour: neighbours){
                if(!visited[neighbour]) queue.add(neighbour);
            }
        }
        
        return count;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
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
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union-Find

class Solution {
    public long countPairs(int n, int[][] edges) {
        UnionFind dsu = new UnionFind(n);
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            dsu.union(node1, node2);
        }
        
        HashMap<Integer, Long> connectedComps = new HashMap();
        for(int node=0; node<n; node++){
            int parent = dsu.find(node);
            connectedComps.put(parent, connectedComps.getOrDefault(parent, 0l)+1l);
        }
        
        long pairs = 0;
        long sum = 0;
        
        for(int root: connectedComps.keySet()){
            long count = connectedComps.get(root);
            pairs+=sum*count;
            sum+=count;
        }
        
        return pairs;
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int nodes;
        
        UnionFind(){}
        
        UnionFind(int nodes){
            this.nodes = nodes;
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node; 
        }
        
        int find(int node){
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
        
        void union(int node1, int node2){
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
        
        void connect(int root1, int root2){
            parent[root1] = root2;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-4: Union-Find One Pass

class Solution {
    public long countPairs(int n, int[][] edges) {
        UnionFind dsu = new UnionFind(n);
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            dsu.union(node1, node2);
        }
        
        return dsu.pairs;
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        long[] size;
        
        int nodes;
        long pairs;
        
        UnionFind(){}
        
        UnionFind(int nodes){
            this.nodes = nodes;
            pairs = (long)nodes*((long)nodes-1l)/2l; // initially we assume all nodes are unreachable from one another
            
            parent = new int[nodes];
            rank = new int[nodes];
            size = new long[nodes];
            
            for(int node=0; node<nodes; node++){
                parent[node] = node;
                size[node] = 1;
            }
        }
        
        int find(int node){
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
        
        void union(int node1, int node2){
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
        
        void connect(int root1, int root2){
            parent[root1] = root2;
            
            /*
              when we connect two components,
              we decrement our pairs by the product of sizes of the components
              since each node in the first component can reach 
              each node in the second component after connecting the components
             */

            long size1 = size[root1];
            long size2 = size[root2];
            
            pairs-=size1*size2;
            size[root2]+=size1;
        }
    }
}