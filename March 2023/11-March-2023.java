/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

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


// Solution-1: Split into two parts from middle

// TC: O(nlogn)
// SC: O(log(n)) recursive

// where n = size of linked list

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        
        return sortedListToBST(head, null);
    }
    
    private TreeNode sortedListToBST(ListNode head, ListNode end){ // end is the node just after the tail node
        if(head==end) return null;
        if(head.next==end) return new TreeNode(head.val);
        
        ListNode middle = getMiddleNode(head, end);
        ListNode next = middle.next;
        
        TreeNode root = new TreeNode(middle.val);
        root.left = sortedListToBST(head, middle);
        root.right = sortedListToBST(next, end);
        
        return root;
    }
    
    private ListNode getMiddleNode(ListNode head, ListNode end){
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next!=end && fast.next.next!=end){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}


// Solution-2: Linear time

// TC: O(n)
// SC: O(log(n)) recursive

// where n = size of linked list

class Solution {
    ListNode curr;
    
    public TreeNode sortedListToBST(ListNode head) {
        int size = getSize(head);
        curr = head;
        
        return sortedListToBST(size);
    }
    
    private int getSize(ListNode head){
        ListNode curr = head;
        int size = 0;
        
        while(curr!=null){ 
            curr = curr.next;
            size++;
        }
        
        return size;
    }
    
    private TreeNode sortedListToBST(int size){
        if(size<=0) return null;
        
        TreeNode left = sortedListToBST(size/2); // construct left subtree
        
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next; // move curr forward to construct right subtree
        
        TreeNode right = sortedListToBST(size-size/2-1); // construct right subtree
        
        root.left = left;
        root.right = right;
        return root;
    }
}