// Iterative Binary Search: 
// TC: O(log(n)) (Base 2)
// SC: O(1) 

class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            int val = nums[mid];
            
            if(val==target) return mid;
            else if(val<target) low = mid+1;
            else high = mid-1;
        }
        
        return -1;
    }
}

// Recursive Binary Search: 
// TC: O(log(n)) (Base 2)
// SC: O(log(n)) (Base 2) 

class Solution {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length-1);
    }
    
    private int search(int[] nums, int target, int low, int high){
        if(low>high) return -1;
        
        int mid = low + (high-low)/2;
        
        int val = nums[mid];
        
        if(val==target) return mid;
        else if(val<target) return search(nums, target, mid+1, high);
        else return search(nums, target, low, mid-1);
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


// Ternary Search: 
// TC: O(log(n)) (Base 3)
// SC: O(1)

class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        
        while(low<=high){
            int mid1 = low + (high-low)/3;
            int mid2 = high - (high-low)/3;
            
            int val1 = nums[mid1];
            int val2 = nums[mid2];
            
            if(val1==target) return mid1;
            else if(val2==target) return mid2;
            
            if(target>val2) low = mid2+1;
            else if(target<val1) high = mid1-1;
            else{
                low = mid1+1;
                high = mid2-1;
            }
        }
        
        return -1;
    }
}

/*
 Question: Which is better, Ternary Search or Binary Search?
 
 Ans: Binary Search
 -> Although the number of iterations is less in Ternary Search, 
    comparisons per iteration (4) in Ternary Search 
    is more than the comparisons per iteration (2) in Binary Search
 -> As a result, the total number of comparisons in Ternary Search 
    is more than the total number of comparisons in Binary Search 
    in the worst case, although asymptotically Ternary Search would be better
 -> This is also the reason why we avoid splitting recursion into more than 2 parts
    in divide and conquer algorithms (Although the number of iterations/depth of recursion tree decreases, 
    the number of comparisons (or work per iteration) increases, which leads to more comparisons in the worst case)
*/
