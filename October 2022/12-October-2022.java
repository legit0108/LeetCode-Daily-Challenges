// for triangle: a<b<c
// b>a -> b+c>a
// c>b -> a+c>b
// check a+b>c
// check 3 consecutive sides after sorting (ensures max perimeter)

// TC: O(len*log(len))
// SC: O(len)

class Solution {
    public int largestPerimeter(int[] nums) {
        int len = nums.length;
        int maxPerimeter = 0;
        Arrays.sort(nums);
        
        for(int idx=len-1; idx>=2;idx--){
            if(nums[idx-2]+nums[idx-1]>nums[idx]){
                maxPerimeter = nums[idx-2]+nums[idx-1]+nums[idx];
                break;
            }
        }
        
        return maxPerimeter;
    }
}