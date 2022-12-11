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

// Solution - 1 : DFS + update global maximum

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    private int maximumPathSum = -(int)1e9;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        
        return maximumPathSum;
    }
    
    private int dfs(TreeNode node){
        if(node==null) return -(int)1e9;
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        int leftPathSum = dfs(left);
        int rightPathSum = dfs(right);
        
        int maxOfLeftAndRightPathSum = Math.max(leftPathSum, rightPathSum);
        int sumOfLeftAndRightPathSum = leftPathSum+rightPathSum;
        
        maximumPathSum = Math.max(maximumPathSum, Math.max(Math.max(maxOfLeftAndRightPathSum, sumOfLeftAndRightPathSum) + val, val));
        
        return Math.max(maxOfLeftAndRightPathSum + val, val);
    }
}

// Solution - 2 : DFS without global maximum

// TC : O(nodes)
// SC : O(nodes)

 class Solution {
    public int maxPathSum(TreeNode root) {
        int pair[] = dfs(root);
        
        return pair[1];
    }
    
    private int[] dfs(TreeNode node){ // pair[0] = maximum path sum including node, pair[1] = maximum path sum in subtree of node (might include / not include node)
        if(node==null) return new int[]{-(int)1e9, -(int)1e9};
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        int leftPair[] = dfs(left); // pair obtained from left subtree
        int rightPair[] = dfs(right); // pair obtained from right subtree
        
        int leftPathSum = leftPair[0];
        int rightPathSum = rightPair[0];
        int maximumPathSum = Math.max(leftPair[1], rightPair[1]);
        
        int maxOfLeftAndRightPathSum = Math.max(leftPathSum, rightPathSum);
        int sumOfLeftAndRightPathSum = leftPathSum+rightPathSum;
        
        maximumPathSum = Math.max(maximumPathSum, Math.max(Math.max(maxOfLeftAndRightPathSum, sumOfLeftAndRightPathSum) + val, val));
        
        return new int[]{Math.max(maxOfLeftAndRightPathSum + val, val), maximumPathSum};
    }
}

// Bonus solution : Same as above, covering all possibilities but with shorter code

// TC : O(nodes) 
// SC : O(nodes)

class Solution {
    private int maximumPathSum = -(int)1e9;
    
    public int maxPathSum(TreeNode root) {
       dfs(root);
        
       return maximumPathSum; 
    }
    
    private int dfs(TreeNode node){
        if(node==null) return 0;
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        int leftPathSum = Math.max(dfs(left), 0);
        int rightPathSum = Math.max(dfs(right), 0);
        int currPathSum = leftPathSum + rightPathSum + val;
        
        maximumPathSum = Math.max(maximumPathSum, currPathSum);
        
        return Math.max(leftPathSum, rightPathSum) + val;
    }
}