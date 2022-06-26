// Problem translation : find minimum subarray sum for a window of size len-k
// maximum sum we can obtain = sum-minSum

// TC : O(len)
// SC : O(1)

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        k = len-k;
        int minSum = 0;
        int currSum = 0;
        int sum = 0;
        int idx = 0;
        
        while(idx<len){
            currSum+=cardPoints[idx];
            sum+=cardPoints[idx];
            if(idx>=k) currSum-=cardPoints[idx-k];
            
            if(idx<k) minSum = currSum;
            else minSum = Math.min(minSum,currSum);
            idx++;
        }
        
        return sum-minSum;
    }
}