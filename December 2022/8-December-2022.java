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

// Solution - 1 : DFS + StringBuilder / ArrayList

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder leafValSeq1 = new StringBuilder();
        StringBuilder leafValSeq2 = new StringBuilder();
        
        dfs(root1, leafValSeq1);
        dfs(root2, leafValSeq2);
        
        return leafValSeq1.toString().equals(leafValSeq2.toString());
    }
    
    private void dfs(TreeNode node, StringBuilder leafValSeq){
        if(node==null) return;

        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        if(left==null && right==null){
            leafValSeq.append(val).append(", ");
        }else{
            dfs(left, leafValSeq);
            dfs(right, leafValSeq);
        }
    }
}

// Solution - 2 : Stack

// TC : O(nodes)
// SC : O(log(nodes)) in average case, O(nodes) in worst case

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack1 = new Stack();
        stack1.push(root1);
        
        Stack<TreeNode> stack2 = new Stack();
        stack2.push(root2);
        
        while(stack1.size()>0 && stack2.size()>0){
            TreeNode curr1 = getLeafNode(stack1);
            TreeNode curr2 = getLeafNode(stack2);
            
            if(curr1.val!=curr2.val) return false;
        }
        
        if(stack1.size()==0 && stack2.size()==0) return true;
        else return false;
    }
    
    private TreeNode getLeafNode(Stack<TreeNode> stack){
        while(stack.size()>0){
            TreeNode curr = stack.pop();
            
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            
            if(left==null && right==null) return curr;
            
            if(right!=null) stack.push(right);
            if(left!=null) stack.push(left);
        }
        
        return null;
    }
}

// Solution - 3 : Morris traversal

// TC : O(nodes)
// SC : O(1)

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        TreeNode curr[] = new TreeNode[2];
        
        TreeNode dummy1 = new TreeNode(-1);
        dummy1.left = root1;
        curr[0] = dummy1;
        
        TreeNode dummy2 = new TreeNode(-1);
        dummy2.left = root2;
        curr[1] = dummy2;
        
        while(curr[0]!=null || curr[1]!=null){
            int currLeafNodeVal1 = getLeafNodeVal(curr, 0);
            int currLeafNodeVal2 = getLeafNodeVal(curr, 1);
            
            if(currLeafNodeVal1!=currLeafNodeVal2) return false;
        }
        
        dummy1.left = null;
        dummy2.left = null;
        
        return true;
    }
    
    private int getLeafNodeVal(TreeNode curr[], int idx){
        while(curr[idx]!=null){
            if(curr[idx].left==null){
                curr[idx] = curr[idx].right;
            }else{
                TreeNode temp = curr[idx].left;
                
                while(temp.right!=null && temp.right!=curr[idx]) temp = temp.right;
                
                if(temp.right==null){
                    temp.right = curr[idx];
                    curr[idx] = curr[idx].left;
                }else{
                    temp.right = null;
                    
                    int val = temp.val;
                    boolean isLeafNode = false;
                    
                    if(temp.left==null && temp.right==null) isLeafNode = true;
                    
                    curr[idx] = curr[idx].right;
                    
                    if(isLeafNode) return val;
                }
            }
        }
        
        return -1;
    }
}