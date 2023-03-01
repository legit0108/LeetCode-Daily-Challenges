// Heapsort

// TC: O(lenlog(len))
// SC: O(1)

class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        
        for(int idx=len/2-1; idx>=0; idx--){
            downHeapify(nums, idx, len);
        }
        
        for(int idx=len-1; idx>=0; idx--){
            swap(nums, idx, 0);
            downHeapify(nums, 0, idx);
        }
        
        return nums;
    }
    
    private void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp; 
    }
    
    
    private void downHeapify(int[] nums, int idx, int len){
        while(idx<len){
            int maxIdx = idx;
            
            int childIdx = 2*idx+1;
            if(childIdx<len && nums[childIdx]>nums[maxIdx]) maxIdx = childIdx;
            
            childIdx = 2*idx+2;
            if(childIdx<len && nums[childIdx]>nums[maxIdx]) maxIdx = childIdx;
            
            if(maxIdx!=idx){
                swap(nums, idx, maxIdx);
                idx = maxIdx;
            }else break;
        }
    }
}