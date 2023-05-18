// All nodes having no incoming edges form smallest set of vertices from which we can reach all nodes in the graph

// TC: O(edges.size())
// SC: O(n)

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncomingEdge = new boolean[n];
        
        for(List<Integer> edge: edges){
            int from = edge.get(0);
            int to = edge.get(1);
            
            hasIncomingEdge[to] = true;
        }
        
        List<Integer> smallestSetOfVertices = new ArrayList();
        
        for(int node=0; node<n; node++){
            if(!hasIncomingEdge[node]) smallestSetOfVertices.add(node);
        }
        
        return smallestSetOfVertices;
    }
}