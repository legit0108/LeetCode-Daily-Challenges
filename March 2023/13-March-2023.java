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


// Solution-1: Recursion/DFS

// TC: O(nodes)
// SC: O(nodes) (recursive stack space)

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode node1, TreeNode node2){
        if(node1==null && node2==null) return true;
        if(node1==null || node2==null) return false;
        if(node1.val != node2.val) return false;
        
        if(isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left)) return true;
        else return false;
    }
}


// Solution-2: Iteration/BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        Queue<TreeNode> queue = new ArrayDeque();
        if(!add(queue, left, right)) return false;
        
        while(queue.size()>0){
            TreeNode node1 = queue.remove();
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            
            TreeNode node2 = queue.remove();
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;
            
            if(node1.val != node2.val) return false;
            
            if(!add(queue, left1, right2)) return false;
            if(!add(queue, right1, left2)) return false;
        }
        
        return true;
    }
    
    private boolean add(Queue<TreeNode> queue, TreeNode node1, TreeNode node2){
        if(sameShape(node1, node2)){
            if(notNull(node1, node2)){
                queue.add(node1);
                queue.add(node2);
            }
        }else return false;
        
        return true;
    }
    
    private boolean sameShape(TreeNode node1, TreeNode node2){
        if((node1!=null && node2==null) || (node2!=null && node1==null)) return false;
        else return true;
    }
    
    private boolean notNull(TreeNode node1, TreeNode node2){
        if(node1==null && node2==null) return false;
        else return true;
    }
}


// Solution-3: Iteration/Stack

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        Stack<TreeNode> stack = new Stack();
        if(!add(stack, left, right)) return false;
        
        while(stack.size()>0){
            TreeNode node1 = stack.pop();
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            
            TreeNode node2 = stack.pop();
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;
            
            if(node1.val != node2.val) return false;
            
            if(!add(stack, left1, right2)) return false;
            if(!add(stack, right1, left2)) return false;
        }
        
        return true;
    }
    
    private boolean add(Stack<TreeNode> stack, TreeNode node1, TreeNode node2){
        if(sameShape(node1, node2)){
            if(notNull(node1, node2)){
                stack.push(node1);
                stack.push(node2);
            }
        }else return false;
        
        return true;
    }
    
    private boolean sameShape(TreeNode node1, TreeNode node2){
        if((node1!=null && node2==null) || (node2!=null && node1==null)) return false;
        else return true;
    }
    
    private boolean notNull(TreeNode node1, TreeNode node2){
        if(node1==null && node2==null) return false;
        else return true;
    }
}