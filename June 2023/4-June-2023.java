// TC: O(nodes*nodes)
// SC: O(nodes)

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int nodes = isConnected.length;
        boolean[] visited = new boolean[nodes];
        int provinces = 0;
        
        for(int node=0; node<nodes; node++){
            if(!visited[node]){
                provinces++;
                dfs(node, isConnected, visited, nodes);
            }
        }
        
        return provinces;
    }
    
    private void dfs(int node, int[][] isConnected, boolean[] visited, int nodes){
        visited[node] = true;
        int[] neighbors = isConnected[node];
        
        for(int neighbor=0; neighbor<nodes; neighbor++){
            if(neighbors[neighbor]==1 && !visited[neighbor]) dfs(neighbor, isConnected, visited, nodes);
        }
    }
}