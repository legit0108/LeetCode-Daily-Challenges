// DFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    TreeNode prev;
    
    public void flatten(TreeNode root) {
        if(root==null) return;
        
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        
        if(prev!=null) prev.right = root;
        
        prev = root;
        root.left = null;
        root.right = null;
        
        flatten(leftNode);
        flatten(rightNode);
    }
}

// Stack
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public void flatten(TreeNode root) {
        if(root==null) return;
        
        Stack<TreeNode> stack = new Stack();  
        stack.push(root);
        TreeNode prev = null;
        
        while(stack.size()>0){
            TreeNode node = stack.pop();
            if(prev!=null) prev.right = node;
            
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            prev = node;
            node.left = null;
            node.right = null;
            
            if(rightNode!=null) stack.push(rightNode);
            if(leftNode!=null) stack.push(leftNode);
        }
    }
}

// Threaded Binary Tree
// TC : O(nodes)
// SC : O(1)

class Solution {
    public void flatten(TreeNode root) {
        TreeNode node = root;
        
        while(node!=null){
            if(node.left!=null){
                TreeNode tempNode = node.left;
                
                while(tempNode.right!=null) tempNode = tempNode.right;
                tempNode.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            
            node = node.right;
        }
    }
}