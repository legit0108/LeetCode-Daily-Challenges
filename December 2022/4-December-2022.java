// Calculate sum of entire array to segregate prefix and suffix to solve the problem in linear time

// TC : O(len)
// SC : O(1)

class Solution {
    public int minimumAverageDifference(int[] nums) {
        int len = nums.length;
        long sum = 0;
        
        for(int idx=0; idx<len; idx++){
            long val = nums[idx];
            sum+=val;
        }
        
        long prefixSum = 0;
        long minAvgDiff = Long.MAX_VALUE;
        int index = -1;
        
        for(int idx=0; idx<len; idx++){
            long val = nums[idx];
            prefixSum+=val;
            long suffixSum = sum-prefixSum;
            
            long prefixLen = idx+1;
            long suffixLen = len-idx-1;
            
            long prefixAvg = (prefixLen==0) ? 0 : prefixSum/prefixLen;
            long suffixAvg = (suffixLen==0) ? 0 : suffixSum/suffixLen;
            
            long currAvgDiff = Math.abs(prefixAvg-suffixAvg);
            if(currAvgDiff < minAvgDiff){
                minAvgDiff = currAvgDiff;
                index = idx;
            }
        }
        
        return index;
    }
}