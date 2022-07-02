// TC : O(hlen*log(hlen)+vlen*log(vlen))
// SC : O(1)

// idea : maximum area you can obtain after cuts is equal to 
// product of maximum adjacent difference of values in 
// horizontalCuts and verticalCuts respectively

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int hlen = horizontalCuts.length;
        long max_h_diff = 0;
        int wlen = verticalCuts.length;
        long max_w_diff = 0;
        long mod = (long)(1e9+7);
        
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        max_h_diff = getMaxDiff(horizontalCuts,hlen,h);
        max_w_diff = getMaxDiff(verticalCuts,wlen,w);
        
        long maxArea = ((max_h_diff%mod)*(max_w_diff%mod))%mod;
        int ans = (int)maxArea;
        if(ans<0)ans+=mod;
        return ans;
    }
    
    private long getMaxDiff(int arr[],int len,int size){
        long maxDiff = 0;
        
        for(int idx=0;idx<=len;idx++){
            if(idx==0){
               maxDiff = Math.max(maxDiff,(long)arr[0]);
            }
            else if(idx<len){
               maxDiff = Math.max(maxDiff,
                            (long)arr[idx]-(long)arr[idx-1]);
            }else{
                maxDiff = Math.max(maxDiff,
                            (long)size-(long)arr[len-1]);
            }
        }
        
        return maxDiff;
    }
}