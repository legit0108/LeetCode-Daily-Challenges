// Method-1 : DFS/recursion

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE); 
    }
    
    private int goodNodes(TreeNode node, int maxSoFar){
        if(node==null) return 0;
        
        int count = (node.val>=maxSoFar)?1:0;
        maxSoFar = Math.max(maxSoFar,node.val);
        
        count+=goodNodes(node.left,maxSoFar);
        count+=goodNodes(node.right,maxSoFar);
        
        return count;
    }
}

// Method-2 : BFS/iteration

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int goodNodes(TreeNode root) {
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(root,Integer.MIN_VALUE));
        int count = 0;
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            TreeNode node = pair.node;
            int maxSoFar = pair.maxSoFar;
            
            if(node.val>=maxSoFar) count++;
            maxSoFar = Math.max(maxSoFar,node.val);
            
            if(node.left!=null) queue.add(new Pair(node.left,maxSoFar));
            if(node.right!=null) queue.add(new Pair(node.right,maxSoFar));
        }
        
        return count;
    }
    
    private class Pair{
        private TreeNode node;
        private int maxSoFar;
        
        private Pair(){
            
        }
        
        private Pair(TreeNode node, int maxSoFar){
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }
}