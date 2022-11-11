// TC: O(len)
// SC: O(1)

class Solution {
    public int removeDuplicates(int[] nums) {
        int currIdx = 0;
        int idx = -1;
        int len = nums.length;
        
        while(currIdx<len){
            int val = nums[currIdx];
            
            if(idx==-1 || (nums[idx]!=val)){
                idx++;
                nums[idx] = val;
            }
            
            currIdx++;
        }
        
        int finalLen = idx+1;
        return finalLen;
    }
}