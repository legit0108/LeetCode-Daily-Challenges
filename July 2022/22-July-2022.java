// TC : O(nodes)
// SC : O(1)

class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head==null||head.next==null) return head;
        
        ListNode smaller = new ListNode(-1);
        ListNode smallerItr = smaller;
        ListNode greater = new ListNode(-1);
        ListNode greaterItr = greater;
        ListNode curr = head;
        ListNode partitionHead = null;
        
        while(curr!=null){
            if(curr.val<x){
                smallerItr.next = curr;
                smallerItr = curr;
            }else{
                greaterItr.next = curr;
                greaterItr = curr;
            }
            
            curr = curr.next;
        }
        
        smallerItr.next = greater.next;
        greaterItr.next = null;
        partitionHead = smaller.next;
        smaller.next = null;
        greater.next = null;
        
        return partitionHead;
    }
}