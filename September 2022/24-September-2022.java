// Method-1 : DFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList();
        dfs(root, paths, new ArrayList(), targetSum);
        return paths;
    }
    
    private void dfs(TreeNode node, List<List<Integer>> paths, List<Integer> path, int targetSum){
        if(node == null) return;
        
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        int val = node.val;
        
        targetSum-=val;
        path.add(val);
        
        if(leftNode == null && rightNode == null){
            if(targetSum == 0) paths.add(new ArrayList(path));
        }
        
        dfs(leftNode, paths, path, targetSum);
        dfs(rightNode, paths, path, targetSum);
        
        path.remove(path.size()-1);
    }
}

// Method-2 : BFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList();
        bfs(root, paths, targetSum);
        return paths;
    }
    
    private void bfs(TreeNode root, List<List<Integer>> paths, int targetSum){
        if(root == null) return;
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(new Node(new ArrayList(), root, targetSum));
        
        while(queue.size() > 0){
            Node node = queue.remove();
            TreeNode treeNode = node.treeNode;
            List<Integer> path = node.path;
            targetSum = node.targetSum;
            
            TreeNode leftTreeNode = treeNode.left;
            TreeNode rightTreeNode = treeNode.right;
            int val = treeNode.val;
            
            path.add(val);
            targetSum-=val;
            
            if(leftTreeNode == null && rightTreeNode == null){
                if(targetSum == 0) paths.add(path); 
            }
            
            if(leftTreeNode != null) queue.add(new Node(new ArrayList(path), leftTreeNode, targetSum));
            if(rightTreeNode != null) queue.add(new Node(new ArrayList(path), rightTreeNode, targetSum));
        }
    }
    
    private class Node {
        private List<Integer> path;
        private TreeNode treeNode;
        private int targetSum;
        
        private Node(){
            
        }
        
        private Node(List<Integer> path, TreeNode treeNode, int targetSum){
            this.path = path;
            this.treeNode = treeNode;
            this.targetSum = targetSum;
        }
    }
}