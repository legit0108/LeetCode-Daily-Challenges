// TC: O(1)
// SC: O(1)

class Solution {
    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        int nextNodeVal = nextNode.val;
        
        node.val = nextNodeVal;
        node.next = nextNode.next;
    }
}