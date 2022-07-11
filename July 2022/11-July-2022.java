// Method-1 : BFS/iterative
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList();
        if(root==null) return view;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                TreeNode node = queue.remove();
                size--;
                
                if(size==0) view.add(node.val);
                
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
        }
        
        return view;
    }
}

// Method-2 : DFS/recursive
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList();
        dfs(root,view,0);
        return view;
    }
    
    private void dfs(TreeNode node,List<Integer> view,int level){
        if(node==null) return;
        
        if(level==view.size()) view.add(node.val);
        else view.set(level,node.val);
        
        dfs(node.left,view,level+1);
        dfs(node.right,view,level+1);
    }
}