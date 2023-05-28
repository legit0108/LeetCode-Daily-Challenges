// MCM Variation
// TC:  O(m^3)
// SC:  O(m^2)
// where m = cuts.length

// Solution-1: Using Map

class Solution {
    public int minCost(int n, int[] cuts) {
        HashMap<String, Integer> dp = new HashMap();
        return getMinCost(0, n, cuts, dp);
    }
    
    private int getMinCost(int start, int end, int[] cuts, HashMap<String, Integer> dp){
        String key = start+","+end;
        if(dp.get(key)!=null) return dp.get(key);
        
        int len = end-start;
        int minCost = (int)1e9;
        
        for(int cut: cuts){
            if(cut>start && cut<end){
                minCost = Math.min(minCost, (end-start) + getMinCost(start, cut, cuts, dp) + getMinCost(cut, end, cuts, dp));
            }
        }
        
        if(minCost==(int)1e9) minCost = 0;
        dp.put(key, minCost);
        
        return minCost;
    }
}


// Solution-2: Using Memoization

class Solution {
    public int minCost(int n, int[] cuts) {
        int len = cuts.length;
        
        int[] newCuts = new int[len+2];
        newCuts[0] = 0;
        newCuts[len+1] = n;
        for(int index=0; index<len; index++){
            newCuts[index+1] = cuts[index];
        }
        
        Integer[][] dp = new Integer[len+2][len+2];
        return getMinCost(0, len+1, newCuts, dp);
    }
    
    private int getMinCost(int startIndex, int endIndex, int[] cuts, Integer[][] dp){
        if(dp[startIndex][endIndex]!=null) return dp[startIndex][endIndex];
        
        int start = cuts[startIndex];
        int end = cuts[endIndex];
        int len = end-start;
        int minCost = (int)1e9;
        
        for(int index=0; index<cuts.length; index++){
            int cut = cuts[index];
            
            if(cut>start && cut<end){
                minCost = Math.min(minCost, len+getMinCost(startIndex, index, cuts, dp) + getMinCost(index, endIndex, cuts, dp));
            }
        }
        
        if(minCost==(int)1e9) minCost = 0;
        dp[startIndex][endIndex] = minCost;
        
        return minCost;
    }
}


// Solution-3: Using Tabulation

class Solution {
    public int minCost(int n, int[] cuts) {
        int len = cuts.length;
        
        int[] newCuts = new int[len+2];
        newCuts[0] = 0;
        newCuts[len+1] = n;
        for(int index=0; index<len; index++){
            newCuts[index+1] = cuts[index];
        }
        
        Arrays.sort(newCuts);
        int[][] dp = new int[len+2][len+2];
        
        for(int gap=0; gap<len+2; gap++){
            for(int startIndex=0, endIndex=gap; endIndex<len+2; endIndex++, startIndex++){
                if(gap<=1) continue;
                else{
                    int start = newCuts[startIndex];
                    int end = newCuts[endIndex];
                    
                    int currLen = end-start;
                    int minCost = (int)1e9;

                    for(int index=startIndex; index<=endIndex; index++){
                        int cut = newCuts[index];

                        if(cut>start && cut<end){
                            minCost = Math.min(minCost, currLen+dp[startIndex][index]+dp[index][endIndex]);
                        }
                    }

                    if(minCost==(int)1e9) minCost = 0;
                    dp[startIndex][endIndex] = minCost; 
                }
            }
        }
        
        return dp[0][len+1];
    }
}