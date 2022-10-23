// Solution-1: Brute force, double for loop or hashmap
// TC: O(len*len) or O(len), extra space used

// Solution-2: Modify input
// TC: O(len)
// SC: O(1)

class Solution {
    public int[] findErrorNums(int[] nums) {
       int len = nums.length;
       int repeating = -1;
       int missing = -1;
        
       for(int idx=0; idx<len; idx++){
           int absVal = Math.abs(nums[idx]);
           int index = Math.abs(absVal)-1;
           
           if(nums[index]<0){
               repeating = absVal; 
           }else nums[index] = -nums[index];
       }
        
       for(int idx=0; idx<len; idx++){
           if(nums[idx]>0) missing = idx+1;
       }
        
       return new int[]{repeating, missing};  
    }
}

// Solution-3: Bit manipulation
// TC: O(n)
// SC: O(1)

class Solution {
    public int[] findErrorNums(int[] nums) {
        int mask = 0;
        int n = nums.length;
        
        for(int num=1; num<=n; num++) mask^=num;
        for(int num : nums) mask^=num;
        
        mask = mask&(-mask);
        
        int num1 = 0;
        int num2 = 0;
        
        for(int num=1; num<=n; num++){
            if((num&mask)==0) num1^=num;
            else num2^=num;
        }
        
        for(int num : nums){
            if((num&mask)==0) num1^=num;
            else num2^=num;
        }
        
        int repeating = -1;
        int missing = -1;
        
        for(int num : nums){
            if(num==num1){
                repeating = num1;
                missing = num2;
            }else if(num==num2){
                repeating = num2;
                missing = num1;
            }
        }
        
        return new int[]{repeating, missing};
    }
}

// Solution-4: Math, one pass: https://leetcode.com/problems/set-mismatch/discuss/1089475/Python-O(n)-timeO(1)-space-math-solution-explained

// TC: O(n)
// SC: O(1)

class Solution {
    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        long n = len;
        long sumOfNums = 0;
        long sumOfSquareNums = 0;
        
        for(int num: nums){
            sumOfNums+=(long)num;
            sumOfSquareNums+=(long)num*(long)num;
        }
        
        long a = n*(n+1l)/2l - sumOfNums;
        long b = n*(n+1l)*(2l*n+1l)/6l - sumOfSquareNums;
            
        long missing = (b/a + a)/2l;
        long repeating = missing-a;
        
        return new int[]{(int)repeating, (int)missing};
    }
}