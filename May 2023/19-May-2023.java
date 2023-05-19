// Graph-coloring
// Color each node of graph such that you alternate colors for neighbors
// If graph can be colored in such a way then we have a bipartite graph else non bipartite

// Solution-1: DFS
// TC: O(V+E)
// SC: O(V)
// where V are the number of nodes, E are the number of edges

class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        Integer[] color = new Integer[nodes];
        
        for(int node=0; node<nodes; node++){
            if(color[node]==null){
                boolean componentIsBipartite = checkForBipartite(graph, color, node, 1, nodes);
                if(!componentIsBipartite) return false;
            }
        }
        
        return true;
    }
    
    private boolean checkForBipartite(int[][] graph, Integer[] color, int node, int colorToAssign, int nodes){
        color[node] = colorToAssign;
        
        for(int neighbor: graph[node]){
            Integer colorOfNeighbor = color[neighbor];
            
            if(colorOfNeighbor==null){
                boolean componentIsBipartite = checkForBipartite(graph, color, neighbor, colorToAssign^1, nodes);
                if(!componentIsBipartite) return false;
            }else if(colorOfNeighbor==color[node]) return false;
        }
        
        return true;
    }
}


// Solution-2: BFS
// TC: O(V+E)
// SC: O(V)

class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        Integer[] color = new Integer[nodes];
        
        for(int node=0; node<nodes; node++){
            if(color[node]==null){
                boolean componentIsBipartite = checkForBipartite(graph, color, node, nodes);
                if(!componentIsBipartite) return false;
            }
        }
        
        return true;
    }
    
    private boolean checkForBipartite(int[][] graph, Integer[] color, int node, int nodes){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(node);
        color[node] = 1;
        
        while(queue.size()>0){
            node = queue.remove();
            int colorOfNode = color[node];
            
            for(int neighbor: graph[node]){
                Integer colorOfNeighbor = color[neighbor];
                
                if(colorOfNeighbor==null){
                    color[neighbor] = colorOfNode^1;
                    queue.add(neighbor);
                }else if(colorOfNeighbor==colorOfNode) return false;
            }
        }
        
        return true;
    }
}


// Solution-3: Union-Find
// We try to divide the graph into sets such that all neighbors of a node are in the same set
// If on such division, a node and its neighbour are put into the same set, we have a non bipartite graph

// TC: O(E)
// SC: O(v)

class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        UnionFind dsu = new UnionFind(nodes);
        
        for(int node=0; node<nodes; node++){
            int[] neighbors = graph[node];
            
            if(neighbors.length==0) continue;
            
            int firstNeighbor = neighbors[0];
            int rootOfNode = dsu.find(node);
            
            for(int neighbor: neighbors){
                if(dsu.find(neighbor) == rootOfNode) return false;
                
                dsu.union(neighbor, firstNeighbor);
            }
        }
        
        return true;
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int nodes;
        
        UnionFind(int nodes){
            this.nodes = nodes;
            
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
        }
        
        void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2){
                parent[root1] = root2;
            }else if(rank2<rank1){
                parent[root2] = root1;
            }else{
                parent[root1] = root2;
                rank[root2]++;
            }
        }
        
        int find(int node){
            int initNode = node;
            
            while(parent[node]!=node) node = parent[node];
            
            int root = node;
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
    }
}