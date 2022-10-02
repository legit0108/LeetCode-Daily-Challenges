// Method-1 : DP

// TC : O(n*target*k)
// SC : O(n*target)

class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        long dp[][] = new long[n+1][target+1];
        long mod = (long)1e9+7;
        
        for(int currDice = 0; currDice<=n; currDice++){
            for(int currTarget = 0; currTarget<=target; currTarget++){
                if(currDice == 0 && currTarget == 0) dp[currDice][currTarget] = 1;
                else if(currDice > 0){
                    long ways = 0;
                    
                    for(int face=1; face<=k; face++){
                        if(currTarget >= face){
                            ways = ((ways%mod) + (dp[currDice-1][currTarget-face]%mod))%mod;
                        }
                    }
                    
                    dp[currDice][currTarget] = ways;
                }
            }
        }
        
        int ans = (int)(dp[n][target]%mod);
        if(ans < 0) ans+=(int)mod;
        return ans;
    }
}

// Method-2 : DP

// TC : O(n*target*k)
// SC : O(target)

class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        long dp[] = new long[target+1];
        long mod = (long)1e9+7;
        
        for(int currDice = 0; currDice<=n; currDice++){
            for(int currTarget = target; currTarget>=0; currTarget--){
                if(currDice == 0 && currTarget == 0) dp[currTarget] = 1;
                else if(currDice > 0){
                    long ways = 0;
                    
                    for(int face=1; face<=k; face++){
                        if(currTarget >= face){
                            ways = ((ways%mod) + (dp[currTarget-face]%mod))%mod;
                        }
                    }
                    
                    dp[currTarget] = ways;
                }
            }
        }
        
        int ans = (int)(dp[target]%mod);
        if(ans < 0) ans+=(int)mod;
        return ans;
    }
}

// Method-3 : DP + Prefix sum + Binary search

// TC : O(n*target*log(k))
// SC : O(target)

class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        long dp[] = new long[target+1];
        long mod = (long)1e9+7;
        
        for(int currDice = 0; currDice<=n; currDice++){
            long newDp[] = new long[target+1];
            
            for(int currTarget = 0; currTarget<=target; currTarget++){
                if(currDice == 0 && currTarget == 0) newDp[currTarget] = 1;
                else if(currDice > 0){
                    long ways = 0;
                    
                    int face = binarySearch(currTarget, 1, k);
                    if(face!=-1){
                        ways = (ways%mod + (dp[currTarget-1]%mod - (currTarget-face-1 >= 0?dp[currTarget-face-1]:0)%mod)%mod)%mod;
                    }
                    
                    newDp[currTarget] = ways;
                    if(currTarget > 0) newDp[currTarget] = (newDp[currTarget]%mod + newDp[currTarget-1]%mod)%mod;
                }
            }
            
            dp = newDp;
        }
        
        int ans = (int)(dp[target]%mod);
        if(ans < 0) ans+=(int)mod;
        return ans;
    }
    
    private int binarySearch(int currTarget, int low, int high){
        int face = -1;
        
        while(low <= high){
            int mid = low + (high-low)/2;
            
            if(mid <= currTarget){
                face = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return face;
    }
}