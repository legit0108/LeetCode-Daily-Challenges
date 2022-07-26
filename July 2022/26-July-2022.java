// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        
        TreeNode leftAns = lowestCommonAncestor(root.left,p,q);
        TreeNode rightAns = lowestCommonAncestor(root.right,p,q);
        
        if(leftAns!=null&&rightAns!=null) return root;
        if(root==p||root==q) return root;
        if(leftAns!=null) return leftAns;
        if(rightAns!=null) return rightAns;
        return null;
    }
}