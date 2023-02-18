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


// Solution-1: DFS/Recursive

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = invertTree(right);
        root.right = invertTree(left);
        
        return root;
    }
}


/* 
   Solution-2: BFS/Iterative
   
   Why is it necessary to write an iterative code for tree problems?
   In the worst case, your tree might be skewed, having a depth equal to the size of the tree
   Running a recursive solution, in this case, is non-optimal since it can lead to stack overflow as stack memory is limited
   We can avoid this by running an iterative solution instead since heap memory is more abundant than stack memory
   
   TC: O(nodes)
   SC: O(nodes)
*/

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            TreeNode node = queue.remove();
            
            swap(node);
            
            TreeNode left = node.left;
            TreeNode right = node.right;
            
            if(left!=null) queue.add(left);
            if(right!=null) queue.add(right);
        }
        
        return root;
    }
    
    private void swap(TreeNode node){
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        node.left = right;
        node.right = left;
    }
}