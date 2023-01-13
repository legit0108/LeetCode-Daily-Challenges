// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private int longestPath;
    
    public int longestPath(int[] parent, String s) {
        int nodes = s.length();
        ArrayList<Integer>[] tree = new ArrayList[nodes];
        
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int node=0; node<nodes; node++){
             int par = parent[node];
             if(par!=-1) tree[par].add(node);
        }
        
        dfs(0, s, tree);
        return longestPath;
    }
    
    private int dfs(int node, String s, ArrayList<Integer>[] tree){
        int maxDepthFromChild = 0;
        int secondMaxDepthFromChild = 0;
        
        for(int child: tree[node]){
            int depthFromChild = dfs(child, s, tree);
            
            if(s.charAt(node)!=s.charAt(child)){
                if(depthFromChild>=maxDepthFromChild){
                    secondMaxDepthFromChild = maxDepthFromChild;
                    maxDepthFromChild = depthFromChild;
                }else if(depthFromChild>=secondMaxDepthFromChild) secondMaxDepthFromChild = depthFromChild;
            }
        }
        
        int depth = maxDepthFromChild+1;
        longestPath = Math.max(longestPath, maxDepthFromChild+secondMaxDepthFromChild+1);
        
        return depth;
    } 
}


// Solution-2: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private int longestPath;
    
    public int longestPath(int[] parent, String s) {
        int nodes = s.length();
        int[] degree = new int[nodes];
        
        for(int node=0; node<nodes; node++){
             int par = parent[node];
             
             if(par!=-1){
                 degree[par]++;
                 degree[node]++;
             }
             
        }
        
        return bfs(s, nodes, parent, degree);
    }
    
    private int bfs(String s, int nodes, int[] parent, int[] degree){
        Queue<Integer> queue = new ArrayDeque();
        int[] maxDepth = new int[nodes];
        int[] secondMaxDepth = new int[nodes];
        int longestPath = 0;
        
        for(int node=0; node<nodes; node++){
            if(degree[node]==1) queue.add(node); 
        }
        
        while(queue.size()>0){
            int node = queue.remove();
            
            if(node==0) continue;
            
            int maxDepthFromChild = maxDepth[node];
            int secondMaxDepthFromChild = secondMaxDepth[node];
            longestPath = Math.max(longestPath, maxDepthFromChild+secondMaxDepthFromChild+1);
            
            int par = parent[node];
            int depthFromChild = maxDepthFromChild+1;
            
            if(s.charAt(node) != s.charAt(par)){
                if(depthFromChild>=maxDepth[par]){
                    secondMaxDepth[par] = maxDepth[par];
                    maxDepth[par] = depthFromChild;
                }else if(depthFromChild>=secondMaxDepth[par]) secondMaxDepth[par] = depthFromChild;
            }
            
            degree[node]--;
            degree[par]--;
            
            if(degree[par]==1) queue.add(par);
        }
        
        int node = 0;
        int maxDepthFromChild = maxDepth[node];
        int secondMaxDepthFromChild = secondMaxDepth[node];
        longestPath = Math.max(longestPath, maxDepthFromChild+secondMaxDepthFromChild+1);
 
        return longestPath;
    } 
}