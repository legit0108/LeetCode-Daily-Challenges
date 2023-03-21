// Zero-filled subarrays ending at each index = count of zeros till that index 
// Total subarrays = zero filled subarrays overall indices

// TC: O(len)
// SC: O(1)

class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int len = nums.length;
        int index = 0;
        long zeroCount = 0;
        long subarrays = 0;
        
        while(index<len){
            if(nums[index]==0) zeroCount++;
            else zeroCount = 0;
            
            subarrays+=zeroCount;
            
            index++;
        }
        
        return subarrays;
    }
}