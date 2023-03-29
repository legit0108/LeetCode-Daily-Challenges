// Solution-1: DP, recursion + memoization
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction); // it is optimal to process elements in sorted order to increase max sum (multiply higher value with higher time)
        int len = satisfaction.length;
        Integer[][] dp = new Integer[len][len+1];
        
        return getMaxSum(0, 1, len, satisfaction, dp);
    }
    
    private int getMaxSum(int index, int time, int len, int[] satisfaction, Integer[][] dp){
        if(index>=len) return 0;
        
        if(dp[index][time]!=null) return dp[index][time];
        
        int maxSum = getMaxSum(index+1, time, len, satisfaction, dp); // leave satisfaction[index]
        maxSum = Math.max(maxSum, satisfaction[index]*time+getMaxSum(index+1, time+1, len, satisfaction, dp)); // pick satisfaction[index]
        
        return dp[index][time] = maxSum;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: DP, tabulation
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        
        int len = satisfaction.length;
        int[][] dp = new int[len+1][len+2];
        
        for(int index=len; index>=0; index--){
            for(int time=1; time<=len; time++){
                if(index==len) dp[index][time] = 0;
                else{
                    int maxSum = dp[index+1][time]; // leave satisfaction[index]
                    maxSum = Math.max(maxSum, satisfaction[index]*time+dp[index+1][time+1]); // pick satisfaction[index]
                    
                    dp[index][time] = maxSum;
                }
            }
        }
        
        return dp[0][1];
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: DP, tabulation, space-optimized
// TC: O(len*len)
// SC: O(len)

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        
        int len = satisfaction.length;
        int[] dp = new int[len+2];
        
        for(int index=len; index>=0; index--){
            for(int time=1; time<=len; time++){
                if(index==len) dp[time] = 0;
                else{
                    int maxSum = dp[time]; // leave satisfaction[index]
                    maxSum = Math.max(maxSum, satisfaction[index]*time+dp[time+1]); // pick satisfaction[index]
                    
                    dp[time] = maxSum;
                }
            }
        }
        
        return dp[1];
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-4: Greedy
 TC: O(len*log(len))
 SC: O(1)
 
 Ideas: 
 
 -> To get maximum sum, multiply higher values of satisfaction later on as time increases
 -> Once currSum<maxSum, it is never possible to get a higher currSum than maxSum
 
 Proof:
 
 [a1, a2, a3, a4, a5, a6]
 
 a3*1 + a4*2 + a5*3 + a6*4 < a4*1 + a5*2 + a6*3 -> (1)  (Note that this will happen when a3 is negative)
 or 
 a3 + a4 + a5 + a6 < 0 -> (2)
 
 Assume that configuration starting from a2 produces higher sum than a4*1 + a5*2 + a6*3
 a2*1 + a3*2 + a4*3 + a5*4 + a6*5
 = a2 (negative) + a3 + a4 + a5 + a6 (negative from (2)) + a3*1 + a4*2 + a5*3 + a6*4 (less than a4*1 + a5*2 + a6*3 from (1))
 = negative value + negative value + value less than a4*1 + a5*2 + a6*3
 = value less than a4*1 + a5*2 + a6*3, our assumption is wrong
 
 Let's say that you skip a3 and chose a2, this will also never be more than a4*1 + a5*2 + a6*3 since the array is sorted from left to right (it is always optimal to choose a higher element)
*/

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int len = satisfaction.length;
        int index = len-1;
        int sum = 0;
        int maxSum = 0;
        
        while(index>=0){
            int satisfactionLevel = satisfaction[index];
            int currSum = satisfactionLevel+maxSum+sum; // a3*1 + a4*2 + a5*3 + a6*4 = a3 + a4*1 + a5*2 + a6*3 + a4 + a5 + a6
            
            if(currSum<maxSum) break;
            else maxSum = currSum;
            
            sum+=satisfactionLevel;
            index--;
        }
        
        return maxSum;
    }
}