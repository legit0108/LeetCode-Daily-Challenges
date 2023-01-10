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

// Solution-1: Recursion (DFS)

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        
        if(p.val!=q.val) return false;
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}


// Solution-2: Iteration (BFS)

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(p);
        queue.add(q);
        
        while(queue.size()>0){
            TreeNode node1 = queue.remove();
            TreeNode node2 = queue.remove();
            
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            int val1 = node1.val;
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;
            int val2 = node2.val;
            
            if(val1!=val2) return false;
            
            if(left1==null || left2==null){
                if(!(left1==null && left2==null)) return false;
            }
            
            if(right1==null || right2==null){
                if(!(right1==null && right2==null)) return false;
            }
            
            if(left1!=null) queue.add(left1);
            if(left2!=null) queue.add(left2);
            
            if(right1!=null) queue.add(right1);
            if(right2!=null) queue.add(right2);
        }
        
        return true;
    }
}


// Solution-3: Constant space (Morris Traversal)

// TC: O(nodes)
// SC: O(1)

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        TreeNode node1 = p;
        TreeNode node2 = q;
        
        while(node1!=null && node2!=null){
           if(node1.left==null){
               if(node2.left!=null) return false;
               
               if(node1.val!=node2.val) return false;
               
               node1 = node1.right;
               node2 = node2.right;
           }else{
               TreeNode temp1 = node1.left;
               TreeNode temp2 = node2.left;
               
               if(temp2==null) return false;
               
               while(temp1.right!=null && temp1.right!=node1){
                   temp1 = temp1.right;
                   temp2 = temp2.right;
                   
                   if(temp2==null) return false;
               }
               
               if(temp1.right==null){
                   if(temp2.right!=null) return false;
                   
                   temp1.right = node1;
                   temp2.right = node2;
                   
                   node1 = node1.left;
                   node2 = node2.left;
               }else{
                   if(temp2.right!=node2) return false;
                   
                   temp1.right = null;
                   temp2.right = null;
                   
                   if(node1.val!=node2.val) return false;
                   
                   node1 = node1.right;
                   node2 = node2.right;
               }
           }   
        }
        
        if(node1==null && node2==null) return true;
        else return false;
    }
}