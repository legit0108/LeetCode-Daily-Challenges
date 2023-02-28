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


// Solution-1: Use string(entire subtree) to uniquely identify subtree

// TC: O(n*n)
// SC: O(n*n)

// where n = total nodes

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<TreeNode> list = new ArrayList();
        
        dfs(root, map, list);
        
        return list;
    }
    
    private String dfs(TreeNode root, HashMap<String, Integer> map, List<TreeNode> list){
        if(root==null) return "N";
        
        String left = dfs(root.left, map, list);
        String right = dfs(root.right, map, list);
        
        String key = "("+left+")"+root.val+"("+right+")";
        map.put(key, map.getOrDefault(key, 0)+1);
        
        int count = map.get(key);
        if(count==2) list.add(root);
        
        return key;
    }
}


// Solution-2: Use string(3 integers) to uniquely identify subtree
// Each subtree is represented by a key consisting of 3 integers, so a key can be considered to be of O(1) length unlike O(n) length in last solution

// TC: O(n)
// SC: O(n)

// where n = total nodes

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        HashMap<String, Integer> rootToId = new HashMap<String, Integer>();
        List<TreeNode> list = new ArrayList();
        
        dfs(root, map, rootToId, list);
        
        return list;
    }
    
    private int dfs(TreeNode root, HashMap<Integer, Integer> map, HashMap<String, Integer> rootToId, List<TreeNode> list){
        if(root==null) return -1;
        
        int left = dfs(root.left, map, rootToId, list);
        int right = dfs(root.right, map, rootToId, list);
        
        String key = "("+left+")"+root.val+"("+right+")";
        if(!rootToId.containsKey(key)){
            int id = rootToId.size();
            rootToId.put(key, id);
        }
        
        int id = rootToId.get(key);
        map.put(id, map.getOrDefault(id, 0)+1);
        
        int count = map.get(id);
        if(count==2) list.add(root);
        
        return id;
    }
}