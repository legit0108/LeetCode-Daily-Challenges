/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// All the solutions run in O(nodes) time and space


// Solution-1: Bottom-Up DFS

class Solution {
    public int longestZigZag(TreeNode root) {
        return dfs(root).longestZigZagPath;
    }
    
    private Path dfs(TreeNode node){
        if(node==null) return new Path(-1, -1, -1);
        
        Path leftPath = dfs(node.left);
        Path rightPath = dfs(node.right);
        
        int longestZigZagPathGoingLeft = 1+leftPath.longestZigZagPathGoingRight;
        int longestZigZagPathGoingRight = 1+rightPath.longestZigZagPathGoingLeft;
        
        int longestZigZagPath = Math.max(longestZigZagPathGoingLeft, longestZigZagPathGoingRight);
        longestZigZagPath = Math.max(longestZigZagPath, Math.max(leftPath.longestZigZagPath, rightPath.longestZigZagPath));
        
        return new Path(longestZigZagPathGoingLeft, longestZigZagPathGoingRight, longestZigZagPath);
    }
    
    private class Path{
        int longestZigZagPathGoingLeft;
        int longestZigZagPathGoingRight;
        int longestZigZagPath;
        
        Path(){}
        
        Path(int longestZigZagPathGoingLeft, int longestZigZagPathGoingRight, int longestZigZagPath){
            this.longestZigZagPathGoingLeft = longestZigZagPathGoingLeft;
            this.longestZigZagPathGoingRight = longestZigZagPathGoingRight;
            this. longestZigZagPath = longestZigZagPath;
        }
    }
}


// Solution-2: Top-Down DFS

class Solution {
    public int longestZigZag(TreeNode root) {
        return dfs(root, 0, 0);
    }
    
    private int dfs(TreeNode node, int pathTillNodeGoingLeft, int pathTillNodeGoingRight){
        if(node==null) return 0;
        
        int longestZigZagPath = Math.max(pathTillNodeGoingLeft, pathTillNodeGoingRight);
        
        longestZigZagPath = Math.max(longestZigZagPath, dfs(node.left, pathTillNodeGoingRight+1, 0));
        longestZigZagPath = Math.max(longestZigZagPath, dfs(node.right, 0, pathTillNodeGoingLeft+1));
        
        return longestZigZagPath;
    }
}


// Solution-3: BFS

class Solution {
    public int longestZigZag(TreeNode root) {
        return bfs(root);
    }
    
    private int bfs(TreeNode root){
        if(root==null) return -1;
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(new Node(root, 0, -1));
        int longestZigZagPath = 0;
        
        while(queue.size()>0){
            Node node = queue.remove();
            
            TreeNode treeNode = node.treeNode;
            int longestZigZagPathTillNode = node.longestZigZagPath;
            int direction = node.direction;
            
            longestZigZagPath = Math.max(longestZigZagPath, longestZigZagPathTillNode);
            
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;
            
            if(left!=null){
                if(direction!=0) queue.add(new Node(left, longestZigZagPathTillNode+1, 0));
                else queue.add(new Node(left, 1, 0));
            }
            if(right!=null){
                if(direction!=1) queue.add(new Node(right, longestZigZagPathTillNode+1, 1));
                else queue.add(new Node(right, 1, 1));
            }
        }
        
        return longestZigZagPath;
    }
    
    private class Node{
        TreeNode treeNode;
        int longestZigZagPath;
        int direction;
        
        Node(){}
        
        Node(TreeNode treeNode, int longestZigZagPath, int direction){
            this.treeNode = treeNode;
            this.longestZigZagPath = longestZigZagPath;
            this.direction = direction;
        }
    }
}