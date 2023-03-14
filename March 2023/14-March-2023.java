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


// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int num){
        if(node==null) return 0;
        
        TreeNode left = node.left;
        TreeNode right = node.right;
        int val = node.val;
        
        num = num*10+val;
        
        if(left==null && right==null) return num;
        else return dfs(left, num) + dfs(right, num);
    }
}


// Solution-2: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        
        Queue<Pair> queue = new ArrayDeque();
        queue.add(new Pair(root, 0));
        int sum = 0;
        
        while(queue.size()>0){
            Pair pair = queue.remove();
            TreeNode node = pair.node;
            int num = pair.num;
            
            TreeNode left = node.left;
            TreeNode right = node.right;
            int val = node.val;
            
            num = num*10+val;
            
            if(left==null && right==null) sum+=num;
            else{
                if(left!=null) queue.add(new Pair(left, num));
                if(right!=null) queue.add(new Pair(right, num));
            }
        }
        
        return sum;
    }
    
    private class Pair{
        private TreeNode node;
        private int num;
        
        private Pair(){
            
        }
        
        private Pair(TreeNode node, int num){
            this.node = node;
            this.num = num;
        }
    }
}


// Solution-3: Morris Traversal

// TC: O(nodes)
// SC: O(1)

class Solution {
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        
        TreeNode curr = root;
        int sum = 0;
        int num = 0;
        int depth = 1;
        
        while(curr!=null){
            if(curr.left==null){
                num = num*10+curr.val;
                
                if(curr.right==null) sum+=num;
                
                curr = curr.right;
            }else{
                depth = 1;
                
                TreeNode temp = curr.left;
                
                while(temp.right!=null && temp.right!=curr){
                    temp = temp.right;
                    depth++;
                }
                
                if(temp.right==null){
                    temp.right = curr;
                    
                    num = num*10+curr.val;
                    
                    curr = curr.left;
                }else{
                    temp.right = null;
                    
                    if(temp.left==null) sum+=num;
                    
                    num/=Math.pow(10, depth);
                    
                    curr = curr.right;
                }
            }
        }
        
        return sum;
    }
}