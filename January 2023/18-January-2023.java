/*
 Kadane's algorithm
 
 Case-1: Subarray takes the middle part, standard Kadane's application
 Case-2: Subarray present between two arrays
 
 max(prefix + suffix) = max(total sum - subarray sum) = total sum + max(-subarray sum) = total sum - min (subarray sum)
 max(subarray circular sum) = max(max subarray sum, total sum - min(subarray sum))
 
 Corner case: Handle all negative elements
 
 TC: O(len), one pass
 SC: O(1)
*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;
        int totalSum = 0;
        int maxSum = -(int)1e9;
        int currMax = 0;
        int minSum = (int)1e9;
        int currMin = 0;
        
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            totalSum+=val;
            
            // max subarray sum
            if(val>currMax+val) currMax = val;
            else currMax+=val;
            
            // min subarray sum
            if(val<currMin+val) currMin = val;
            else currMin+=val;
            
            if(currMax>maxSum) maxSum = currMax;
            if(currMin<minSum) minSum = currMin;
        }
        
        if(maxSum<0) return maxSum;
        else return Math.max(maxSum, totalSum-minSum);
    }
}