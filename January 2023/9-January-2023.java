// Solution-1: Recursion (DFS)

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        dfs(root, list);
        return list;
    }
    
    private void dfs(TreeNode node, List<Integer> list){
        if(node==null) return;
        
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        list.add(val);
        
        dfs(left, list);
        dfs(right, list);
    }
}


// Solution-2: Iteration (Stack)

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        
        if(root==null) return list;
        
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        
        while(stack.size()>0){
            TreeNode node = stack.pop();
            
            int val = node.val;
            TreeNode left = node.left;
            TreeNode right = node.right;
            
            list.add(val);
            
            if(right!=null) stack.push(right);
            if(left!=null) stack.push(left);
        }
        
        return list;
    }
}


// Solution-3: Constant space (Morris Traversal)

// TC: O(nodes)
// SC: O(1), excluding space required for output

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        
        if(root==null) return list;
        
        TreeNode curr = root;
        
        while(curr!=null){
            TreeNode temp = curr.left;
            
            if(temp==null){
                list.add(curr.val);
                curr = curr.right;
            }else{
                while(temp.right!=null && temp.right!=curr) temp = temp.right;
                
                if(temp.right==null){
                    temp.right = curr;
                    list.add(curr.val);
                    curr = curr.left;
                }else{
                    temp.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return list;
    }
}