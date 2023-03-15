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


// Solution-1: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
         
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        boolean nullFound = false;
        
        while(queue.size()>0){
            TreeNode node = queue.remove();
                
            TreeNode left = node.left;
            TreeNode right = node.right;
                
            if(left!=null){
                if(nullFound) return false; // return false if null found and left is non null
                else queue.add(left);
            }else nullFound = true; // null found
                    
            if(right!=null){
                if(nullFound) return false; // return false if null found and right is non null
                else queue.add(right);
            }else nullFound = true; // null found
        }
        
        return true;
    }
}