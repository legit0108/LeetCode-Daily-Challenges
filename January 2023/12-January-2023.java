// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        int[] ans = new int[n];
        int[] map = dfs(0, -1, tree, labels, ans);
        
        return ans;
    }
    
    private int[] dfs(int node, int parent, ArrayList<Integer>[] tree, String labels, int[] ans){
        int[] map = new int[26];
        char label = labels.charAt(node);
        
        for(int child: tree[node]){
            if(child!=parent){
                int[] childMap = dfs(child, node, tree, labels, ans);
                
                for(int idx=0; idx<26; idx++) map[idx]+=childMap[idx];
            }
        }
        
        map[label-'a']++;
        ans[node] = map[label-'a'];
        
        return map;
    }
}

// Solution-2: BFS, Kahn's algorithm-like 

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        int[] ans = new int[n];
        bfs(tree, n, labels, ans);
        
        return ans;
    }
    
    private void bfs(ArrayList<Integer>[] tree, int n, String labels, int[] ans){
        int[][] map = new int[n][26];
        int[] degree = new int[n];
        
        for(int node=0; node<n; node++){
            for(int child: tree[node]){
                degree[child]++;
            }
        }
        
        Queue<Integer> queue = new ArrayDeque();
        for(int node=0; node<n; node++){
            if(degree[node]==1) queue.add(node);
        }
        
        while(queue.size()>0){
            int node = queue.remove();
            if(node==0) continue;
            
            char label = labels.charAt(node);
            map[node][label-'a']++;
            ans[node] = map[node][label-'a'];
            
            int parent = -1;
            for(int neighbour: tree[node]){
                if(degree[neighbour]>0){
                    parent = neighbour;
                    break;
                }
            }
            
            for(int idx=0; idx<26; idx++) map[parent][idx]+=map[node][idx];
                
            degree[node]--;
            degree[parent]--;
                
            if(degree[parent]==1) queue.add(parent);
        }
        
        int node = 0;
        char label = labels.charAt(node);
        map[node][label-'a']++;
        ans[node] = map[node][label-'a'];
    }
}