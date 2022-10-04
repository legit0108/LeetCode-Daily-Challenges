// Solution-1 : DFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        
        int val = root.val;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        
        targetSum-=val;
        
        if(leftNode==null && rightNode==null){
            if(targetSum==0) return true;
            else return false;
        }
        
        return hasPathSum(leftNode, targetSum) || hasPathSum(rightNode, targetSum);
    }
}

// Solution-2 : BFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(root, targetSum));
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            TreeNode node = pair.node;
            targetSum = pair.targetSum;
            
            int val = node.val;
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            targetSum-=val;
            
            if(leftNode==null && rightNode==null){
                if(targetSum==0) return true;
            }
            
            if(leftNode!=null) queue.add(new Pair(leftNode, targetSum));
            if(rightNode!=null) queue.add(new Pair(rightNode, targetSum));
        }
        
        return false;
    }
    
    private class Pair{
        private TreeNode node;
        private int targetSum;
        
        private Pair(TreeNode node, int targetSum){
            this.node = node;
            this.targetSum = targetSum;
        }
    }
}