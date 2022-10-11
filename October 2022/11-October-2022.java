// Solution-1: Compare current value with prefix min and suffix max

// TC: O(len)
// SC: O(len)

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        int suffixMax[] = new int[len];
        
        for(int idx=len-1; idx>=0; idx--){
            suffixMax[idx] = nums[idx];
            if(idx<len-1) suffixMax[idx] = Math.max(suffixMax[idx], suffixMax[idx+1]);
        }
        
        int prefixMin = nums[0];
        for(int idx=1; idx<len; idx++){
            if(idx<len-1 && (prefixMin < nums[idx] && nums[idx] < suffixMax[idx+1])) return true;
            prefixMin = Math.min(prefixMin, nums[idx]);
        }
        
        return false;
    }
}

/*

Solution-2: Constant space

Since we are only interested in finding whether subsequence exists or not
we can greedily solve the problem without caring about the indices of 
increasing triplet subsequence

TC: O(len)
SC: O(1)

*/

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        
        for(int val : nums){
            if(val<first) first = val;
            else if(val>first && val<second) second = val;
            else if(val>first && val>second){
                /*
                
                first < second < val obtained
                since val > second we know that some num must exist such that second>num
                first may or may not be equal to num
                but we do not care about that
                we do not need to find the sequence, we just need to check its existence
                
                */
                
                return true; 
            }
        }
        
        return false;
    }
}