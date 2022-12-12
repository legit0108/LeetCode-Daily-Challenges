// Fibonacci DP

// TC : O(n)
// SC : O(1)

class Solution {
    public int climbStairs(int n) {
        int waysToReachLastStep = 1;
        int waysToReachSecondLastStep = 0;
        
        for(int step=1; step<=n; step++){
            int totalWays = waysToReachLastStep + waysToReachSecondLastStep;
            
            waysToReachSecondLastStep = waysToReachLastStep;
            waysToReachLastStep = totalWays;
        }
        
        return waysToReachLastStep;
    }
}