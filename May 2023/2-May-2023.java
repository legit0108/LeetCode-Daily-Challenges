// Brute force: Calculate product and return sign of the product -> Leads to overflow


// Solution-1: Track count of negative numbers
// TC: O(nums.length)
// SC: O(1)

class Solution {
    public int arraySign(int[] nums) {
        int negativeNumbers = 0;
        boolean zeroFound = false;
        
        for(int num: nums){
            if(num<0) negativeNumbers++;
            else if(num==0) zeroFound = true;
        }
        
        if(zeroFound) return 0;
        else if(negativeNumbers%2==0) return 1;
        else return -1;
    }
}


// Solution-2: Track sign of product
// TC: O(nums.length)
// SC: O(1)

class Solution {
    public int arraySign(int[] nums) {
        int sign = 1;
        
        for(int num: nums){
            sign*=getSign(num);
        }
        
        return sign;
    }
    
    private int getSign(int num){
        if(num>0) return 1;
        else if(num<0) return -1;
        else return 0;
    }
}