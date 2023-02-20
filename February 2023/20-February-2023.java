// Binary Search

// TC: O(log(len))
// SC: O(1)

class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int low = 0;
        int high = len-1;
        int index = len; // initialize index with len to handle case when target is greater than all numbers
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(nums[mid]>=target){ 
                index = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return index;
    }
}