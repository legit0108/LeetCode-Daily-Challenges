// Min-heap
// Each query runs in O(logk) time

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;
    
    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue();
        this.k = k;
        
        for(int num: nums) add(num);
    }
    
    public int add(int val) { // O(logk)
        minHeap.add(val);
        if(minHeap.size()>k) minHeap.remove();
        
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */