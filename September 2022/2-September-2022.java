// Method-1 : BFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averageList = new ArrayList();
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        
        while(queue.size()>0){
            int size = queue.size();
            long sum = 0;
            int visNodes = 0;
            
            while(visNodes<size){
                TreeNode node = queue.remove();
                sum+=(long)node.val;
                visNodes++;
                
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            
            averageList.add(sum*1.0/size);
        }
        
        return averageList;
    }
}

// Method-2 : DFS
// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Pair> list = new ArrayList();
        dfs(root, list, 0);
        
        List<Double> averageList = new ArrayList();
        for(Pair pair : list){
            long sum = pair.sum;
            int size = pair.size;
            
            averageList.add(sum*1.0/size);
        }
        
        return averageList;
    }
    
    private void dfs(TreeNode node, List<Pair> list, int level){
        if(node==null) return;
        if(level>=list.size()) list.add(new Pair());
        
        long val = node.val;
        list.get(level).sum+=val;
        list.get(level).size++;
        
        dfs(node.left, list, level+1);
        dfs(node.right, list, level+1);
    }
    
    private class Pair {
        private long sum;
        private int size;
        
        private Pair(){
            this.sum = 0;
            this.size = 0;
        }
    }
}