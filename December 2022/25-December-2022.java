// Sort -> Prefix Sum -> Binary Search

// TC: O((len + totalQueries) * log(len))
// SC: O(len + totalQueries) (can implement in O(1) space if input modification allowed)

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        int len = nums.length;
        int[] prefixSum = new int[len];
        
        for(int idx=0; idx<len; idx++) prefixSum[idx] = nums[idx];
        
        Arrays.sort(prefixSum);
        
        for(int idx=1; idx<len; idx++) prefixSum[idx]+=prefixSum[idx-1];
        
        int totalQueries = queries.length;
        int[] answer = new int[totalQueries];
        
        for(int idx=0; idx<totalQueries; idx++){
            int sum = queries[idx];
            answer[idx] = binarySearch(prefixSum, len, sum);
        }
        
        return answer;
    }
    
    private int binarySearch(int[] prefixSum, int len, int sum){
        int low = 0;
        int high = len-1;
        int size = 0;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(prefixSum[mid]<=sum){
                size = mid+1;
                low = mid+1;
            }else high = mid-1;
        }
        
        return size;
    }
}