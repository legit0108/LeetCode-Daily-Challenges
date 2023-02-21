/* 
 Binary Search
 
 Note: 
 If you observe carefully, you can see that it wouldn't have mattered if the array had been unsorted. 
 The only thing required to apply binary search is that the duplicate elements occur adjacent to each other.
 So the approach works for both sorted & un-sorted arrays, given that duplicates occur adjacently.
 
 TC: O(log(len))
 SC: O(1)
*/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        
        int low = 0;
        int high = len-1;
        int idx = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(mid==0){
                if(nums[mid]==nums[mid+1]) low = mid+2;
                else{
                    idx = mid;
                    break;
                }
            }else if(mid==len-1){
                if(nums[mid]==nums[mid-1]) high = mid-2;
                else{
                    idx = mid;
                    break;
                }
            }else{
                // Idea: Single element lies in that half of the array which contains odd number of elements
                
                int val = nums[mid];
                int elementsInLeftHalf = 0;
                int elementsInRightHalf = 0;
                
                if(val==nums[mid+1]){
                    elementsInLeftHalf = mid;
                    elementsInRightHalf = len-elementsInLeftHalf;
                    
                    if(elementsInRightHalf%2==0) high = mid-1;
                    else low = mid+2;
                }else if(val==nums[mid-1]){
                    elementsInLeftHalf = mid+1;
                    elementsInRightHalf = len-elementsInLeftHalf;
                    
                    if(elementsInLeftHalf%2==0) low = mid+1;
                    else high = mid-2;
                }else{
                    idx = mid;
                    break;
                }
            }
        }
        
        return nums[idx];
    }
}