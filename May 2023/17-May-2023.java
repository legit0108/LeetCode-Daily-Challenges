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

// Solution-1: Iteration
// Reverse second half and find max twin sum

// TC: O(nodes)
// SC: O(1)

class Solution {
    public int pairSum(ListNode head) {
        if(head==null) return 0;
        
        ListNode middle = getMiddle(head);
        middle.next = reverse(middle.next);
        
        ListNode twin1 = head;
        ListNode twin2 = middle.next;
        
        int maxTwinSum = 0;
        
        while(twin1!=null && twin2!=null){
            maxTwinSum = Math.max(maxTwinSum, twin1.val+twin2.val);
            
            twin1 = twin1.next;
            twin2 = twin2.next;
        }
        
        middle.next = reverse(middle.next);
        return maxTwinSum;
    }
    
    private ListNode getMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Recursion
// Maintain global twin and calculate maximum twin sum while returning from recursion

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    private ListNode twin;
    
    public int pairSum(ListNode head) {
        twin = head;
        return findMaxTwinSum(head);
    }
    
    private int findMaxTwinSum(ListNode node){
        if(node==null) return -1;
        
        int maxTwinSum = findMaxTwinSum(node.next);
        
        maxTwinSum = Math.max(maxTwinSum, node.val+twin.val);
        twin = twin.next;
        
        return maxTwinSum;
    }
}