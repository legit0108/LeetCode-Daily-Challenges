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

// Solution-1: Recursion

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) return head;
        
        ListNode next = head.next;
        
        head.next = swapPairs(next.next);
        next.next = head;
        
        return next; 
    }
}


// Solution-2: Iteration, optimal

// TC: O(nodes)
// SC: O(1)

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;
        
        while(curr!=null && curr.next!=null){
            swap(prev, curr, curr.next);
            
            prev = curr;
            curr = curr.next;
        }
        
        head = dummy.next;
        dummy.next = null;
        return head;
    }
    
    private void swap(ListNode prev, ListNode node1, ListNode node2){
        ListNode next = node2.next;
        prev.next = node2;
        node2.next = node1;
        node1.next = next;
    }
}