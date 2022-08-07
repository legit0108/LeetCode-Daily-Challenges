// TC : O(n)
// SC : O(1)

class Solution {
    public int countVowelPermutation(int n) {
        long ending_a = 1;
        long ending_e = 1;
        long ending_i = 1;
        long ending_o = 1;
        long ending_u = 1;
        long mod = (long)1e9+7;
        
        for(int len = 1;len<n;len++){
            long currEnding_a = (ending_e)%mod;
            long currEnding_e = (ending_a + ending_i)%mod;
            long currEnding_i = (ending_a+ending_e+ending_o+ending_u)%mod;
            long currEnding_o = (ending_i+ending_u)%mod;
            long currEnding_u = (ending_a)%mod;
            
            ending_a = currEnding_a;
            ending_e = currEnding_e;
            ending_i = currEnding_i;
            ending_o = currEnding_o;
            ending_u = currEnding_u;
        }
        
        long totalWays = (ending_a+ending_e+ending_i+ending_o+ending_u)%mod;
        int finalAns = (int)((totalWays)%mod);
        if(finalAns<0) finalAns+=mod;
        return finalAns;
    }
}