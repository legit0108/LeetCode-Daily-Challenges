// Solution-1: Use temporary cloned nums

// TC: O(len)
// SC: O(len)

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int len = 2*n;
        int[] clonedNums = clone(nums, len);
       
        int ptr1 = 0;
        int ptr2 = n;
        int idx = 0;
        
        while(ptr2<len){
            if(idx%2==0){
                nums[idx] = clonedNums[ptr1];
                ptr1++;
            }else{
                nums[idx] = clonedNums[ptr2];
                ptr2++;
            }
            
            idx++;
        }
        
        return nums;
    }
     
    private int[] clone(int[] nums, int len){
        int[] clonedNums = new int[len]; 
        
        for(int idx=0; idx<len; idx++) clonedNums[idx] = nums[idx];
       
        return clonedNums; 
    }
}


// Solution-2: One pass

// TC: O(len)
// SC: O(len)

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int len = 2*n;
        int[] result = new int[len];
        int ptr1 = 0;
        int ptr2 = n;
        int idx = 0;
        
        while(idx<len){
            result[idx] = nums[ptr1];
            result[idx+1] = nums[ptr2];
            
            idx+=2;
            ptr1++;
            ptr2++;
        }
        
        return result;
    }
}


// Solution-3: Constant space 

// TC: O(len)
// SC: O(1)

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int len = 2*n;
        modify(nums, n);
        
        int idx = 0;
        int ptr = n;
        
        while(idx<len){
            int val = nums[ptr];
            nums[idx] = val/1001;
            nums[idx+1] = val%1001;
           
            idx+=2;
            ptr++;
        }
        
        return nums;
    }
     
    private void modify(int[] nums, int n){
        int idx = 0;
        
        while(idx<n){
            nums[idx+n] = nums[idx]*1001+nums[idx+n]; // can use any number >1000 for modification (constraints)
            idx++;
        }
    }
}


// Solution-4: Bit manipulation
// Largest possible number uses 10 bits, we can fit two numbers in one 32 bit binary representation

// TC: O(len)
// SC: O(1)

class Solution {
    public int[] shuffle(int[] nums, int n) {
        for(int idx=0; idx<n; idx++){
            nums[idx]<<=10;
            nums[idx]|=nums[idx+n];
        }
        
        int len = 2*n;
        int idx = len-1;
        int ptr = n-1;
        
        while(idx>=1){
            int val = nums[ptr];
            
            nums[idx]=val&1023;
            nums[idx-1]=val>>10;
            
            idx-=2;
            ptr--;
        }
        
        return nums;
    }
}