// Brute force: Add to list, sort list, sorted list maintained, each query answered in O(size*log(size))
// Better: Simulate sorted list using two heaps

/*

Follow ups

1) Use bucket to collect frequency of each number, add and findMedian work in O(1) (theoretically, findMedian actually works in O(100))

2) Overtime, we do not need to care about numbers outside [0, 100], we know that median must be in [0, 100] since this range contains 99% elements, we can store count of numbers less than 0 and count of numbers greater than 100 and follow same procedure as done in 1)

To deal with values initially out of range, we can return dummy values or null

*/

class MedianFinder {
    private PriorityQueue<Integer> minHeap; // minHeap of bigger elements
    private PriorityQueue<Integer> maxHeap; // maxHeap of smaller elements
    
    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());
    }
    
    public void addNum(int num) { // O(log(size)) time
        if(minHeap.size()>0 && num>=minHeap.peek()) minHeap.add(num);
        else maxHeap.add(num);
        
        int sizeDiff = Math.abs(minHeap.size()-maxHeap.size());
        if(sizeDiff>=2) adjust();
    }
    
    public double findMedian() { // O(1) time
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        Integer minHeapTop = (minHeapSize>0?minHeap.peek():null);
        Integer maxHeapTop = (maxHeapSize>0?maxHeap.peek():null);
        int size = minHeapSize+maxHeapSize;
        
        if(size%2!=0){
            if(minHeapSize>maxHeapSize) return minHeapTop;
            else return maxHeapTop;
        }else{
            return (maxHeapTop+minHeapTop)*1.0/2;
        }
    }
    
    private void adjust(){
        if(maxHeap.size()>minHeap.size()) minHeap.add(maxHeap.remove());
        else maxHeap.add(minHeap.remove());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */