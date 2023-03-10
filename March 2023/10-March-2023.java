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


/* 
 Solution-2: Reservoir Sampling 
 
 1: probability of each node = 1
 1->2: probability of each node = 1*1/2 = 1/2
 1->2->3: probability of each node = 1*1/2*2/3 = 1/3
 so on and so forth while iterating stream
 
 NOTE: random.nextInt(i) returns any number in [0.....i-1] with equal probability
 
 Example: 
 1: probability of each node = 1
 1->2: if random.nextInt() returns 1, we set ans to 2, 
       from NOTE we know that each value is returned with equal probability
       so, probability of getting 1 = 1/2, probability of 0 = 1-1/2 = 1/2
 1->2->3: if random.nextInt() returns 2, we set ans to 3, 
          from NOTE we know that each value is returned with equal probability
          so, probability of getting 2 = 1/3,
          probability of not getting 2(no replacement) = 1-1/3 = 2/3
          probability of getting 0 or 1 in current iteration = 
          probability of getting 0 or 1 in previous iteration * probability of not getting 2(no replacement)
          = 1/2*2/3 = 1/3
          probability of getting 0 or 1 corresponds to getting node 1 or 2 in current iteration

 crux: 1*1/2*2/3*3/4*........*(n-1)/n = 1/n, where n = size of linked list
 probability of each node = 1/n
 
 follow up: 
 length unknown? get length via iterating linked list
 no extra space? provided by reservoir sampling
 
 TC: O(size) for getRandom(), O(1) for constructor 
 SC: O(1)
*/

class Solution {
    private ListNode head;
    private Random random;
    
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    public int getRandom() {
        int index = 0;
        
        ListNode curr = head;
        int randomVal = -1;
        
        while(curr!=null){
            if(random.nextInt(index+1)==index) randomVal = curr.val;
            
            curr = curr.next;
            index++;
        }
        
        return randomVal;
    }
}