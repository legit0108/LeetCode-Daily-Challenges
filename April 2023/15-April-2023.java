// DP
// TC: O(k*s) where s = sum(piles[pilesIndex].size())
// SC: O(piles.size()*k), which can be optimized to O(k)


// Solution-1: Recursion + Memoization

class Solution {
    private Integer[][] dp;
    
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int size = piles.size();
        dp = new Integer[size][k+1];
        return maxValueOfCoins(piles, size-1, k);
    }
    
    private int maxValueOfCoins(List<List<Integer>> piles, int pilesIndex, int k){
        if(k==0 || pilesIndex==-1) return 0;
        if(dp[pilesIndex][k]!=null) return dp[pilesIndex][k];
        
        int maxValue = maxValueOfCoins(piles, pilesIndex-1, k); // skip current pile
        
        List<Integer> pile = piles.get(pilesIndex);
        int prefixSum = 0;
        int pileSize = pile.size();
            
        for(int index=0; index<pileSize && (index+1)<=k; index++){ // pick current pile
            prefixSum+=pile.get(index);
            maxValue = Math.max(maxValue, prefixSum+maxValueOfCoins(piles, pilesIndex-1, k-index-1));
        }
            
        return dp[pilesIndex][k] = maxValue;
    }
}


// Solution-2: Tabulation

class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int size = piles.size();
        int[][] dp = new int[size][k+1];
        
        for(int pilesIndex=0; pilesIndex<size; pilesIndex++){
            for(int coins=1; coins<=k; coins++){
                int maxValue = get(dp, pilesIndex-1, coins, size, k+1);
                    
                List<Integer> pile = piles.get(pilesIndex);
                int prefixSum = 0;
                int pileSize = pile.size();
                    
                for(int index=0; index<pileSize && (index+1)<=coins; index++){
                    prefixSum+=pile.get(index);
                    maxValue = Math.max(maxValue, prefixSum+get(dp, pilesIndex-1, coins-index-1, size, k+1));
                }
                    
                dp[pilesIndex][coins] = maxValue;
            }
        }
        
        return dp[size-1][k];
    }
    
    private int get(int[][] dp, int row, int col, int rows, int cols){
        if(row<0 || col<0 || row>=rows || col>=cols) return 0;
        else return dp[row][col];
    }
}


// Solution-3: Space optimized DP

class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int size = piles.size();
        int[] dp = new int[k+1];
        
        for(int pilesIndex=0; pilesIndex<size; pilesIndex++){
            for(int coins=k; coins>=1; coins--){
                int maxValue = dp[coins];
                    
                List<Integer> pile = piles.get(pilesIndex);
                int prefixSum = 0;
                int pileSize = pile.size();
                    
                for(int index=0; index<pileSize && (index+1)<=coins; index++){
                    prefixSum+=pile.get(index);
                    maxValue = Math.max(maxValue, prefixSum+dp[coins-index-1]);
                }
                    
                dp[coins] = maxValue;
            }
        }
        
        return dp[k];
    }
}