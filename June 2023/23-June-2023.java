class Solution {
    public int longestArithSeqLength(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer>[] dp = new HashMap[len];
        int longestArithSeqLen = 0;
        
        for(int index1=0; index1<len; index1++){
            dp[index1] = new HashMap();
            HashMap<Integer, Integer> map1 = dp[index1];
            
            for(int index2=0; index2<index1; index2++){
                int diff = nums[index1]-nums[index2];
                HashMap<Integer, Integer> map2 = dp[index2];
                
                if(map2.containsKey(diff)) map1.put(diff, Math.max(map1.getOrDefault(diff, 0), map2.get(diff)+1));
                else map1.put(diff, Math.max(map1.getOrDefault(diff, 0), 2));
                
                longestArithSeqLen = Math.max(longestArithSeqLen, map1.get(diff));
            }
        }
        
        return longestArithSeqLen;
    }
}