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


// Solution-1: DFS + HashMap (Recursion)

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private int postorderIndex;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = postorder.length;
        postorderIndex = len-1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int index=0; index<len; index++) map.put(inorder[index], index);
        
        return construct(postorder, 0, len-1, map);
    }
    
    private TreeNode construct(int[] postorder, int start, int end, HashMap<Integer, Integer> map){
        if(start>end) return null;
        
        int val = postorder[postorderIndex];
        TreeNode root = new TreeNode(val);
        postorderIndex--;
        
        if(start==end) return root;
        
        int index = map.get(val);
        root.right = construct(postorder, index+1, end, map);
        root.left = construct(postorder, start, index-1, map);
        
        return root;
    }
}


/*
 Solution-2: Stack (Iteration)

 Starting from the last element of the postorder and inorder array, 
 put element from postorder array to stack, each one is the right child of the last one 
 till the element in the postorder array is equal to the element in the inorder array

 Pop elements from the stack, decrease mark in the inorder array until peek() is 
 not equal to the inorder value or stack is empty

 The new element we scan from the postorder array is the left child 
 of the last element popped from the stack

 TC: O(nodes)
 SC: O(nodes)
*/

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        int inorderIndex = len-1;
        int postorderIndex = len-1;
        Stack<TreeNode> stack = new Stack();
        TreeNode prev = null;
        TreeNode root = null;
        
        while(postorderIndex>=0){
            while(stack.size()>0 && stack.peek().val==inorder[inorderIndex]){
                prev = stack.pop();
                inorderIndex--;
            }
            
            TreeNode node = new TreeNode(postorder[postorderIndex]);
            if(root==null) root = node;
            
            if(prev!=null) prev.left = node;
            else if(stack.size()>0) stack.peek().right = node;
            
            stack.push(node);
            prev = null;
            postorderIndex--;
        }
        
        return root;
    }
}