// The problem boils down to finding the minimum edge in the connected component where initial node 1 is present
// All 3 solutions run in O(nodes) time and space (DSU runs in O(nodes) time amortized)


// Solution-1: DFS

class Solution {
    public int minScore(int n, int[][] roads) {
        ArrayList<Pair>[] graph = createGraph(n, roads);
        boolean[] visited = new boolean[n+1];
        
        return dfs(1, graph, visited);
    }
    
    private ArrayList<Pair>[] createGraph(int nodes, int[][] edges){
        ArrayList<Pair>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList<Pair>();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            int dist = edge[2];
            
            graph[node1].add(new Pair(node2, dist));
            graph[node2].add(new Pair(node1, dist));
        }
        
        return graph;
    }
    
    private int dfs(int node, ArrayList<Pair>[] graph, boolean[] visited){
        visited[node] = true;
        int minDist = Integer.MAX_VALUE;
        
        List<Pair> list = graph[node];
        
        for(Pair pair: list){
            int neighbour = pair.node;
            int dist = pair.dist;
            int minDistFromNeighbour = Integer.MAX_VALUE;
            
            if(!visited[neighbour]) minDistFromNeighbour = dfs(neighbour, graph, visited);
            
            minDist = getMin(minDist, dist, minDistFromNeighbour);
        }
        
        return minDist;
    }
    
    private int getMin(int num1, int num2, int num3){
        return Math.min(Math.min(num1, num2), num3);
    }
    
    private class Pair{
        private int node;
        private int dist;
        
        private Pair(){
            
        }
        
        private Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

class Solution {
    public int minScore(int n, int[][] roads) {
        ArrayList<Pair>[] graph = createGraph(n, roads);
        
        return bfs(graph, 1, n);
    }
    
    private ArrayList<Pair>[] createGraph(int nodes, int[][] edges){
        ArrayList<Pair>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList<Pair>();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            int dist = edge[2];
            
            graph[node1].add(new Pair(node2, dist));
            graph[node2].add(new Pair(node1, dist));
        }
        
        return graph;
    }
    
    private int bfs(ArrayList<Pair>[] graph, int initNode, int nodes){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(initNode);
        boolean[] visited = new boolean[nodes+1];
        int minDist = Integer.MAX_VALUE;
        
        while(queue.size()>0){
            int node = queue.remove();
            
            if(visited[node]) continue;
            visited[node] = true;
            
            List<Pair> list = graph[node];
            
            for(Pair pair: list){
                int neigbhour = pair.node;
                int dist = pair.dist;
                minDist = Math.min(minDist, dist);
                
                if(!visited[neigbhour]) queue.add(neigbhour);
            }
        }
        
        return minDist;
    }
    
    private int getMin(int num1, int num2, int num3){
        return Math.min(Math.min(num1, num2), num3);
    }
    
    private class Pair{
        private int node;
        private int dist;
        
        private Pair(){
            
        }
        
        private Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union Find

class Solution {
    public int minScore(int n, int[][] roads) {
        UnionFind dsu = new UnionFind(n);
        
        for(int[] road: roads){
            int node1 = road[0];
            int node2 = road[1];
            int dist = road[2];
            
            dsu.union(node1, node2, dist);
        }
        
        int root = dsu.find(1);
        return dsu.getMinDist(root, roads);
    }
    
    private class UnionFind{
        private int[] parent;
        private int[] rank;
        
        private UnionFind(){
            
        }
        
        private UnionFind(int nodes){
            parent = new int[nodes+1];
            rank = new int[nodes+1];
            
            for(int node=1; node<=nodes; node++) parent[node] = node;
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
        
        private void union(int node1, int node2, int dist){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2){
                connect(root1, root2, dist);
            }else if(rank2<rank1){
                connect(root2, root1, dist);
            }else{
                connect(root1, root2, dist);
                rank[root2]++;
            }
        }
        
        private void connect(int root1, int root2, int dist){
            parent[root1] = root2;
        }
        
        private int getMinDist(int root, int[][] edges){
            /*
             Instead of looping through all edges at the end,
             we can also find minDist by maintaining an array
             for minDist corresponding to each root
             and then return minDist corresponding to the root of initial node, here node 1
            */

            int minDist = Integer.MAX_VALUE;
            
            for(int[] edge: edges){
                int node1 = edge[0];
                int node2 = edge[0];
                int dist = edge[2];
                
                if(find(node1)==root || find(node2)==root) minDist = Math.min(minDist, dist);
            }
            
            return minDist;
        }
    }
}