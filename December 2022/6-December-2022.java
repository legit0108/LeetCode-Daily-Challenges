// Solution - 1 : Maintain flag / index

// TC : O(nodes)
// SC : O(1)

class Solution {
    public ListNode oddEvenList(ListNode head) {
         if(head==null || head.next==null) return head;

         ListNode curr = head;
         ListNode oddListHead = null;
         ListNode oddListTail = null;
         ListNode evenListHead = null;
         ListNode evenListTail = null;
         int idx = 1;

         while(curr!=null){
             ListNode next = curr.next;

             if(idx%2!=0){
                  if(oddListHead==null) oddListHead = oddListTail = curr;
                  else{
                      oddListTail.next = curr;
                      oddListTail = curr;
                  }
             }else{
                  if(evenListHead==null) evenListHead = evenListTail = curr;
                  else{
                      evenListTail.next = curr;
                      evenListTail = curr;
                  }
             }
             
             curr = next;
             idx++;
         }

         oddListTail.next = evenListHead;
         evenListTail.next = null;
         
         return oddListHead;
    }
}

// Solution - 2 : Without flag / index

// TC : O(nodes)
// SC : O(1)

class Solution {
    public ListNode oddEvenList(ListNode head) {
         if(head==null || head.next==null) return head;

         ListNode odd = head;
         ListNode even = head.next;
         ListNode evenHead = even;

         while(odd.next!=null && even.next!=null){
             odd.next = odd.next.next;
             even.next = even.next.next;

             odd = odd.next;
             even = even.next;
         }

         odd.next = evenHead;

         return head;
    }
}