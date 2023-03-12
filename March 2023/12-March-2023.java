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


// Solution-1: Using min heap

// TC: O(n*k*log(k))
// SC: O(k)

// where n = average length of a single sorted linked list, k = number of such linked lists

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap;
        minHeap = new PriorityQueue<ListNode>((node1, node2)->Integer.compare(node1.val, node2.val));
        for(ListNode head: lists){
            if(head!=null) minHeap.add(head);
        }
        
        ListNode head = null, tail = null;
        
        while(minHeap.size()>0){
            ListNode curr = minHeap.remove();
            
            if(tail==null){
                head = tail = curr;
            }else{
                tail.next = curr;
                tail = curr;
            }
            
            ListNode next = curr.next;
            if(next!=null) minHeap.add(next);
        }
        
        return head;
    }
}


// Solution-2: Divide and Conquer

// TC: O(n*k*log(k))
// SC: O(log(k)) recursive 

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length-1);
    }
    
    private ListNode mergeKLists(ListNode[] lists, int start, int end){
        if(start>end) return null;
        if(start==end) return lists[start];
        
        int mid = start + (end-start)/2;
        
        ListNode head1 = mergeKLists(lists, start, mid);
        ListNode head2 = mergeKLists(lists, mid+1, end);
        
        return merge(head1, head2);
    }
    
    private ListNode merge(ListNode head1, ListNode head2){
        if(head1==null && head2==null) return null;
        if(head1==null) return head2;
        if(head2==null) return head1;
        
        ListNode head = null, tail = null;
        ListNode curr1 = head1, curr2 = head2;
        
        while(curr1!=null && curr2!=null){
            if(curr1.val<curr2.val){
                if(tail==null){
                    head = tail = curr1;
                }else{
                    tail.next = curr1;
                    tail = curr1;
                }
                
                curr1 = curr1.next;
            }else{
                if(tail==null){
                    head = tail = curr2;
                }else{
                    tail.next = curr2;
                    tail = curr2;
                }
                
                curr2 = curr2.next;
            }
        }
        
        if(curr1!=null) tail.next = curr1;
        else if(curr2!=null) tail.next = curr2;
        
        return head;
    }
}


// Solution-3: Iterative Divide and Conquer

// TC: O(n*k*log(k))
// SC: O(1)

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len==0) return null;
        
        int step = 1;
        
        while(step<len){
            for(int index=0; index+step<len; index = index+2*step){
                lists[index] = merge(lists[index], lists[index+step]);
            }
            
            step = step*2;
        }
        
        return lists[0];
    }
    
    private ListNode merge(ListNode head1, ListNode head2){
        if(head1==null && head2==null) return null;
        if(head1==null) return head2;
        if(head2==null) return head1;
        
        ListNode head = null, tail = null;
        ListNode curr1 = head1, curr2 = head2;
        
        while(curr1!=null && curr2!=null){
            if(curr1.val<curr2.val){
                if(tail==null){
                    head = tail = curr1;
                }else{
                    tail.next = curr1;
                    tail = curr1;
                }
                
                curr1 = curr1.next;
            }else{
                if(tail==null){
                    head = tail = curr2;
                }else{
                    tail.next = curr2;
                    tail = curr2;
                }
                
                curr2 = curr2.next;
            }
        }
        
        if(curr1!=null) tail.next = curr1;
        else if(curr2!=null) tail.next = curr2;
        
        return head;
    }
}