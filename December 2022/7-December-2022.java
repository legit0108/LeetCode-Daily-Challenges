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

// Solution - 1 : DFS + pruning, can also implement iteratively with stack

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root==null) return 0;

        int sum = 0;
        int val = root.val;
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        if(val>high) sum = rangeSumBST(left, low, high);
        else if(val<low) sum = rangeSumBST(right, low, high);
        else{ 
            sum = val;
            sum += rangeSumBST(left, low, high);
            sum += rangeSumBST(right, low, high);
        }
        
        return sum;
    }
}

// Solution - 2 : BFS + pruning

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root==null) return 0;
        
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            TreeNode node = queue.remove();
            
            int val = node.val;
            TreeNode left = node.left;
            TreeNode right = node.right;
            
            if(val>=low && val<=high) sum+=val;
            
            if(val>low && left!=null) queue.add(left);
            if(val<high && right!=null) queue.add(right);
        }
        
        return sum;
    }
}

// Solution - 3 : Morris traversal + pruning

// TC : O(nodes)
// SC : O(1)

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        TreeNode curr = root;
        int sum = 0;
        
        while(curr!=null){
            if(curr.left==null || curr.val<low){ // prune
                int val = curr.val;
                
                if(val>=low && val<=high) sum+=val;
                else if(val>high) break; // prune
                
                curr = curr.right;
            }else{
                TreeNode temp = curr.left;
                
                while(temp.right!=null && temp.right!=curr) temp = temp.right;
                
                if(temp.right==null){
                    temp.right = curr;
                    curr = curr.left;
                }else{
                    int val = curr.val;
                
                    if(val>=low && val<=high) sum+=val;
                    else if(val>high) break; // prune

                    temp.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return sum;
    }
}