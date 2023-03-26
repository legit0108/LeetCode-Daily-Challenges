// Idea: Traversing a component, if a node repeats we found a cycle
// length of this cycle = distance at which node was encountered previously - current distance

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int longestCycle(int[] edges) {
        int nodes = edges.length;
        boolean[] visited = new boolean[nodes];
        int longestCycleLen = -1;
        
        for(int node=0; node<nodes; node++){
            if(visited[node]==false){
                HashMap<Integer, Integer> dist = new HashMap();
                int currNode = node;
                int currDist = 1;
                
                while(currNode!=-1 && !visited[currNode]){
                    visited[currNode] = true;
                    dist.put(currNode, currDist);
                    
                    currNode = edges[currNode];
                    currDist++;
                }
                
                if(currNode!=-1){
                    if(dist.containsKey(currNode)) longestCycleLen = Math.max(longestCycleLen, currDist-dist.get(currNode));   
                }
            }
        }
        
        return longestCycleLen;
    }
}