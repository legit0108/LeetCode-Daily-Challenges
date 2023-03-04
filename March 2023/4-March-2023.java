// Derived from: https://leetcode.com/problems/count-subarrays-with-fixed-bounds/discuss/2707995/Simple-O(n)-C%2B%2B-solution

// TC: O(len)
// SC: O(1)

class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int len = nums.length;
        int minKIdx = -1; // last index of minK
        int maxKIdx = -1; // last index of maxK
        int start = 0; // last valid position from where we can start fixed-bound subarray
        long count = 0;
        
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            
            if(val<minK || val>maxK){ // we can never form fixed-bound subarray with current index, start from next index
                
                // reset minK and maxK indices
                minKIdx = -1;
                maxKIdx = -1;
                
                // next index is potential start of fixed-bound subarray
                start = idx+1;
            }else{
                if(val==minK) minKIdx = idx;
                if(val==maxK) maxKIdx = idx;
                
                if(minKIdx!=-1 && maxKIdx!=-1){
                    int minIdx = Math.min(minKIdx, maxKIdx);
                    long subarrays = minIdx-start+1; /* If we have valid indices for minK and maxK, 
                                                       the number of fixed bound subarrays will be min(minKIdx, maxKIdx)-start+1, 
                                                       we take min since we want to include both minK and maxK */
                    count+=subarrays;
                }
            }
        }
        
        return count;
    }
}