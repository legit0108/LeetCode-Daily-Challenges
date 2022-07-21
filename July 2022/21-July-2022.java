// TC : O(nodes)
// SC : O(1)

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null||head.next==null||left==right) return head;
        
        ListNode prev = null;
        ListNode next = null;
        ListNode rangeHead = null;
        ListNode rangeTail = null;
        ListNode curr = head;
        int currIdx = 1;
        
        while(curr!=null){
            ListNode nextNode = curr.next;
            
            if(currIdx==left-1) prev = curr;
            else if(currIdx==right+1) next = curr;
            else if(currIdx>=left&&currIdx<=right){
                if(rangeHead==null){
                    rangeHead = rangeTail = curr;
                }else{
                    curr.next = rangeHead;
                    rangeHead = curr;
                }
            }
            
            curr = nextNode;
            currIdx++;
        }
        
        if(prev!=null) prev.next = rangeHead;
        else head = rangeHead;
        rangeTail.next = next;
        
        return head;
    }
}