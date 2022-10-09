// Solution-1: Two sum problem on inorderList

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList();
        dfs(root, inorderList);
        
        int start = 0;
        int end = inorderList.size()-1;
        
        while(start<end){
            int sum = inorderList.get(start)+inorderList.get(end);
            
            if(sum>k) end--;
            else if(sum<k) start++;
            else return true;
        }
        
        return false;
    }
    
    private void dfs(TreeNode node, List<Integer> inorderList){
        if(node==null) return;
        
        TreeNode leftSubtree = node.left;
        TreeNode rightSubtree = node.right;
        int val = node.val;
        
        dfs(leftSubtree, inorderList);
        inorderList.add(val);
        dfs(rightSubtree, inorderList);
    }
}

// Solution-2: Simulate two sum using stack

// TC: O(nodes)
// SC: O(log(nodes)) average case, O(nodes) worst case

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Stack<Pair> inorderStack = new Stack();
        Stack<Pair> reversedInorderStack = new Stack();
        
        inorderStack.push(new Pair(root, 0));
        reversedInorderStack.push(new Pair(root, 0));
        
        TreeNode inorderNode = getNextInInorder(inorderStack);
        TreeNode reversedInorderNode = getNextInReversedInorder(reversedInorderStack);
        
        while(inorderNode.val<reversedInorderNode.val){
            int sum = inorderNode.val+reversedInorderNode.val;
            
            if(sum>k) reversedInorderNode = getNextInReversedInorder(reversedInorderStack);
            else if(sum<k) inorderNode = getNextInInorder(inorderStack);
            else return true;
        }
        
        return false;
    }
    
    private TreeNode getNextInInorder(Stack<Pair> stack){
        while(stack.size()>0){
            Pair pair = stack.peek();
            TreeNode node = pair.node;
            int state = pair.state;
            
            if(state==0){
                TreeNode leftSubtree = node.left;
                if(leftSubtree!=null) stack.push(new Pair(leftSubtree, 0));
                pair.state++;
            }else if(state==1){
                TreeNode rightSubtree = node.right;
                if(rightSubtree!=null) stack.push(new Pair(rightSubtree, 0));
                pair.state++;
                
                return node;
            }else stack.pop();
        }
        
        return null;
    }
    
    private TreeNode getNextInReversedInorder(Stack<Pair> stack){
        while(stack.size()>0){
            Pair pair = stack.peek();
            TreeNode node = pair.node;
            int state = pair.state;
            
            if(state==0){
                TreeNode rightSubtree = node.right;
                if(rightSubtree!=null) stack.push(new Pair(rightSubtree, 0));
                pair.state++;
            }else if(state==1){
                TreeNode leftSubtree = node.left;
                if(leftSubtree!=null) stack.push(new Pair(leftSubtree, 0));
                pair.state++;
                
                return node;
            }else stack.pop();
        }
        
        return null;
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