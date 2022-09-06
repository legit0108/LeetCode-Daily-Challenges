// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root==null) return null;
        
        TreeNode prunedLeftSubtree = pruneTree(root.left);
        TreeNode prunedRightSubtree = pruneTree(root.right);
        
        if(prunedLeftSubtree==null && prunedRightSubtree==null && root.val==0) return null;
        
        root.left = prunedLeftSubtree;
        root.right = prunedRightSubtree;
        
        return root;
    }
}