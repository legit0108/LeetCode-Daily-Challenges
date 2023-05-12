// Solution-1: DP, Reursion + memoization

// TC: O(len)
// SC: O(len)

class Solution {
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        Long[] dp = new Long[len];
        return getMostPoints(questions, 0, len, dp);
    }
    
    private long getMostPoints(int[][] questions, int index, int len, Long[] dp){
        if(index>=len) return 0;
        if(dp[index]!=null) return dp[index];
        
        int[] question = questions[index];
        long points = question[0];
        int brainpower = question[1];
        
        long pick = points+getMostPoints(questions, index+brainpower+1, len, dp);
        long skip = getMostPoints(questions, index+1, len, dp);
        
        return dp[index] = Math.max(pick, skip);
    }
}


// Solution-2: DP, Tabulation

// TC: O(len)
// SC: O(len)

class Solution {
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len+1];
        
        for(int index=len-1; index>=0; index--){
            int[] question = questions[index];
            long points = question[0];
            int brainpower = question[1];
            
            dp[index] = Math.max(dp[index+1], points+dp[Math.min(len, index+brainpower+1)]);
        }
        
        return dp[0];
    }
}


// Solution-3: DP, Tabulation, Left to right

// TC: O(len)
// SC: O(len)

class Solution {
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len+1];
        
        for(int index=0; index<len; index++){
            int[] question = questions[index];
            long points = question[0];
            int brainpower = question[1];

            // pick
            int nextIndex = Math.min(len, index+brainpower+1);
            dp[nextIndex]=Math.max(dp[nextIndex], dp[index]+points);
            
            // skip
            dp[index+1] = Math.max(dp[index], dp[index+1]);
        }
        
        return dp[len];
    }
}