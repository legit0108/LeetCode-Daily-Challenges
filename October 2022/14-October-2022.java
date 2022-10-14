// TC: O(nodes)
// SC: O(1)

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null) return null;
        
        ListNode nodeBeforeMiddle = getNodeBeforeMiddle(head);
         
        ListNode middle = nodeBeforeMiddle.next;
        nodeBeforeMiddle.next = middle.next;
        middle.next = null;
        
        return head;
    }
    
    private ListNode getNodeBeforeMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode nodeBeforeMiddle = null;
        
        while(fast!=null && fast.next!=null){
            nodeBeforeMiddle = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return nodeBeforeMiddle;
    }
}