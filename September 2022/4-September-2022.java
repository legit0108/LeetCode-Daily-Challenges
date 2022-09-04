// Method-1 : DFS

// TC : O(nodes*log(nodes))
// SC : O(nodes) 

class Solution {
    private int minCol;
    private int maxCol;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer,List<Node>> map = new HashMap();
        List<List<Integer>> ans = new ArrayList();
        minCol = Integer.MAX_VALUE;
        maxCol = Integer.MIN_VALUE;
        
        dfs(root, map, 0, 0);
        
        for(int key = minCol; key<=maxCol; key++){
            List<Node> nodesList = map.get(key);
            Collections.sort(nodesList);
            
            List<Integer> list = new ArrayList();
            for(Node node : nodesList){
                list.add(node.val);
            }
            
            ans.add(list);
        }
        
        return ans;
    }
    
    private void dfs(TreeNode node, HashMap<Integer,List<Node>> map, int row, int col){
        if(node==null) return;
        
        if(map.containsKey(col)==false) map.put(col, new ArrayList());
        map.get(col).add(new Node(node.val, row, col));
        
        minCol = Math.min(minCol, col);
        maxCol = Math.max(maxCol, col);
        
        dfs(node.left, map, row+1, col-1);
        dfs(node.right, map, row+1, col+1);
    }
    
    private class Node implements Comparable<Node> {
        private int row;
        private int col;
        private int val;
        
        private Node(){
            
        }
        
        private Node(int val, int row, int col){
            this.row = row;
            this.col = col;
            this.val = val;
        }
        
        public int compareTo(Node other){
            if(this.row!=other.row) return this.row-other.row;
            else return this.val-other.val;
        }
    }
}

// Method-2 : BFS

// TC : O(nodes*log(nodes))
// SC : O(nodes)
 
class Solution {
    private int minCol;
    private int maxCol;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer,List<Node>> map = new HashMap();
        List<List<Integer>> ans = new ArrayList();
        minCol = Integer.MAX_VALUE;
        maxCol = Integer.MIN_VALUE;
        
        bfs(root, map, 0, 0);
        
        for(int key = minCol; key<=maxCol; key++){
            List<Node> nodesList = map.get(key);
            Collections.sort(nodesList);
            
            List<Integer> list = new ArrayList();
            for(Node node : nodesList){
                list.add(node.treeNode.val);
            }
            
            ans.add(list);
        }
        
        return ans;
    }
    
    private void bfs(TreeNode node, HashMap<Integer,List<Node>> map, int row, int col){
        Queue<Node> queue = new ArrayDeque();
        queue.add(new Node(node, 0, 0));
        
        while(queue.size()>0){
            Node currNode = queue.remove();
            row = currNode.row;
            col = currNode.col;
            TreeNode treeNode = currNode.treeNode;
            
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol,col);
            int key = col;
            
            if(map.containsKey(key)==false) map.put(key, new ArrayList());
            map.get(key).add(new Node(treeNode, row, col));
            
            if(treeNode.left!=null) queue.add(new Node(treeNode.left, row+1, col-1));
            if(treeNode.right!=null) queue.add(new Node(treeNode.right, row+1, col+1));
        }
    }
    
    private class Node implements Comparable<Node> {
        private int row;
        private int col;
        private TreeNode treeNode;
        
        private Node(){
            
        }
        
        private Node(TreeNode treeNode, int row, int col){
            this.row = row;
            this.col = col;
            this.treeNode = treeNode;
        }
        
        public int compareTo(Node other){
            if(this.row!=other.row) return this.row-other.row;
            else return this.treeNode.val-other.treeNode.val;
        }
    }
}