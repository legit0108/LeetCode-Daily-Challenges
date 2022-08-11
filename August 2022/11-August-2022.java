// Inorder traversal of BST is always strictly increasing
// Perform Morris traversal to achieve O(1) space

// TC : O(nodes)
// SC : O(1)

class Solution {
    public boolean isValidBST(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;
        
        while(curr!=null){
            if(curr.left==null){
                if(prev!=null){
                    if(curr.val<=prev.val) return false;
                }
                
                prev = curr;
                curr = curr.right;
            }else{
                TreeNode temp = curr.left;
                
                while(temp.right!=null&&temp.right!=curr) temp = temp.right;
                
                if(temp.right==null){
                    temp.right = curr;
                    curr = curr.left;
                }else{
                    if(prev!=null){
                        if(curr.val<=prev.val) return false;
                    }
                
                    prev = curr;
                    temp.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return true;
    }
}