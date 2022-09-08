// Method-1 : DFS

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        dfs(root, list);
        return list;
    }
    
    private void dfs(TreeNode node, List<Integer> list){
        if(node==null) return;
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}

// Method-2 : Stack

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<Pair> stack = new Stack();
        List<Integer> list = new ArrayList();
        
        if(root==null) return list;
        
        stack.push(new Pair(root, 0));
        
        while(stack.size()>0){
            Pair pair = stack.peek();
            TreeNode node = pair.node;
            int state = pair.state;
            
            if(state==0){
                if(node.left!=null) stack.push(new Pair(node.left,0));
                pair.state++;
            }else if(state==1){
                list.add(node.val);
                
                if(node.right!=null) stack.push(new Pair(node.right,0));
                pair.state++;
            }else if(state==2){
                stack.pop();
            }
        }
        
        return list;
    }
    
    private class Pair{
        private TreeNode node;
        private int state;
        
        private Pair(TreeNode node, int state){
            this.node = node;
            this.state = state;
        }
    }
}

// Method-3 : Morris Inorder traversal

// TC : O(nodes)
// SC : O(1)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        TreeNode curr = root;
        
        while(curr!=null){
            if(curr.left==null){
                list.add(curr.val);
                curr = curr.right;
            }else{
                TreeNode pre = curr.left;

                while(pre.right!=null && pre.right!=curr) pre = pre.right;

                if(pre.right==null){
                    pre.right = curr;
                    curr = curr.left;
                }else{
                    pre.right = null;
                    list.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        
        return list;
    }
}