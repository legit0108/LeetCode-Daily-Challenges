// TC: O(len^2)
// SC: O(1)

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        Integer sum = null;
        
        for(int idx=len-1; idx>=0; idx--){
            int start = 0;
            int end = idx-1;
            
            while(start<end){
                int currSum = nums[start]+nums[end]+nums[idx];
                if(sum==null || Math.abs(currSum-target)<Math.abs(sum-target)) sum = currSum;
                
                if(currSum>target) end--;
                else start++;
            }
        }
        
        return (int)sum;
    }
}