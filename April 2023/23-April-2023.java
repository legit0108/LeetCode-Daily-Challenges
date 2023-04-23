// DP, since k<=1e9 we look at next 10 places to get number of ways


// Solution-1
// TC: O(len)
// SC: O(len)

class Solution {
    public int numberOfArrays(String s, int k) {
        int len = s.length();
        long[] dp = new long[len+1];
        long mod = (long)1e9+7;
        
        dp[len] = 1;
        
        for(int index1=len-1; index1>=0; index1--){
            long num = 0;
            long ways = 0;
            
            for(int index2=index1; index2<=Math.min(len-1, index1+9); index2++){
                num = num*10+(s.charAt(index2)-'0');
                
                if(num>=1 && num<=k) ways = (ways+dp[index2+1]+mod)%mod;
                else break;
            }
            
            dp[index1] = ways;
        }
        
        int numArrays = (int)(dp[0]%mod);
        if(numArrays<0) numArrays+=(int)mod;
        return numArrays;
    }
}


// Solution-2: Space optimized
// TC: O(len)
// SC: O(log(k))

class Solution {
    public int numberOfArrays(String s, int k) {
        int len = s.length();
        int maxLen = getLen(k);
        long[] dp = new long[maxLen];
        long mod = (long)1e9+7;
        
        dp[len%maxLen] = 1;
        
        for(int index1=len-1; index1>=0; index1--){
            long num = 0;
            long ways = 0;
            
            for(int index2=index1; index2<=Math.min(len-1, index1+maxLen-1); index2++){
                num = num*10+(s.charAt(index2)-'0');
                
                if(num>=1 && num<=k) ways = (ways+dp[(index2+1)%maxLen]+mod)%mod;
                else break;
            }
            
            dp[index1%maxLen] = ways;
        }
        
        int numArrays = (int)(dp[0]%mod);
        if(numArrays<0) numArrays+=(int)mod;
        return numArrays;
    }
    
    private int getLen(int k){
        int len = 0;
        
        while(k>0){
            len++;
            k = k/10;
        }
        
        return len;
    }
}