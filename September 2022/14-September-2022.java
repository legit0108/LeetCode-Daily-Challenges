// Method-1 : DFS

// TC : O(nodes)
// SC : O(nodes), O(1) if recursive stack space ignored

class Solution {
    public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int mask){
        if(node == null) return 0;
        
        int val = node.val;
        mask = mask ^ (1<<val);
        
        if(node.left == null && node.right == null){
            if(mask == 0 || singleBitSet(mask)) return 1;
            else return 0;
        }else{
            int count = 0;
            count+=dfs(node.left, mask);
            count+=dfs(node.right, mask);

            return count;
        }
    }
    
    private boolean singleBitSet(int mask){ // single bit set -> power of 2
        if(mask <= 0) return false;
        
        if((mask&(mask-1)) == 0) return true;
        else return false;
    }
}

// Method-2 : BFS 

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int pseudoPalindromicPaths (TreeNode root) {
        return bfs(root);
    }
    
    private int bfs(TreeNode root){
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(root, 0));
        int paths = 0;
        
        while(queue.size() > 0){
            Pair pair = queue.remove();
            TreeNode node = pair.node;
            int mask = pair.mask;
            
            int val = node.val;
            mask = mask ^ (1<<val);
            
            if(node.left == null && node.right == null){
                if(mask == 0 || singleBitSet(mask)) paths++;
            }else{
                if(node.left != null) queue.add(new Pair(node.left, mask));
                if(node.right != null) queue.add(new Pair(node.right, mask));
            }
        }
        
        return paths;
    }
    
    private boolean singleBitSet(int mask){ // single bit set -> power of 2
        if(mask <= 0) return false;
        
        if((mask&(mask-1)) == 0) return true;
        else return false;
    }
    
    private class Pair{
        private TreeNode node;
        private int mask;
        
        private Pair(TreeNode node, int mask){
            this.node = node;
            this.mask = mask;
        }
    }
}

// Can also be implemented with morris traversal in O(nodes) time and O(1) space 