// Minimize sum buy making a mountain array, increasing to a peak value and then decreasing
// Peak value will be maxSum

// TC: O(log(maxSum))
// SC: O(1)

class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int low = 1;
        int high = maxSum;
        int ans = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(isPossible(mid, index, n, maxSum)){
                ans = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return ans;
    }
    
    private boolean isPossible(long maxVal, int index, int n, int maxSum){
        long sum = 0;
        
        if(maxVal==index+1){
            sum+=maxVal*(maxVal+1l)/2l;
        }else if(maxVal<index+1){
            sum+=maxVal*(maxVal+1l)/2l+(index+1-maxVal);
        }else{
            sum+=maxVal*(maxVal+1l)/2l-(((maxVal-1l-(long)index)*(maxVal-(long)index))/2l);    
        }
        
        int remaining = n-index-1;
        
        if(remaining==maxVal-1){
            sum+=(maxVal-1l)*maxVal/2l;
        }else if(remaining<maxVal-1){
            sum+=((maxVal-1l)*maxVal/2l) - (((maxVal-1l-remaining)*(maxVal-remaining))/2l);
        }else{
            sum+=(maxVal-1l)*maxVal/2l + remaining-(maxVal-1l);
        }
        
        return sum<=maxSum;
    }
}