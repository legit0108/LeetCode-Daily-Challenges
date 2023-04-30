// Solution-1: DSU
// Give more priority to type 3 edges when connecting edges since one type 3 edge can be used by both alice and bob for traversal
// (connecting the graph with minimal edges results in maximum removals)

// TC: O(edges.length)
// SC: O(n)

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind aliceDsu = new UnionFind(n+1);
        UnionFind bobDsu = new UnionFind(n+1);
        int totalEdges = edges.length;
        Tuple tuple = new Tuple();
        
        processEdges(edges, 3, totalEdges, aliceDsu, bobDsu, tuple);
        processEdges(edges, 2, totalEdges, null, bobDsu, tuple); 
        processEdges(edges, 1, totalEdges, aliceDsu, null, tuple); 
        
        if(tuple.aliceVisitedEdges!=n-1 || tuple.bobVisitedEdges!=n-1) return -1;
        
        return totalEdges-tuple.edgesToKeep;
    }
    
    private void processEdges(int[][] edges, int edgeType, int totalEdges, UnionFind aliceDsu, UnionFind bobDsu, Tuple tuple){
        for(int[] edge: edges){
            int type = edge[0];
            int node1 = edge[1];
            int node2 = edge[2];
            boolean keepEdge = false;
            
            if(type==edgeType){
                if(aliceDsu!=null){
                    if(aliceDsu.union(node1, node2)){
                        keepEdge=true;
                        tuple.aliceVisitedEdges++;
                    }
                }
                
                if(bobDsu!=null){
                    if(bobDsu.union(node1, node2)){
                        keepEdge=true;
                        tuple.bobVisitedEdges++;
                    }
                }
                
                if(keepEdge) tuple.edgesToKeep++;
            }
        }
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
        
        boolean union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return false;
            
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
            
            return true;
        }
        
        int find(int node){
            int root = node;
            int initNode = node;
            
            while(parent[root]!=root) root = parent[root];
            
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
    }
    
    private class Tuple{
        int edgesToKeep;
        int aliceVisitedEdges;
        int bobVisitedEdges;
        
        Tuple(){}
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: DFS

// TC: O(V+E)
// SC: O(V) 
// where V = number of vertices, E = total number of edges

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // Build graph only of type-3 edges
        ArrayList<Integer>[] graph = createGraph(n, edges);
        
        // remove cyclic edges
        Set<String> removedEdges = new HashSet();
        int edgesToRemove = removeCyclicEdges(graph, n, removedEdges);
        
        // Process edges for bob and alice
        Set<Integer> nodes = new HashSet();
        int edgesToKeep=processEdges(edges, nodes, removedEdges, 2);
        if(edgesToKeep<n-1 || nodes.size()<n) return -1; // note that BOTH conditions are necessary
        else edgesToRemove+=edgesToKeep-(n-1);
        
        nodes = new HashSet();
        edgesToKeep=processEdges(edges, nodes, removedEdges, 1);
        if(edgesToKeep<n-1 || nodes.size()<n) return -1;
        else edgesToRemove+=edgesToKeep-(n-1);
        
        return edgesToRemove;
    }
    
    private ArrayList<Integer>[] createGraph(int n, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int node=0; node<=n; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int type = edge[0];
            int node1 = edge[1];
            int node2 = edge[2];
            
            if(type==3){
                graph[node1].add(node2);
                graph[node2].add(node1);
            }
        }
        
        return graph;
    }
    
    private int removeCyclicEdges(ArrayList<Integer>[] graph, int nodes, Set<String> removedEdges){
        int edgesToRemove = 0;
        boolean[] visited = new boolean[nodes+1];
        
        for(int node=0; node<nodes; node++){
            if(visited[node]==false) edgesToRemove+=dfs(graph, node, -1, removedEdges, visited);
        }
        
        return edgesToRemove;
    }
    
    private int dfs(ArrayList<Integer>[] graph, int node, int parent, Set<String> removedEdges, boolean[] visited){
        visited[node] = true;
        int edgesToRemove = 0;
        ArrayList<Integer> neighbors = graph[node];
        
        for(int neighbor: neighbors){
            if(neighbor==parent) continue;
            else if(!visited[neighbor]) edgesToRemove+=dfs(graph, neighbor, node, removedEdges, visited);
            else if(!removedEdges.contains(node+"->"+neighbor)){
                removedEdges.add(node+"->"+neighbor);
                removedEdges.add(neighbor+"->"+node);
                edgesToRemove++;
            }
        }
        
        return edgesToRemove;
    }
    
    private int processEdges(int[][] edges, Set<Integer> nodes, Set<String> removedEdges, int exclusiveType){
        int edgesToKeep = 0;
        
        for(int[] edge: edges){
            int type = edge[0];
            int node1 = edge[1];
            int node2 = edge[2];
            
            if(type==exclusiveType) continue;
            
            if(type==3 && (removedEdges.contains(node1+"->"+node2) || removedEdges.contains(node2+"->"+node1))) continue;
            nodes.add(node1);
            nodes.add(node2);
            
            edgesToKeep++;
        }
        
        return edgesToKeep;
    }
}