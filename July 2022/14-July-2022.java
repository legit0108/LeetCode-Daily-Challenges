// TC : O(nodes)
// SC : O(nodes)

class Solution {
    private int preorderIdx;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
       int len = inorder.length;
       HashMap<Integer,Integer> map = new HashMap();
        
       for(int idx=0;idx<len;idx++) map.put(inorder[idx],idx);
       
       return buildTree(0,len-1,map,preorder,inorder); 
    }
    
    private TreeNode buildTree(int start,int end,HashMap<Integer,Integer> map,int
    preorder[],int inorder[]){
       if(start>end) return null;
        
       TreeNode node = new TreeNode(preorder[preorderIdx]);
       preorderIdx++;
        
       if(start==end) return node;
        
       int idx = map.get(node.val);
       node.left = buildTree(start,idx-1,map,preorder,inorder);
       node.right = buildTree(idx+1,end,map,preorder,inorder);
        
       return node; 
    }
}