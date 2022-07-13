// Method - 1 : BFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList();
        if(root==null) return traversal;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            List<Integer> list = new ArrayList();
            
            while(size>0){
                TreeNode node = queue.remove();
                list.add(node.val);
                size--;
                
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            
            traversal.add(list);
        }
        
        return traversal;
    }
}

// Method - 2 : DFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList();
        dfs(root,traversal,0);
        return traversal;
    }
    
    private void dfs(TreeNode node,List<List<Integer>> traversal,int level){
        if(node==null) return;
        if(level==traversal.size()) traversal.add(new ArrayList());
        
        traversal.get(level).add(node.val);
        dfs(node.left,traversal,level+1);
        dfs(node.right,traversal,level+1);
    }
}