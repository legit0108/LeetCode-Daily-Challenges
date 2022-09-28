// Acquire (n+1)th node from end and perform removal

// TC : O(length of list), one pass
// SC : O(1)

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || head.next==null) return null;
        
        ListNode curr = head;
        n++;
        
        while(n>0 && curr!=null){
            curr = curr.next;
            n--;
        }
        
        if(n>0){
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        }else{
            ListNode slow = head;
            ListNode fast = curr;
            
            while(fast!=null){
                slow = slow.next;
                fast = fast.next;
            }
            
            ListNode nodeToRemove = slow.next;
            slow.next = nodeToRemove.next;
            nodeToRemove.next = null;
            
            return head;
        }
    }
}