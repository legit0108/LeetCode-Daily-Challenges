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

// Solution-1: Swap values

// TC: O(nodes)
// SC: O(1)

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        
        ListNode kthNodeFromStart = head;
        
        // get kth node from start
        while(k>1){
            kthNodeFromStart = kthNodeFromStart.next;
            k--;
        }
        
        // get kth node from end using slow-fast pointer technique
        ListNode slow = head;
        ListNode fast = kthNodeFromStart.next;
        
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        
        ListNode kthNodeFromEnd = slow;
        swap(kthNodeFromStart, kthNodeFromEnd);
        
        return head;
    }
    
    private void swap(ListNode node1, ListNode node2){
        int val = node1.val;
        node1.val = node2.val;
        node2.val = val;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Follow up: Can you do it without swapping the values, instead swapping the nodes? (Imagine a real-word situation)
// Solution-2: Swap actual nodes

// TC: O(nodes)
// SC: O(1)

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        
        ListNode dummy = new ListNode(-1); // when we physically swap nodes, our head can change so we use dummy node such that finally after swapping is done dummy.next corresponds to new head
        dummy.next = head;
        
        ListNode kthNodeFromStart = head;
        ListNode kMinusOnethNodeFromStart = dummy; // we need to maintain node just before kth node from start for swapping purposes
        
        // get kth node from start
        while(k>1){
            kMinusOnethNodeFromStart = kthNodeFromStart;
            kthNodeFromStart = kthNodeFromStart.next;
            k--;
        }
        
        // get kth node from end using slow-fast pointer technique
        ListNode slow = head;
        ListNode fast = kthNodeFromStart.next;
        ListNode prev = dummy; // we need to maintain node just before kth node from end for swapping purposes
        
        while(fast!=null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        
        ListNode kthNodeFromEnd = slow;
        ListNode kPlusOnethNodeFromEnd = prev;
        swap(kMinusOnethNodeFromStart, kthNodeFromStart, kPlusOnethNodeFromEnd, kthNodeFromEnd);
        
        head = dummy.next;
        dummy.next = null;
        return head;
    }
    
    private void swap(ListNode prev1, ListNode node1, ListNode prev2, ListNode node2){ // actually swap node1 and node2, not just their values
        // we have 3 cases
        // (1) node1.next == node2
        // (2) node2.next == node1
        // (3) general case 
        // dry run change in links of nodes on paper for better understanding 
        
        ListNode next1 = node1.next;
        ListNode next2 = node2.next;
        
        if(next1==node2){ // (1)
            prev1.next = node2;
            node1.next = next2;
            node2.next = node1;
        }else if(next2==node1){ // (2)
            prev2.next = node1;
            node2.next = next1;
            node1.next = node2;
        }else{ // (3)
            node1.next = next2;
            prev2.next = node1;

            node2.next = next1;
            prev1.next = node2;
        }
    }
}