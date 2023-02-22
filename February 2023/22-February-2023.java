// Binary Search on answer

// TC: O(weights.length*log(high-low))
// SC: O(1)

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;
        
        for(int weight: weights){
            low = Math.max(low, weight);
            high+=weight;
        }
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            if(possibleToLoadWeight(weights, mid, days)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private boolean possibleToLoadWeight(int[] weights, int maxWeight, int days){
        int daysReqd = 1;
        int currWeight = 0;
        
        for(int weight: weights){
            if((currWeight+weight)>maxWeight){
                currWeight=weight;
                daysReqd++;
            }else currWeight+=weight;
        }
        
        return daysReqd<=days;
    }
}