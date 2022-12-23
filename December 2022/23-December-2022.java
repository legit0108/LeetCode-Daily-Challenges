// Solution-1: DP

// TC: O(days)
// SC: O(days)

class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int[][] dp = new int[days][2]; // dp[day][0] = bought state profit, dp[day][1] = sold state profit
        
        dp[0][0] = -prices[0]; // buy first stock
        
        for(int day=1; day<days; day++){
            int price = prices[day];
            
            if(day-2>=0) dp[day][0] = dp[day-2][1]-price;
            else dp[day][0] = -price;
            dp[day][1] = dp[day-1][0]+price;
            
            dp[day][0] = Math.max(dp[day][0], dp[day-1][0]);
            dp[day][1] = Math.max(dp[day][1], dp[day-1][1]);
        }
        
        return dp[days-1][1];
    }
}

// Solution-2: Space-optimized DP

// TC: O(days)
// SC: O(1)

class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int boughtStateProfit = -prices[0]; // buy first stock
        int soldStateProfit = 0;
        int cooldownStateProfit = 0;
        
        for(int day=1; day<days; day++){
            int price = prices[day];
            
            int currBoughtStateProfit = cooldownStateProfit-price;
            int currSoldStateProfit = boughtStateProfit+price;
            int currCooldownStateProfit = soldStateProfit;
            
            boughtStateProfit = Math.max(boughtStateProfit, currBoughtStateProfit);
            soldStateProfit = Math.max(soldStateProfit, currSoldStateProfit);
            cooldownStateProfit = Math.max(cooldownStateProfit, currCooldownStateProfit);
        }
        
        return soldStateProfit;
    }
}