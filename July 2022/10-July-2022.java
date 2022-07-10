// TC : O(len)
// SC : O(1)

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        
        int secondLast = cost[len-1];
        int last = cost[len-2];
            
        for(int idx=len-3;idx>=0;idx--){
            int curr = cost[idx] + Math.min(last,secondLast);
            secondLast = last;
            last = curr;
        }
        
        int ans = Math.min(last,secondLast);
        return ans;
    }
}