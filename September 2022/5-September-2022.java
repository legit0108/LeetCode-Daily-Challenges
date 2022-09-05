// Method-1 : BFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrderTraversal = new ArrayList();
        if(root==null) return levelOrderTraversal;
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            List<Integer> list = new ArrayList();
            
            while(size>0){
                Node node = queue.remove();
                size--;
                list.add(node.val);
                
                for(Node childNode : node.children) queue.add(childNode);
            }
            
            levelOrderTraversal.add(list);
        }
        
        return levelOrderTraversal;
    }
}

// Method-2 : DFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrderTraversal = new ArrayList();
        if(root==null) return levelOrderTraversal;
        
        dfs(root, 0, levelOrderTraversal);
        return levelOrderTraversal;
    }
    
    private void dfs(Node node, int level, List<List<Integer>> levelOrderTraversal){
        if(level>=levelOrderTraversal.size()) levelOrderTraversal.add(new ArrayList());
        
        levelOrderTraversal.get(level).add(node.val);
        
        for(Node childNode : node.children){
            dfs(childNode, level+1, levelOrderTraversal);
        }
    }
}