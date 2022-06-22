// TC : O(nlogk)
// SC : O(k)
// where n is size of nums

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        
        for(int num : nums){
            minHeap.add(num);
            if(minHeap.size()>k) minHeap.remove();
        }
        
        int kthLargest = minHeap.remove();
        return kthLargest;
    }
}