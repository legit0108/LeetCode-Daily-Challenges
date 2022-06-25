// TC : O(len)
// SC : O(1)

class Solution {
    public boolean checkPossibility(int[] nums) {
        boolean modified = false;
        int len = nums.length;
        
        for(int idx=0;idx<len-1;idx++){
            if(nums[idx]>nums[idx+1]){
                if(modified) return false;
                
                modified = true;
                
                //[2,4,1]
                // modify nums[idx+1] = nums[idx]
                if(idx-1>=0&&nums[idx+1]<nums[idx-1]){
                    idx++;
                    
                    //[2,4,1,3]
                    if(idx+1<len&&nums[idx+1]<nums[idx-1]) return false; 
                }
                //else modify nums[idx] = nums[idx+1]  
            }
        }
        
        return true;
    }
}