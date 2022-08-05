// TC : O(len*target)
// SC : O(target)

// Same as Combination Sum , but change the order of loops
// we can further optimize this solution by sorting nums
// so we don't have to loop len times for each target
// Follow up : The problem with negative elements
// is that we can have infinite length sequences that
// sum to target , consider target : 1 and nums : [1,-1]
// possible solutions : [1,-1,1] , [1,-1,1,-1,1] , [1,-1,1,-1,1,-1,1]
// to deal with negative elements we need to set bound to length of sequence

class Solution {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        int len = nums.length;
        dp[0] = 1;
        
        for(int currTarget = 1;currTarget<=target;currTarget++){
            for(int idx = 0;idx<len;idx++){
                if(currTarget>=nums[idx]) dp[currTarget]+=dp[currTarget-nums[idx]];
            }
        }
        
        return dp[target];
    }
}