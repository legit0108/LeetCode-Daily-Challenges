// TC: O(n)
// SC: O(n)

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        int root = -1;
        for(int node=0; node<n; node++){
            int parent = manager[node];
            
            if(parent==-1) root = node;
            else tree[parent].add(node);
        }
        
        return dfs(root, tree, informTime);
    }
    
    private int dfs(int node, ArrayList<Integer>[] tree, int[] informTime){
        int minutes = 0;
        ArrayList<Integer> children = tree[node];
        
        for(int child: children){
            minutes = Math.max(minutes, dfs(child, tree, informTime));
        }
        
        return minutes+informTime[node];
    }
}