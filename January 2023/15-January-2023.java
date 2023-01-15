/*
 -> If we start with the largest value, any path will be a good path
 -> Total paths = nCr where n is the count of nodes of the largest value
 -> Remove the largest value and repeat
 -> After removal, treat components separately, no path exists between components
 -> Instead of tearing apart nodes, stitch them together, DSU
 -> Apply the same logic in reverse
 -> Tearing apart things, speed it up, apply reverse algorithm
 
 TC: O(V + E)
 SC: O(V)
 
 where V is the number of vertices and E is the number of edges
*/

class Solution {
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int nodes = vals.length;
        ArrayList<Integer>[] tree = new ArrayList[nodes];
        UnionFind set = new UnionFind(nodes);
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        int maxVal = 0;
        for(int val: vals) maxVal = Math.max(maxVal, val);
        
        ArrayList<Integer>[] valToNodes = new ArrayList[maxVal+1];
        for(int val=0; val<=maxVal; val++) valToNodes[val] = new ArrayList();
        
        for(int node=0; node<nodes; node++){
            int val = vals[node];
            valToNodes[val].add(node);
        }
        
        int paths = nodes;
        for(int val=0; val<=maxVal; val++){
            ArrayList<Integer> valNodes = valToNodes[val];
            
            for(int node: valNodes){
                ArrayList<Integer> neighbours = tree[node];
                
                for(int neighbour: neighbours){
                    if(vals[neighbour] <= vals[node]) set.union(node, neighbour);
                }
            }
            
            HashMap<Integer, Integer> map = new HashMap();
            for(int node: valNodes){
                int leader = set.find(node);
                map.put(leader, map.getOrDefault(leader, 0)+1);
            }
            
            for(int leader: map.keySet()){
                int size = map.get(leader);
                
                paths+=size*(size-1)/2; // nCr
            }
        }
        
        return paths;
    }
    
    private class UnionFind{
        private int[] parent;
        private int[] rank;
        
        private UnionFind(int nodes){
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++){
                parent[node] = node;
                rank[node] = 1;
            }
        }
        
        private void union(int node1, int node2){
            int leader1 = find(node1);
            int leader2 = find(node2);

            if(leader1==leader2) return;

            int rankLeader1 = rank[leader1];
            int rankLeader2 = rank[leader2];

            if(rankLeader1<rankLeader2){
                parent[leader1] = leader2;
            }else if(rankLeader2<rankLeader1){
                parent[leader2] = leader1;
            }else{
                parent[leader1] = leader2;
                rank[leader2]++;
            }
        }
    
        private int find(int node){
            int leader = node;

            while(leader!=parent[leader]) leader = parent[leader];

            while(node!=parent[node]){
                int par = parent[node];
                parent[node] = leader;
                node = par;
            }

            return leader;
        }
    }
}