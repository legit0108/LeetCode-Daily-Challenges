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

// TC: O(n)
// SC: O(n)

class Solution {
    private int minAbsDiff = Integer.MAX_VALUE;
    private TreeNode prev;
    
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minAbsDiff;
    }
    
    private void dfs(TreeNode node){
        if(node==null) return;
        
        dfs(node.left);
        if(prev!=null) minAbsDiff = Math.min(minAbsDiff, node.val-prev.val);
        prev = node;
        dfs(node.right);
    }
}