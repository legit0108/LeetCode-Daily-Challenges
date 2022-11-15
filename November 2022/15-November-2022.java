// DFS

// Subtree of a complete binary tree is also a complete binary tree
// Here only one of the two recursive calls expands (when leftDepth!=rightDepth), and each recursive call takes O(log(nodes)) time to calculate depth so time complexity is O(log(nodes)^2)
// We can calculate depth using one function also

// TC: O(log(nodes)^2)
// SC: O(log(nodes))

class Solution {
    public int countNodes(TreeNode root) {
        if(root==null) return 0;
        
        int leftDepth = getLeftDepth(root);
        int rightDepth = getRightDepth(root);
        
        if(leftDepth==rightDepth) return (1<<leftDepth)-1;
        else return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    private int getLeftDepth(TreeNode root){
        TreeNode curr = root;
        int leftDepth = 0;
        
        while(curr!=null){
            leftDepth++;
            curr = curr.left;
        }
        
        return leftDepth;
    }
    
    private int getRightDepth(TreeNode root){
        TreeNode curr = root;
        int rightDepth = 0;
        
        while(curr!=null){
            rightDepth++;
            curr = curr.right;
        }
        
        return rightDepth;
    }
}