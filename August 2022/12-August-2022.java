// TC : O(log(nodes)) average case , O(nodes) worst case
// SC : O(1)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        TreeNode lca = null;
        
        while(lca==null){
            if(curr.val>p.val&&curr.val>q.val) curr = curr.left;
            else if(curr.val<p.val&&curr.val<q.val) curr = curr.right;
            else{
                lca = curr;
            }
        }
        
        return lca;
    }
}