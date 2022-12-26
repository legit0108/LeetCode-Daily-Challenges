// Solution - 1 : DP

// TC : O(len^2)
// SC : O(len)

class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[len-1] = true;
        
        for(int idx1=len-2; idx1>=0; idx1--){
            int start = idx1+1;
            int end = Math.min(idx1+nums[idx1], len-1);
            
            for(int idx2=start; idx2<=end; idx2++){
                dp[idx1]|=dp[idx2];
            }
        }
        
        return dp[0];
    }
}

// Greedy Solutions

// Solution - 2 : Keep track of maximum index reachable from current index

// TC : O(len)
// SC : O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        
        if(len==1) return true;
        if(nums[0]==0) return false;
        
        int maxReach = nums[0];
        int steps = maxReach;
        
        for(int idx=1; idx<len; idx++){
            if(idx==len-1) return true;
            
            int val = nums[idx];
            int currMaxReach = idx + val;
            if(currMaxReach>maxReach) maxReach = currMaxReach;
            
            steps--;
            if(steps==0){
                if(idx>=maxReach) return false;
                steps = maxReach-idx;
            }
        }
        
        return false;
    }
}

// Solution - 3 : Same idea as Solution 2 but shorter code

// TC : O(len)
// SC : O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if(len==1) return true;
        
        int maxReach = nums[0];
        if(maxReach==0) return false;
        
        for(int idx=1; idx<=maxReach; idx++){
            int currMaxReach = idx+nums[idx];
            
            if(currMaxReach>maxReach) maxReach = currMaxReach;
            
            if(maxReach>=len-1) return true;
        }
        
        return false;
    }
}

// Solution - 4 : Work backwards and find the first index such that you can reach the last index from that index, return true if this index is 0

// TC : O(len)
// SC : O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if(len==1) return true;
        
        int maxReach = nums[0];
        if(maxReach==0) return false;
        
        int lastIdx = len-1;
        
        for(int idx=len-1; idx>=0; idx--){
            int currMaxReach = idx+nums[idx];
            
            if(currMaxReach>=lastIdx) lastIdx = idx;
        }
        
        return lastIdx==0;
    }
}