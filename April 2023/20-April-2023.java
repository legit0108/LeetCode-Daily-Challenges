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

// Time and space: O(nodes)
// We give indices to each node, starting node of each level indexed from 0
// In this way, we minimize overflow (More chances of overflow exist if root indexed at 1)


// Solution-1: DFS

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        return getMaximumWidth(root, 0, 0, new HashMap<Integer, Integer>());
    }
    
    private int getMaximumWidth(TreeNode node, int index, int level, HashMap<Integer, Integer> map){
        if(node==null) return 0;
        
        int maximumWidth = 0;
        if(!map.containsKey(level)) map.put(level, index);
        maximumWidth = Math.max(maximumWidth, index-map.get(level)+1);
        
        maximumWidth = Math.max(maximumWidth, getMaximumWidth(node.left, index*2, level+1, map));
        maximumWidth = Math.max(maximumWidth, getMaximumWidth(node.right, index*2+1, level+1, map));
        
        return maximumWidth;
    }
}

// Solution-2: BFS

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
       return getMaximumWidth(root);
   }
   
   private int getMaximumWidth(TreeNode root){
       if(root==null) return 0;
       
       Queue<Pair> queue = new ArrayDeque();
       queue.add(new Pair(root, 0));
       int maximumWidth = 0;
       
       while(queue.size()>0){
           int start = -1;
           int end = -1;
           int size = queue.size();
           
           while(size>0){
               Pair pair = queue.remove();
               TreeNode node = pair.node;
               int index = pair.index;
               size--;
               
               TreeNode left = node.left;
               TreeNode right = node.right;
               
               if(start==-1) start = index;
               end = index;
               
               if(left!=null) queue.add(new Pair(left, 2*index));
               if(right!=null) queue.add(new Pair(right, 2*index+1));
           }
           
           maximumWidth = Math.max(maximumWidth, end-start+1);
       }
       
       return maximumWidth;
   }
   
   private class Pair{
       TreeNode node;
       int index;
       
       Pair(){}
       
       Pair(TreeNode node, int index){
           this.node = node;
           this.index = index;
       }
   }
}