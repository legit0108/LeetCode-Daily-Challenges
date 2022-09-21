// Update even sum on the go

// TC : O(nums.length + queries.length)
// SC : O(queries.length), O(1) excluding output space

class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int totalQueries = queries.length;
        int answer[] = new int[totalQueries];
        int evenSum = 0;
        
        for(int num : nums){
            if(num%2 == 0) evenSum+=num;
        }
        
        for(int idx = 0;idx < totalQueries;idx++){
            int query[] = queries[idx];
            int val = query[0];
            int index = query[1];
            
            int oldVal = nums[index];
            int newVal = nums[index] + val;
            
            if(newVal%2 == 0){
                if(oldVal%2 == 0) evenSum+=val;
                else evenSum+=newVal;
            }else{
                if(oldVal%2 == 0) evenSum-=oldVal;
            }
            
            nums[index] = newVal;
            answer[idx] = evenSum;
        }
        
        return answer;
    }
}