// TC : O(nodes)
// SC : O(1)

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) return true;
        
        ListNode middleNode = getMiddleNode(head);
        ListNode head1 = head;
        ListNode head2 = middleNode.next;
        middleNode.next = null;
        
        head2 = reverse(head2);
        if(equal(head1,head2)) return true;
        return false;
    }
    
    private ListNode getMiddleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next!=null&&fast.next.next!=null){
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
    
    private boolean equal(ListNode head1,ListNode head2){
        ListNode curr1 = head1;
        ListNode curr2 = head2;
        
        while(curr2!=null){
            if(curr1.val!=curr2.val) return false;
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        return true;
    }
}