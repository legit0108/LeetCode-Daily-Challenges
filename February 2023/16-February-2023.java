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


// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return 1+Math.max(left, right);
    }
}


// Solution-2: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        int depth = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                TreeNode node = queue.remove();
                TreeNode leftNode = node.left;
                TreeNode rightNode = node.right;
                
                if(leftNode!=null) queue.add(leftNode);
                if(rightNode!=null) queue.add(rightNode);
                
                size--;
            }
            
            depth++;
        }
        
        return depth;
    }
}