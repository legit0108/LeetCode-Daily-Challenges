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

// Solution-1: 
// Convert given linked list to list and use random.nextInt()
// random.nextInt(i) returns a number in [0....i-1] with equal probability

// TC: O(size) for constructor, O(1) for getRandom()
// SC: O(size)

class Solution {
    private List<ListNode> list;
    private Random random;
    
    public Solution(ListNode head) {
       list = new LinkedList<ListNode>();
       random = new Random();
        
       ListNode curr = head;
        
       while(curr!=null){
           list.add(curr);
           curr = curr.next;
       }
    }
    
    public int getRandom() {
       int idx = random.nextInt(list.size());
        
       return list.get(idx).val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */