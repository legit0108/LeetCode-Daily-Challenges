// TC : O(lenlog(len))
// SC : O(len)

class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int dp[] = new int[len+1];
        
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        
        for(int idx=0;idx<len;idx++){
            int searchIdx = binarySearch(dp,nums[idx]);
            
            if(nums[idx]>dp[searchIdx-1]) dp[searchIdx] = nums[idx];
        }
        
        for(int idx = len;idx>=0;idx--){
            if(dp[idx]!=Integer.MAX_VALUE) return idx;
        }
        
        return -1;
    }
    
    private int binarySearch(int nums[],int val){
        int low = 0;
        int high = nums.length-1;
        
        while(low<high){
            int mid = low + (high-low)/2;
            if(nums[mid]>=val) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
}