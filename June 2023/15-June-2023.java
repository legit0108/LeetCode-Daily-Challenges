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
class Solution {
    public int maxLevelSum(TreeNode root) {
        long maxLevelSum = Long.MIN_VALUE;
        int maxLevel = -1;
        int currLevel = 1;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            long currLevelSum = 0;
            
            while(size>0){
                TreeNode node = queue.remove();
                size--;
                TreeNode left = node.left;
                TreeNode right = node.right;
                
                currLevelSum+=(long)node.val;
                
                if(left!=null) queue.add(left);
                if(right!=null) queue.add(right);
            }
            
            if(currLevelSum>maxLevelSum){
                maxLevelSum = currLevelSum;
                maxLevel = currLevel;
            }
            
            currLevel++;
        }
        
        return maxLevel;
    }
}