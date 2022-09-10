// Method-1: DP

// TC: O(k*len)
// SC: O(k*len)

class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(len==0) return 0;
        
        int dp[][] = new int[k+1][len];
        
        for(int transaction = 0;transaction<=k;transaction++){
            int maxProfit = -prices[0];
            
            for(int day = 1;day<len;day++){
                if(transaction==0) dp[transaction][day] = 0;
                else{
                    dp[transaction][day] = dp[transaction][day-1];
                    dp[transaction][day] = Math.max(dp[transaction][day], maxProfit+prices[day]);

                    maxProfit = Math.max(maxProfit, dp[transaction-1][day]-prices[day]);
                }
            }
        }
        
        return dp[k][len-1];
    }
}

// Method-2: Space optimized DP since we only look at immediate states in recurrence

// TC: O(k*len)
// SC: O(len)

class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(len==0) return 0;
        
        int dp[] = new int[len];
        
        for(int transaction = 0;transaction<=k;transaction++){
            int maxProfit = -prices[0];
            
            for(int day = 1;day<len;day++){
                if(transaction==0) dp[day] = 0;
                else{
                    int prevProfit = dp[day];
                    
                    dp[day] = dp[day-1];
                    dp[day] = Math.max(dp[day], maxProfit+prices[day]);

                    maxProfit = Math.max(maxProfit, prevProfit-prices[day]);
                }
            }
        }
        
        return dp[len-1];
    }
}