// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        
        dfs(root, val, 1, depth);
        return root;
    }
    
    private void dfs(TreeNode node, int val, int currDepth, int depth){
        if(node==null) return;
        
        TreeNode leftSubtreeRoot = node.left;
        TreeNode rightSubtreeRoot = node.right;
        
        if(currDepth==depth-1){
            TreeNode newLeftSubtreeRoot = new TreeNode(val);
            node.left = newLeftSubtreeRoot;
            newLeftSubtreeRoot.left = leftSubtreeRoot;
            
            TreeNode newRightSubtreeRoot = new TreeNode(val);
            node.right = newRightSubtreeRoot;
            newRightSubtreeRoot.right = rightSubtreeRoot;
        }else{
            dfs(leftSubtreeRoot, val, currDepth+1, depth);
            dfs(rightSubtreeRoot, val, currDepth+1, depth);
        }
    }
}

// Solution-2: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        
        bfs(root, val, depth);
        return root;
    }
    
    private void bfs(TreeNode root, int val, int depth){
        Queue<TreeNode> queue = new ArrayDeque();
        int currDepth = 1;
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                TreeNode node = queue.remove();
                TreeNode leftSubtreeRoot = node.left;
                TreeNode rightSubtreeRoot = node.right;

                if(currDepth==depth-1){
                    TreeNode newLeftSubtreeRoot = new TreeNode(val);
                    node.left = newLeftSubtreeRoot;
                    newLeftSubtreeRoot.left = leftSubtreeRoot;
            
                    TreeNode newRightSubtreeRoot = new TreeNode(val);
                    node.right = newRightSubtreeRoot;
                    newRightSubtreeRoot.right = rightSubtreeRoot;
                }else{
                    if(leftSubtreeRoot!=null) queue.add(leftSubtreeRoot);
                    if(rightSubtreeRoot!=null) queue.add(rightSubtreeRoot);
                }
                
                size--;
            }
            
            currDepth++;
        }
    }
}