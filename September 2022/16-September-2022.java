/*

Method-1: DP

Initial recurrence -> 3 states, start, end, move
Since end-start+1 = numsLen-(move-1), can be reduced to two states start, move
start*move is at max 10^8 so gives MLE
Can further reduce to O(m*m), passes because m<=10^3 

TC: O(multipliersLen*multipliersLen)
SC: O(multipliersLen)

*/

class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        int numsLen = nums.length;
        int multipliersLen = multipliers.length;
        int dp[] = new int[multipliersLen+1];
        
        for(int move = multipliersLen; move>=1; move--){
            for(int start = 0; start<move; start++){
                // to calculate end: 
                // end-start+1 = numsLen-(move-1)
                // end=numsLen-(move-1)+start-1
                
                int idx = move-1;
                int end = numsLen-(move-1)+start-1;
                
                int score1 = nums[start] * multipliers[idx] + dp[start+1];
                int score2 = nums[end] * multipliers[idx] + dp[start];

                dp[start] = Math.max(score1, score2);
            }
        }
        
        return dp[0];
    }
}