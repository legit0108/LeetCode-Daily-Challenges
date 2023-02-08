// Solution-1: DP, explore all possibilities

// TC: O(len^2)
// SC: O(len)

class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        
        for(int idx1=len-2; idx1>=0; idx1--){
            int maxJump = nums[idx1];
            int minMoves = (int)1e9;
            
            for(int idx2=idx1+1; idx2<=Math.min(idx1+maxJump, len-1); idx2++){
                minMoves = Math.min(minMoves, 1+dp[idx2]);   
            }
            
            dp[idx1] = minMoves;
        }
        
        return dp[0];
    }
}


// Solution-2: Greedy, jump as far as possible to minimize jumps

// TC: O(len)
// SC: O(1)

class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        
        if(len==1) return 0;
        
        int currMaxIdx = nums[0]; // maximum index we could reach in last jump
        int nextMaxIdx = -1; // maximum index we can reach in next jump
        int jumps = 1;
        
        for(int idx=1; idx<len; idx++){
            int num = nums[idx];
            
            if(idx>currMaxIdx){ // perform "lazy" jump
                currMaxIdx = nextMaxIdx;
                nextMaxIdx = -1;
                jumps++;
            }
            
            nextMaxIdx = Math.max(nextMaxIdx, idx+num); // idx<=currMaxIdx, update nextMaxIdx
        }
        
        return jumps;
    }
}