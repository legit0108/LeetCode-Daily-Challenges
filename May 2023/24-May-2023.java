// Solution-1: We sort on basis of decreasing order of nums2, such that every element of nums2 gets a chance to become minimum element
// We then take sum of top k maximum elements (all such elements before current index) 

// TC: O(nlogn)
// SC: O(n)

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int len = nums1.length;
        
        Integer[] indices = new Integer[len];
        for(int index=0; index<len; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(nums2[index2], nums2[index1]));
        
        long sum = 0;
        long maximumScore = 0;
        PriorityQueue<Long> minHeap = new PriorityQueue();
        
        for(int position=0; position<len; position++){
            int index = indices[position];
            long val = nums1[index];
            sum+=val;
            minHeap.add(val);
            
            if(position-k>=0) sum-=minHeap.remove(); // remove element with min val
            
            if(position-k+1>=0) maximumScore = Math.max(maximumScore, sum*(long)nums2[index]);
        }
        
        return maximumScore;
    }
}


// Solution-2: 
/* 
 We sort on basis of decreasing order of nums1, to maximize our sum
 We then take top of heap to multiply, we use a minheap to maintain minimum of k elements
 When size exceeds k, we will remove minimum nums2[index]
 This is optimal because let's say you remove some other element, 
 in that case your multiplier will still remain same or decrease, whereas your sum will always decrease
 since we are processing elements from largest to smallest
*/ 

// TC: O(nlogn)
// SC: O(n)

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int len = nums1.length;
        
        Integer[] indices = new Integer[len];
        for(int index=0; index<len; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(nums1[index2], nums1[index1]));
        
        long sum = 0;
        long maximumScore = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((index1, index2)->Integer.compare(nums2[index1], nums2[index2]));
        
        for(int position=0; position<len; position++){
            int index = indices[position];
            long val = nums1[index];
            sum+=val;
            minHeap.add(index);
            
            if(position-k>=0) sum-=nums1[minHeap.remove()];
            
            if(position-k+1>=0) maximumScore = Math.max(maximumScore, sum*(long)nums2[minHeap.peek()]);
        }
        
        return maximumScore;
    }
}