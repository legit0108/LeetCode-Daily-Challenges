// Solution - 1 : DP

// TC : O(n)
// SC : O(n)

class Solution {
    public int numTilings(int n) {
        long[] dp = new long[n+1];
        long mod = (long)1e9+7;
        
        for(int len=0; len<=n; len++){
            if(len<=1) dp[len] = 1;
            else if(len==2) dp[len] = 2;
            else dp[len] = ((2*dp[len-1] + dp[len-3])+mod)%mod;
        }
        
        int ans = (int)(dp[n]%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}

// Solution - 2 : Space optimized DP

// TC : O(n)
// SC : O(1)

class Solution {
    public int numTilings(int n) {
        if(n==0) return 0;
        if(n<=2) return n;
        
        long waysToTile1 = 2;
        long waysToTile2 = 1;
        long waysToTile3 = 1;
        long mod = (long)1e9+7;
        
        for(int len=3; len<=n; len++){
            long waysToTile = ((2*waysToTile1 + waysToTile3)+mod)%mod;
            waysToTile3 = waysToTile2;
            waysToTile2 = waysToTile1;
            waysToTile1 = waysToTile;
        }
        
        int ans = (int)(waysToTile1%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}