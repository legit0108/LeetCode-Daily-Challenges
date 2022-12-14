// Solution - 1 : DP, dp[idx] = max money possible you can rob till idx

// TC : O(len)
// SC : O(len)

class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        int dp[] = new int[len];
    
        for(int idx=0; idx<len; idx++){
            int num = nums[idx];
            
            dp[idx] = num + ((idx-2>=0)?dp[idx-2]:0); // include current number
            if(idx-1>=0) dp[idx] = Math.max(dp[idx], dp[idx-1]); // exclude current number
        }
        
        return dp[len-1];
    }
}

// Solution - 2 : Space optimized DP

// TC : O(len)
// SC : O(1)

class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        int maxMoneyInclLastNum = 0;
        int maxMoneyExclLastNum = 0;
    
        for(int idx=0; idx<len; idx++){
            int num = nums[idx];
            
            int maxMoneyInclCurrNum = maxMoneyExclLastNum + num;
            int maxMoneyExclCurrNum = Math.max(maxMoneyExclLastNum, maxMoneyInclLastNum);
            
            maxMoneyInclLastNum = maxMoneyInclCurrNum;
            maxMoneyExclLastNum = maxMoneyExclCurrNum;
        }
        
        return Math.max(maxMoneyInclLastNum, maxMoneyExclLastNum);
    }
}