// TC : O(nums.length)
// SC : O(nums.length)

// idea : to get longest consecutive sequence
// start building the sequence from its head

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet();
        for(int num : nums) set.add(num);
        int maxLen = 0;
        
        for(int num : set){
            if(!set.contains(num-1)){
                int currNum = num;
                int currLen = 1;
                
                while(set.contains(currNum+1)){
                    currNum++;
                    currLen++;
                }
                
                maxLen = Math.max(maxLen,currLen);
            }
        }
        
        return maxLen;
    }
}