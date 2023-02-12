/*
   For every node, we need to find out the number of cars 
   required to take all representatives in the 
   subtree of the node to its parent node
*/


// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private long minFuelCost;
    
    public long minimumFuelCost(int[][] roads, int seats) {
        int nodes = roads.length+1;
        ArrayList<Integer>[] tree = createTree(roads, nodes);
        
        dfs(tree, 0, -1, seats);
        
        return minFuelCost;
    }
    
    private int dfs(ArrayList<Integer>[] tree, int node, int parent, int seats){
        int representatives = 1; // node itself has a representative
        
        for(int neighbor: tree[node]){
            if(neighbor!=parent) representatives+=dfs(tree, neighbor, node, seats);
        }
        
        // We need ceil(representatives/seats) cars to travel from current node to parent
        if(node!=0) minFuelCost+=((long)(representatives+seats-1)/(long)seats);
        
        return representatives;
    }
    
    private ArrayList<Integer>[] createTree(int[][] edges, int nodes){
        ArrayList<Integer>[] tree = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        return tree;
    }
}


// Solution-2: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public long minimumFuelCost(int[][] roads, int seats) {
        int nodes = roads.length+1;
        int[] degree = new int[nodes];
        ArrayList<Integer>[] tree = createTree(roads, degree, nodes);
        
        return bfs(tree, nodes, degree, seats);
    }
    
    private long bfs(ArrayList<Integer>[] tree, int nodes, int[] degree, int seats){
        Queue<Integer> queue = new ArrayDeque();
        int[] representatives = new int[nodes];
        
        for(int node=1; node<nodes; node++){
            if(degree[node]==1) queue.add(node);
        }
        
        long minFuelCost = 0;
        
        while(queue.size()>0){
            int node = queue.remove();
            representatives[node]++; // node itself has a representative
            long representativesOfNode = representatives[node];
            minFuelCost+=((long)(representativesOfNode+seats-1)/(long)seats);
            
            for(int neighbor: tree[node]){
                if(neighbor!=0){
                    degree[neighbor]--;
                    representatives[neighbor]+=representativesOfNode;
                    
                    if(degree[neighbor]==1) queue.add(neighbor);
                }
            }
        }
        
        return minFuelCost;
    }
    
    private ArrayList<Integer>[] createTree(int[][] edges, int[] degree, int nodes){
        ArrayList<Integer>[] tree = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
            
            degree[node1]++;
            degree[node2]++;
        }
        
        return tree;
    }
}