// Solution-1: DP, Recursion + memoization

// TC: O(high)
// SC: O(high)

class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        long goodStrings = 0;
        long mod = (long)1e9+7;
        Long[] dp = new Long[high+1];
        
        for(int length=low; length<=high; length++){
            long goodStringsOfCurrentLength = getGoodStringOfCurrentLength(length, zero, one, mod, dp);
            goodStrings = ((goodStrings+goodStringsOfCurrentLength)+mod)%mod;
        }
        
        int count = (int)((goodStrings+mod)%mod);
        if(count<0) count+=(int)mod;
        return count;
    }
    
    private long getGoodStringOfCurrentLength(int length, int zero, int one, long mod, Long[] dp){
        if(length==0) return 1;
        if(length<0) return 0;
        if(dp[length]!=null) return dp[length];
        
        long goodStringsOfCurrentLength = ((getGoodStringOfCurrentLength(length-zero, zero, one, mod, dp) // append zeros to all strings having length as length-zero
                                         +getGoodStringOfCurrentLength(length-one, zero, one, mod, dp))+mod)%mod; // append ones to all strings having length as length-one
        
        return dp[length] = goodStringsOfCurrentLength;
    }
}


// Solution-2: DP, Tabulation

// TC: O(high)
// SC: O(high)

class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        long goodStrings = 0;
        long mod = (long)1e9+7;
        long[] dp = new long[high+1];
        dp[0] = 1;
        
        for(int length=1; length<=high; length++){
            appendCharacters(dp, length, zero, mod);
            appendCharacters(dp, length, one, mod);
            
            if(length>=low) goodStrings = ((goodStrings+dp[length])+mod)%mod;
        }
        
        int count = (int)((goodStrings+mod)%mod);
        if(count<0) count+=(int)mod;
        return count;
    }
    
    private void appendCharacters(long[] dp, int length, int characters, long mod){
         if(characters<=length) dp[length] = ((dp[length] + dp[length-characters])+mod)%mod;
    }
}


// Solution-3: DP, Space optimized Tabulation
// Idea: Since we only look back max(zero, one) index from current index, we can maintain a window of size max(zero, one)
// Use modulo to map current index to window index
// Another optimization: start loop from min(low, high) instead of 1

// TC: O(high)
// SC: O(max(zero, one))

class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        long goodStrings = 0;
        long mod = (long)1e9+7;
        int maximumCharacters = Math.max(zero, one);
        long[] dp = new long[maximumCharacters];
        dp[0] = 1;
        
        for(int length=Math.min(zero, one); length<=high; length++){
            long goodStringsOfCurrentLength = 0;
            
            if(length>=zero) goodStringsOfCurrentLength = ((goodStringsOfCurrentLength + dp[(length-zero)%maximumCharacters])+mod)%mod;
            if(length>=one)  goodStringsOfCurrentLength = ((goodStringsOfCurrentLength + dp[(length-one)%maximumCharacters])+mod)%mod;
            dp[length%maximumCharacters] = goodStringsOfCurrentLength;
            
            if(length>=low) goodStrings = ((goodStrings+goodStringsOfCurrentLength)+mod)%mod;
        }
        
        int count = (int)((goodStrings+mod)%mod);
        if(count<0) count+=(int)mod;
        return count;
    }
}