/* 
 It is not correct to find probability by 
 probability = number of ways/total number of ways in this question
 Reason: Probability of reaching each of the scores by different paths is not same
 
 Ex: 
 for maxPts = 10 and score = 3
 P(1,1,1) = 0.1*0.1*0.1
 P(1,2) = 0.1*0.1
 P(2,1) = 0.1*0.1
 P(3) = 0.1
 
 ans = sum(all events) = P(1,1,1) + P(1,2) + P(2,1) + P(3)
 */

// Solution-1: DP, TLE
// TC: O(n*maxPts)
// SC: O(n)

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        double[] dp = new double[n+1]; // dp[i] = probability of getting i points
        dp[0] = 1.0;
        
        for(int points=1; points<=n; points++){
            double sum = 0;
            
            // we need sum from max(0, i-1) to min(k-1, i-1) to calculate dp[i]
            for(int draw=1; draw<=maxPts; draw++){
                if(points-draw>=0 && points-draw<k) sum+=dp[points-draw]; // probability to reach dp[i] will be sum of all probabilities 
                                                                          // such that a draw on corresponding points leads to dp[i]
            }
            
            dp[points] = sum/maxPts; // 1/2 + 1/2 * 1/2 = 1/2*(1+1/2)
        }
        
        double probability = 0;
        
        for(int points=k; points<=n; points++) probability+=dp[points]; // answer is sum of all probabilities >=k and <=n
        
        return probability;
    }
}


// Solution-2: DP
// Optimize Solution-1, maintain global sum, discard values from sum if those values fall out of range by k

// TC: O(n)
// SC: O(n)

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        double[] dp = new double[n+1];
        dp[0] = 1.0;
        double sum = Math.min(k, 1.0);
        
        for(int points=1; points<=n; points++){
            if(points-maxPts-1>=0 && points-maxPts-1<k) sum-=dp[points-maxPts-1]; // we only need sum of values of dp[i] from max(0, i-maxPts) to min(k-1, i-1)
            dp[points] = sum/maxPts;
            if(points<k) sum+=dp[points];
        }
        
        double probability = 0;
        
        for(int points=k; points<=n; points++) probability+=dp[points];
        
        return probability;
    }
}


// Solution-3: Optimize Solution-2 futher
// Space optimized to O(maxPts)
// Using only one loop

// TC: O(n)
// SC: O(maxPts)

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        double[] dp = new double[maxPts+2];
        double sum = 0;
        double probability = 0;
        
        for(int points=0; points<=n; points++){
            double currProbability = 0;
            
            if(points>0){
                if(points-maxPts-1>=0 && points-maxPts-1<k) sum-=dp[(points-maxPts-1)%(maxPts+1)];
                currProbability = sum/maxPts;
            }else currProbability = 1.0;
            
            dp[points%(maxPts+1)] = currProbability;
            
            if(points<k) sum+=currProbability;
            else probability+=currProbability;
        }
        
        return probability;
    }
}