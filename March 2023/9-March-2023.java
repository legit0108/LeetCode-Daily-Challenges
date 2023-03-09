/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


// Solution-1: Find cyclic node via set

// TC: O(nodes)
// SC: O(nodes)

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null) return null;
        
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode curr = head;
        
        while(curr!=null){
            if(set.contains(curr)) return curr;
            
            set.add(curr);
            
            curr = curr.next;
        }
        
        return null;
    }
}


// Solution-2: Cycle Detection Algorithm

// TC: O(nodes)
// SC: O(1)

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null) return null; // base case
        
        ListNode slow = head;
        ListNode fast = head;
        boolean foundLoop = false;
        
        // find the point where slow and fast meet 
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow==fast){
                foundLoop = true;
                slow = head;
                break;
            }
        }
        
        // return null if loop does not exist
        if(!foundLoop) return null;
        
        // move slow and fast with the same speed to reach the starting node of cycle
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}