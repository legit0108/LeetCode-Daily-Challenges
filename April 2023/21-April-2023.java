// DP, caveat: if total price exceeds min profit, we reset it to min profit


// Solution-1: Recursion + Memoization
// TC: O(len*n*minProfit)
// SC: O(len*n*minProfit)

class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        long mod = (long)1e9+7;
        int len = group.length;
        Long[][][] dp = new Long[len][n+1][minProfit+1];
        long ways = getProfitableSchemes(0, n, 0, minProfit, mod, group, profit, len, dp);
        
        int schemes = (int)(ways%mod);
        if(schemes<0) schemes+=(int)mod;
        return schemes;
    }
    
    private long getProfitableSchemes(int index, int count, int totalProfit, int minProfit, long mod, int[] group, int[] profit, int len, Long[][][] dp){
        if(index==len || count==0){
            if(totalProfit>=minProfit) return 1;
            else return 0;
        }
        
        if(dp[index][count][totalProfit]!=null) return dp[index][count][totalProfit];

        long skip = getProfitableSchemes(index+1, count, totalProfit, minProfit, mod, group, profit, len, dp);

        int currProfit = profit[index];
        int members = group[index];
        long pick = 0;   
        if(members<=count) pick = getProfitableSchemes(index+1, count-members, Math.min(totalProfit+currProfit, minProfit), minProfit, mod, group, profit, len, dp);

        long ways = ((pick+skip)+mod)%mod;
        return dp[index][count][totalProfit] = ways;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Tabulation
// TC: O(len*n*minProfit)
// SC: O(len*n*minProfit)

class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        long mod = (long)1e9+7;
        int len = group.length;
        long[][][] dp = new long[len+1][n+1][minProfit+1];
        
        for(int index=len; index>=0; index--){
            for(int count=0; count<=n; count++){
                for(int totalProfit=0; totalProfit<=minProfit; totalProfit++){
                    if(index==len || count==0){
                        if(totalProfit>=minProfit) dp[index][count][totalProfit] = 1;    
                    }else{
                        long skip = dp[index+1][count][totalProfit];

                        int currProfit = profit[index];
                        int members = group[index];
                        long pick = 0;   
                        if(members<=count) pick = dp[index+1][count-members][Math.min(totalProfit+currProfit, minProfit)];

                        long ways = ((pick+skip)+mod)%mod;
                        dp[index][count][totalProfit] = ways;
                    }
                }
            }
        }
        
        long ways = dp[0][n][0];
        int schemes = (int)(ways%mod);
        if(schemes<0) schemes+=(int)mod;
        return schemes;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Space-optimized tabulation, we eliminate one dimension from our dp (index depends only on index+1)
// TC: O(len*n*minProfit)
// SC: O(n*minProfit)

class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        long mod = (long)1e9+7;
        int len = group.length;
        long[][] dp = new long[n+1][minProfit+1];
        
        for(int index=len; index>=0; index--){
            for(int count=n; count>=0; count--){
                for(int totalProfit=0; totalProfit<=minProfit; totalProfit++){
                    if(index==len || count==0){
                        if(totalProfit>=minProfit) dp[count][totalProfit] = 1;    
                    }else{
                        long skip = dp[count][totalProfit];

                        int currProfit = profit[index];
                        int members = group[index];
                        long pick = 0;   
                        if(members<=count) pick = dp[count-members][Math.min(totalProfit+currProfit, minProfit)];

                        long ways = ((pick+skip)+mod)%mod;
                        dp[count][totalProfit] = ways;
                    }
                }
            }
        }
        
        long ways = dp[n][0];
        int schemes = (int)(ways%mod);
        if(schemes<0) schemes+=(int)mod;
        return schemes;
    }
}