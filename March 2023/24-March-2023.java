/* 
 Idea: The cost of an edge already present in the tree should be treated as 0, and the cost of the corresponding reversed edge added should be treated as 1
 We can also treat the cost of existing edge as 1 and reversed edge as 0 because going from a node to the root and going from the root to a node ultimately mean the same thing
 Also, this has an easier implementation
*/

// Both BFS and DFS solutions run in O(n) time and space


// Solution-1: DFS

class Solution {
    public int minReorder(int n, int[][] connections) {
        ArrayList<Edge>[] tree = createTree(connections, n);
        
        return dfs(0, -1, tree);
    }
    
    private int dfs(int node, int parent, ArrayList<Edge>[] tree){
        int ans = 0;
        ArrayList<Edge> neighbours = tree[node];
        
        for(Edge edge: neighbours){
            int neighbour = edge.node;
            int weight = edge.weight;
            
            if(neighbour!=parent){
                ans+=weight+dfs(neighbour, node, tree);
            }
        }
        
        return ans;
    }
    
    private ArrayList<Edge>[] createTree(int[][] edges, int nodes){
        ArrayList<Edge>[] tree = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(new Edge(node2, 1));
            tree[node2].add(new Edge(node1, 0));
        }
        
        return tree;
    }
    
    private class Edge{
        int node;
        int weight;
        
        Edge(){
            
        }
        
        Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

class Solution {
    public int minReorder(int n, int[][] connections) {
        ArrayList<Edge>[] tree = createTree(connections, n);
        
        return bfs(tree);
    }
    
    private int bfs(ArrayList<Edge>[] tree){
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(0, -1));
        
        int ans = 0;
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            int node = pair.node;
            int parent = pair.parent;
            
            ArrayList<Edge> neighbours = tree[node];
            for(Edge edge: neighbours){
                int neighbour = edge.node;
                int weight = edge.weight;
                
                if(neighbour!=parent){
                    ans+=weight;
                    queue.add(new Pair(neighbour, node));
                }
            }
        }
        
        return ans;
    }
    
    private ArrayList<Edge>[] createTree(int[][] edges, int nodes){
        ArrayList<Edge>[] tree = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(new Edge(node2, 1));
            tree[node2].add(new Edge(node1, 0));
        }
        
        return tree;
    }
    
    private class Edge{
        int node;
        int weight;
        
        Edge(){
            
        }
        
        Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }
    
    private class Pair{
        int node;
        int parent;
        
        Pair(){
            
        }
        
        Pair(int node, int parent){
            this.node = node;
            this.parent = parent;
        }
    }
}