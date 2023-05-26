// Solution-1: Recursion + Memoization

// TC: O(len*len*len)
// SC: O(len*len)

class Solution {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        Integer[][] dp = new Integer[len][len+1];
        return getMaxStones(piles, dp, 0, piles.length, 1);
    }
    
    private int getMaxStones(int[] piles, Integer[][] dp, int initIndex, int len, int m){
        if(initIndex>=len) return 0;
        if(dp[initIndex][m]!=null) return dp[initIndex][m];
        
        int sum = 0;
        for(int index=initIndex; index<len; index++) sum+=piles[index];
        
        int prefixSum = 0;
        int maxStones = 0;
        
        // try out all possible splits, stones obtained on split = prefixSum + (sum-stones obtained by opponent in next turn)
        // maximize value over all splits 
        
        for(int index=initIndex; index<Math.min(len, initIndex+2*m); index++){
            int pile = piles[index];
            prefixSum+=pile;
            sum-=pile;
            
            maxStones = Math.max(maxStones, prefixSum+(sum-getMaxStones(piles, dp, index+1, len, Math.max(m, index-initIndex+1))));
        }
        
        return dp[initIndex][m] = maxStones;
    }
}


// Solution-2: Tabulation

// TC: O(len*len*len)
// SC: O(len*len)

class Solution {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] prefixSumArr = getPrefixSum(piles, len);
        int[][] dp = new int[len+1][len+1];
        
        for(int initIndex=len-1; initIndex>=0; initIndex--){
           for(int m=1; m<=len; m++){
               int sum = prefixSumArr[len-1];
               if(initIndex>0) sum-=prefixSumArr[initIndex-1];
               int prefixSum = 0;
               int maxStones = 0;
               
               for(int index=initIndex; index<Math.min(len, initIndex+2*m); index++){
                   int pile = piles[index];
                   prefixSum+=pile;
                   sum-=pile;
                   
                   maxStones = Math.max(maxStones, prefixSum+(sum-dp[index+1][Math.max(m, index-initIndex+1)]));
               }
               
               dp[initIndex][m] = maxStones;
           } 
        }
        
        return dp[0][1];
    }
    
    private int[] getPrefixSum(int[] piles, int len){
        int[] prefixSum = new int[len];
        
        for(int index=0; index<len; index++){
            prefixSum[index] = piles[index];
            if(index>0) prefixSum[index]+=prefixSum[index-1];
        }
        
        return prefixSum;
    }
}