// TC: O(n)
// SC: O(n)

class Solution {
    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        int[] averages = new int[len];
        Arrays.fill(averages, -1);
        long sum = 0;   
        
        for(int index=0; index<len; index++){
            long num = nums[index];
            sum+=num;
            
            int center = index-k;
            if(center-k>=0){
                averages[center] = (int)(sum/((long)2l*k+1l));
                sum-=(long)nums[center-k];
            }
        }
        
        return averages;
    }
}