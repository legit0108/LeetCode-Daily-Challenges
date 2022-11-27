// DP, dp[idx] stores count of subsequences having some common difference diff and length>=2 

// TC: O(len^2)
// SC: O(len^2)

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        HashMap<Long, Integer> dp[] = new HashMap[len];
        int subsequences = 0;
        
        for(int idx1=0; idx1<len; idx1++){
            dp[idx1] = new HashMap();
            HashMap<Long, Integer> map1 = dp[idx1];
            
            for(int idx2=0; idx2<idx1; idx2++){
                long diff = (long)nums[idx1]-(long)nums[idx2];
                if(diff<=Integer.MIN_VALUE || diff>=Integer.MAX_VALUE) continue;
                
                map1.put(diff, map1.getOrDefault(diff, 0)+1);
                
                HashMap<Long, Integer> map2 = dp[idx2];
                
                if(map2.containsKey(diff)){
                    int freq = map2.get(diff);
                    subsequences+=freq;
                    
                    map1.put(diff, map1.getOrDefault(diff, 0)+freq);
                }
            }
        }
        
        return subsequences;
    }
}