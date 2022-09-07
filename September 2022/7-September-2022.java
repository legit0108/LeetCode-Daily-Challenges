// Method-1 : DFS/Recursion

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public String tree2str(TreeNode root) {
        StringBuilder str = new StringBuilder();
        dfs(root, str);
        return str.toString();
    }
    
    private void dfs(TreeNode node, StringBuilder str){
        if(node==null) return;
        
        int val = node.val;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        
        if(leftNode==null && rightNode==null){
            str.append(val);
            return;
        }
        if(leftNode!=null){
            str.append(val).append("(");
            dfs(leftNode, str);
            str.append(")");
        }
        if(rightNode!=null){
            if(leftNode==null) str.append(val).append("(").append(")");
            
            str.append("(");
            dfs(rightNode, str);
            str.append(")");
        }
    }
}


// Method-2 : Simulate recursion using stack

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public String tree2str(TreeNode root) {
        StringBuilder str = new StringBuilder();
        if(root==null) return str.toString();
        
        Stack<Pair> stack = new Stack();
        stack.push(new Pair(root, 0));
        
        while(stack.size()>0){
            Pair pair = stack.peek();
            TreeNode node = pair.node;
            int state = pair.state;
            
            int val = node.val;
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            if(leftNode==null&&rightNode==null){
                str.append(val);
                stack.pop();
            }
            else if(state==0){
                if(leftNode!=null){
                    str.append(val).append("(");
                    stack.push(new Pair(leftNode, 0));
                }else if(rightNode!=null){
                    str.append(val).append("(");
                }
                
                pair.state++;
            }else if(state==1){
                if(leftNode!=null || rightNode!=null){
                    str.append(")");
                    
                    if(rightNode!=null){
                        str.append("(");
                        stack.push(new Pair(rightNode, 0));
                    }
                    
                    pair.state++;
                }
            }else if(state==2){
                if(rightNode!=null) str.append(")");
                stack.pop();
            }
        }
        
        return str.toString();
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