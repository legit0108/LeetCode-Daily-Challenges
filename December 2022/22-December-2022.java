// Solution-1: 2 DFS
// answer(node) = answer(parent) - size(node) + (totalNodes-size(node)-2);  

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        int[] answer = new int[n];
        int[] size = new int[n];
        
        dfs1(0, -1, 0, tree, size, answer);
        dfs2(0, -1, tree, size, answer, n);
        
        return answer;
    }
    
    private int dfs1(int node, int parent, int dist, ArrayList<Integer>[] tree, int[] size, int[] answer){
        answer[0]+=dist;
        
        for(int child: tree[node]){
            if(child!=parent){
                int count = dfs1(child, node, dist+1, tree, size, answer);
                size[node]+=count;
            }
        }
        
        return size[node]+1;
    }
    
    private void dfs2(int node, int parent,  ArrayList<Integer>[] tree, int[] size, int[] answer, int totalNodes){
        if(parent!=-1){
           answer[node] = answer[parent] - size[node] + (totalNodes-size[node]-2);  
        } 
        
        for(int child: tree[node]){
            if(child!=parent){
                dfs2(child, node, tree, size, answer, totalNodes);
            }
        }
    }
}

// Solution-2: 1 DFS
// answer(node) = sum(answer(children) + size(children)) 

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        HashMap<String, Pair> cache = new HashMap();
        int[] answer = new int[n];
        
        for(int node=0; node<n; node++){
            Pair pair = dfs(node, -1, tree, cache);
            
            answer[node] = pair.ans;
        }
        
        return answer;
    }
    
    private Pair dfs(int node, int parent, ArrayList<Integer>[] tree, HashMap<String, Pair> cache){
        String key = node+","+parent;
        
        if(cache.get(key)!=null) return cache.get(key);
        
        int ans = 0;
        int size = 0;
        
        for(int child: tree[node]){
            if(child!=parent){
                Pair pair = dfs(child, node, tree, cache);
                int subtreeAns = pair.ans;
                int subtreeSize = pair.size;

                ans+=subtreeAns+subtreeSize;
                size+=subtreeSize;
            }
        }
        
        size++;
        cache.put(key, new Pair(ans, size));
        return cache.get(key);
    }
    
    private class Pair{
        private int ans;
        private int size;
        
        private Pair(int ans, int size){
            this.ans = ans;
            this.size = size;
        }
    }
}