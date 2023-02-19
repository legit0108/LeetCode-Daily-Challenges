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


// Solution-1: BFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if(root==null) return result;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        int level = 0;
        
        while(queue.size()>0){
            List<Integer> list = new ArrayList();
            int size = queue.size();
            
            while(size>0){
                TreeNode node = queue.remove();
                TreeNode left = node.left;
                TreeNode right = node.right;
                size--;
                
                // Add will be O(1) if linked list is used
                int val = node.val;
                if(level%2==0) list.add(val);
                else list.add(0, val);
                
                if(left!=null) queue.add(left);
                if(right!=null) queue.add(right);
            }
            
            result.add(list);
            level++;
        }
        
        return result;
    }
} 


// Solution-2: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if(root==null) return result;
        
        dfs(root, 0, result);
        
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<List<Integer>> result){
        if(node==null) return;
        
        if(level>=result.size()) result.add(new ArrayList());
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        // Add will be O(1) is linked list is used
        List<Integer> list = result.get(level);
        if(level%2==0) list.add(val);
        else list.add(0, val);
        
        dfs(left, level+1, result);
        dfs(right, level+1, result);
    }
}


// Solution-3: Stack

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if(root==null) return result;
        
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        int level = 0;
        
        while(stack.size()>0){
            Stack<TreeNode> temp = new Stack();
            List<Integer> list = new ArrayList();
            int size = stack.size();
            
            while(size>0){
                TreeNode node = stack.pop();
                TreeNode left = node.left;
                TreeNode right = node.right;
                size--;
                
                list.add(node.val);
                
                if(level%2==0){
                    if(left!=null) temp.push(left);
                    if(right!=null) temp.push(right);
                }else{
                    if(right!=null) temp.push(right);
                    if(left!=null) temp.push(left);
                }
            }
            
            result.add(list);
            level++;
            stack = temp;
        }
        
        return result;
    }
}