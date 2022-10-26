// For a subarray to be a multiple of k, remainder of prefix sum with k will repeat

// TC: O(len)
// SC: O(len)

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> remsMap = new HashMap();
        remsMap.put(0, -1);
        int sum = 0;
        int len = nums.length;
        boolean ans = false;
        
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            sum+=val;
            int rem = sum%k;
            
            if(remsMap.containsKey(rem)){
                int subarrayLen = idx-remsMap.get(rem);
                if(subarrayLen>=2){
                    ans = true;
                    break;
                }
            }else remsMap.put(rem, idx);
        }
        
        return ans;
    }
}