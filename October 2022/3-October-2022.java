// To make rope colorful, remove all consecutive balloons
// except the one with maximum neededTime

// TC : O(len)
// SC : O(1)

class Solution {
    public int minCost(String colors, int[] neededTime) {
        int len = colors.length();
        int minTime = 0;
        int idx = 0;
        
        while(idx < len){
            int currIdx = idx;
            int currTime = neededTime[currIdx];
            int maxTime = neededTime[currIdx];
            
            idx++;
            while(idx<len && colors.charAt(idx)==colors.charAt(currIdx)){
                currTime+=neededTime[idx];
                maxTime = Math.max(maxTime, neededTime[idx]);
                idx++;
            }
            
            minTime+=currTime-maxTime;
        }
        
        return minTime;
    }
}