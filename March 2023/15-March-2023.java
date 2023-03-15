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
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
         
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        boolean nullFound = false;
        
        while(queue.size()>0){
            TreeNode node = queue.remove();
                
            TreeNode left = node.left;
            TreeNode right = node.right;
                
            if(left!=null){
                if(nullFound) return false; // return false if null found and left is non null
                else queue.add(left);
            }else nullFound = true; // null found
                    
            if(right!=null){
                if(nullFound) return false; // return false if null found and right is non null
                else queue.add(right);
            }else nullFound = true; // null found
        }
        
        return true;
    }
}


// Solution-2: DFS
// After encountering null node, no node should be encountered, be it towards the right or down below
// Find minimum position of null node since it acts like threshold point

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private Integer nullPosition;
    
    public boolean isCompleteTree(TreeNode root) {
        findNullPosition(root, 1);
        return dfs(root, 1);
    }
    
    private void findNullPosition(TreeNode node, int position){
        if(node==null){
            if(nullPosition==null){
                nullPosition = position;
            }else nullPosition = Math.min(nullPosition, position);
            
            return;
        }
        
        findNullPosition(node.left, 2*position);
        findNullPosition(node.right, 2*position+1);
    }
    
    private boolean dfs(TreeNode node, int position){
        if(node==null) return true;
        
        if(position>nullPosition) return false;
        
        return dfs(node.left, 2*position) && dfs(node.right, 2*position+1);
    }
}


/* 
 Solution-3: DFS 1 pass
 
 If a full tree has N nodes, increasing one more level will increase nodes by N+1 resulting total 2N+1 nodes
 nodes in full tree of height k+1 = 2^(k+1)-1
 nodes in full tree of height k = 2^k-1
 nodes in full tree of height k = (1/2)*nodes in full tree of height k+1
 
 To be complete at current root:
 Left subtree is full and has k levels, then right subtree must be k-1 level full or k level full
 So, left/2<=right<=left
 
 OR
 
 Left subtree is not full ans has k levels, then right subtree must be k-1 level full to satisfy complete tree property
 So, right<left<2*right+1 (since left is not complete)
 
 TC: O(nodes)
 SC: O(nodes)
*/

class Solution {
    public boolean isCompleteTree(TreeNode root) {
      return dfs(root) >= 0;
  }
  
  public int dfs(TreeNode root) {
      if(root == null) return 0;
      
      int left = dfs(root.left);
      int right = dfs(root.right);
      
      if(isPowerOfTwo(left+1) && (left/2<=right && right<=left)) return left+right+1;
      else if(isPowerOfTwo(right+1) && (right<left && left<2*right+1)) return left+right+1;
      else return -1;
  }
  
  private boolean isPowerOfTwo(int num){
      return (num&(num-1)) == 0;
  }
}