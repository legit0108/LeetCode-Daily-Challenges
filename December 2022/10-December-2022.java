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

 // DFS 

 // TC : O(nodes)
 // SC : O(nodes)
 
 class Solution {
    private long maxProd;
    
    public int maxProduct(TreeNode root) {
        long sum = dfs(root);
        
        dfs(root, sum);
        
        long mod = (long)1e9+7;
        int ans = (int)(maxProd%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
    
    private long dfs(TreeNode node){
        if(node==null) return 0;
        
        long val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        long sum = dfs(left) + dfs(right) + val;
        
        return sum;
    }
    
    private long dfs(TreeNode node, long sum){
        if(node==null) return 0;
        
        long val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        long subtreeSum = dfs(left, sum) + dfs(right, sum) + val;
        long sum1 = subtreeSum;
        long sum2 = sum-sum1;
        
        long currProd = sum1*sum2;
        maxProd = Math.max(maxProd, currProd);
        
        return subtreeSum;
    }
}