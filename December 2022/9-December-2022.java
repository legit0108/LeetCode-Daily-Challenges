/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Solution - 1 : DFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    private int maxVal;
    
    public int maxAncestorDiff(TreeNode root) {
        int val = root.val;
        
        dfs(root, val, val);
        
        return maxVal; 
    }
    
    private void dfs(TreeNode node, int max, int min){
        if(node==null) return;
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        maxVal = Math.max(maxVal, Math.abs(max-val));
        maxVal = Math.max(maxVal, Math.abs(min-val));
        
        max = Math.max(max, val);
        min = Math.min(min, val);
        
        dfs(left, max, min);
        dfs(right, max, min);
    }
}

// Solution - 2 : BFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int maxAncestorDiff(TreeNode root) {
        Queue<Node> queue = new ArrayDeque();
        int val = root.val;
        queue.add(new Node(root, val, val));
        int maxVal = 0;
        
        while(queue.size()>0){
            Node node = queue.remove();
            TreeNode treeNode = node.treeNode;
            val = treeNode.val;
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;
            
            int max = node.max;
            int min = node.min;
            
            maxVal = Math.max(maxVal, Math.abs(max-val));
            maxVal = Math.max(maxVal, Math.abs(min-val));
            
            max = Math.max(max, val);
            min = Math.min(min, val);
            
            if(left!=null) queue.add(new Node(left, max, min));
            if(right!=null) queue.add(new Node(right, max, min));
        }
        
        return maxVal;
    }
    
    private class Node{
        private TreeNode treeNode;
        private int max;
        private int min;
        
        private Node(){
            
        }
        
        private Node(TreeNode treeNode, int max, int min){
            this.treeNode = treeNode;
            this.max = max;
            this.min = min;
        }
    }
}