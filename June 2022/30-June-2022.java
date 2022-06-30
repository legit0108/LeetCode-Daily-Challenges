// idea : We have to make all elements equal to some element in the array
// If we chose an element which is not present in nums and try to change 
// all elements to that element 
// then it will increase our moves 
// So now we have to figure out which element to chose as pivot 
// lets chose the minimum element 
// it can be observed that if we chose minimum element as pivot ,
// lets say we get number of moves as 'x' , then if we shift our 
// pivot to 2nd smallest element , 3rd smallest element
// and so on , then 'x' decreases , till we reach the middle element of the 
// array and then 'x' increases if we continue shifting our pivot 
// So the element we have to chose as pivot is equal to 
// median of the array 

// TC : O(lenlog(len))
// SC : O(1)

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int median = nums[len/2];
        int minMoves = 0;
        
        for(int idx=0;idx<len;idx++){
            minMoves+=Math.abs(median-nums[idx]);
        }
        
        return minMoves;
    }
}