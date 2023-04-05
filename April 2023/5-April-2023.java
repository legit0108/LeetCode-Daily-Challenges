// Solution-0: Brute-Force (TLE)
// For all possible values from 0 to max(nums), return smallest possible value to which all numbers in nums can be converted
 
// TC: O(len*max(nums))
// SC: O(1)

class Solution {
    public int minimizeArrayValue(int[] nums) {
        int len = nums.length;
        int max = getMax(nums, len);
        
        for(int val=0; val<=max; val++){
            if(maxPossible(nums, val, len)) return val;
        }
        
        return -1;
    }
    
    private int getMax(int[] nums, int len){
        int max = Integer.MIN_VALUE;
        
        for(int index=0; index<len; index++) max = Math.max(max, nums[index]);
        
        return max;
    }
    
    private boolean maxPossible(int[] nums, long max, int len){ // returns true if all numbers in nums can be converted to <=max
        long val = nums[len-1]; // current value (maintained as variable to avoid modification of nums)
        
        for(int index=len-1; index>=1; index--){ // we iterate from the back since nums[index] can be increased by a factor obtained from [index+1 .... len-1]
            long nextVal = nums[index-1];
            
            if(val>max){
                long diff = val-max;
                nextVal+=diff;
            }
            
            val = nextVal;
        }
        
        return val<=max; // All values except the first are <=max, return true if the first value is also <= max
    }
}


// Solution-1: Optimize Brute Force with Binary Search
// In our brute force solution, maxPossible is a montonic function, so we can improve the complexity using Binary Search

// TC: O(len*log(max(nums)))
// SC: O(1)

class Solution {
    public int minimizeArrayValue(int[] nums) {
        int len = nums.length;
        int low = 0;
        int high = getMax(nums, len);
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            if(maxPossible(nums, mid, len)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private int getMax(int[] nums, int len){
        int max = Integer.MIN_VALUE;
        
        for(int index=0; index<len; index++) max = Math.max(max, nums[index]);
        
        return max;
    }
    
    private boolean maxPossible(int[] nums, long max, int len){
        long val = nums[len-1];
        
        for(int index=len-1; index>=1; index--){
            long nextVal = nums[index-1];
            
            if(val>max){
                long diff = val-max;
                nextVal+=diff;
            }
            
            val = nextVal;
        }
        
        return val<=max;
    }
}


/* 
 Solution-2: Binary Search 2

 -> maxPossible in Solution-1 can be implemented as follows: prefixSum at every index should be less than or equal to max in consideration * length of array so far
 -> We can convert the entire prefix of nums to a state such that all numbers in nums are <= max in consideration
 if and only if the sum of our prefix is less than sum of prefix obtained by filling all the places of our prefix with max in consideration

 TC: O(len*log(max(nums)))
 SC: O(1)
*/

class Solution {
    public int minimizeArrayValue(int[] nums) {
        int len = nums.length;
        int low = 0;
        int high = getMax(nums, len);
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            if(maxPossible(nums, mid, len)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private int getMax(int[] nums, int len){
        int max = Integer.MIN_VALUE;
        
        for(int index=0; index<len; index++) max = Math.max(max, nums[index]);
        
        return max;
    }
    
    private boolean maxPossible(int[] nums, long max, int len){
        long prefixSum = 0;
        long pivot= 0; // since prefixSum<=(index+1)*max for every index, we can store maximum value for ceil(prefixSum/index) and compare it with max
        
        for(int index=0; index<len; index++){
            prefixSum+=nums[index];
            pivot = Math.max(pivot, (prefixSum+(long)index)/(index+1l)); // ceil(a/b) = (a+b-1)/b
        }
        
        return pivot<=max;
    }
}


// Solution-3: Optimize Solution-2, remove Binary Search
// We can directly return the maximum value of pivot obtained in Solution-2 instead of iterating using Binary Search 

// TC: O(len)
// SC: O(1)

class Solution {
    public int minimizeArrayValue(int[] nums) {
        int len = nums.length;
        long prefixSum = 0;
        int pivot= 0;
        
        for(int index=0; index<len; index++){
            prefixSum+=nums[index];
            pivot = Math.max(pivot, (int)((prefixSum+(long)index)/(index+1l)));
        }
        
        return pivot;
    }
}