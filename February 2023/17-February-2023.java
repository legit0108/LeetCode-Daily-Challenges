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


// Solution-1: Recursively using DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private int minDiff = Integer.MAX_VALUE;
    private TreeNode prev;
    
    public int minDiffInBST(TreeNode root) {
       dfs(root);
       return minDiff; 
    }
    
    private void dfs(TreeNode node){
        if(node==null) return;
        
        dfs(node.left);
        
        if(prev!=null) minDiff = Math.min(minDiff, node.val-prev.val);
        prev = node;
        
        dfs(node.right);
    }
}


// Solution-2: Iteratively using stack

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int minDiffInBST(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        TreeNode prev = null;
        
        Stack<TreeNode> stack = new Stack();
        push(stack, root);
        
        while(stack.size()>0){
            TreeNode curr = stack.pop();
            
            if(prev!=null) minDiff = Math.min(minDiff, curr.val-prev.val);
            prev = curr;
            
            push(stack, curr.right);
        }
        
        return minDiff;
    }
    
    private void push(Stack<TreeNode> stack, TreeNode node){
        while(node!=null){
            stack.push(node);
            node = node.left;
        }
    }
}


// Solution-3: Iteratively using Morris Traversal (constant space)

// TC: O(nodes)
// SC: O(1)

class Solution {
    public int minDiffInBST(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;
        int minDiff = Integer.MAX_VALUE;
        
        while(curr!=null){
            if(curr.left==null){
                if(prev!=null){
                    minDiff = Math.min(minDiff, curr.val-prev.val);
                }
                
                prev = curr;
                curr = curr.right;
            }else{
                TreeNode temp = curr.left;
                
                while(temp.right!=null && temp.right!=curr) temp = temp.right;
                
                if(temp.right==null){
                    temp.right = curr;
                    curr = curr.left;
                }else{
                    if(prev!=null){
                        minDiff = Math.min(minDiff, curr.val-prev.val);
                    }
                
                    temp.right = null;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        
        return minDiff;
    }
}