// TC : O(len)
// SC : O(1)

// idea : nums[idx]>nums[idx-1] , then the sequence wiggles up
// nums[idx]<nums[idx-1] , then the sequence wiggles down

class Solution {
    public int wiggleMaxLength(int[] nums) {
        int incr = 1;
        int decr = 1;
        int len = nums.length;
        
        for(int idx=1;idx<len;idx++){
            if(nums[idx]>nums[idx-1]){
                incr = decr+1;
            }else if(nums[idx]<nums[idx-1]){
                decr = incr+1;
            }
        }
        
        int maxLen = Math.max(incr,decr);
        return maxLen;
    }
}