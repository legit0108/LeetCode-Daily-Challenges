/*
 Solution-1: Sort + Binary Search + pow

 -> For every index, find lastValidIndex, i.e., nums[index] + nums[lastValidIndex]<=target
 -> Number of subsequences = 2^(lastValidIndex-index)
    
    Valid subsequences = 2^(lastValidIndex-index-1) + 2^(lastValidIndex-index-2) + ...... + 2^0 + 1
    = sum of GP + 1 = 2^(lastValidIndex-index)

    OR

    For each number in [index+1, lastValidIndex], we have two options -> pick or skip

 TC: O(len*log(len))
 SC: O(sort)
*/

class Solution {
    public int numSubseq(int[] nums, int target) {
        int len = nums.length;
        int index = 0;
        
        Arrays.sort(nums);
        long subseqCount = 0;
        long mod = (long)1e9+7;
        
        while(index<len){
            int lastValidIndex = binarySearch(nums, index, len, target);
            
            if(lastValidIndex!=-1){
                long count = lastValidIndex-index;
                
                subseqCount+=(pow(2, count, mod)+mod)%mod;
            }
            
            index++;
        }
        
        int ans = (int)(subseqCount%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private int binarySearch(int[] nums, int index, int len, int target){
        int lastValidIndex = -1;
        int low = index;
        int high = len-1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(nums[index]+nums[mid]<=target){
                lastValidIndex = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return lastValidIndex;
    }
    
    private long pow(long x, long n, long mod){
        long ans = 1;
        
        while(n>0){
            if(n%2!=0) ans = ((ans*x)+mod)%mod;
            x = ((x*x)+mod)%mod;
            n = n/2;
        }
        
        return ans;
    }
}


/*
 Solution-2: Sort + Two pointers + pow

 -> We can find lastValidIndex for every index using two pointers technique

 TC: O(len*log(len))
 SC: O(sort)
*/

class Solution {
    public int numSubseq(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len-1;
        
        Arrays.sort(nums);
        long subseqCount = 0;
        long mod = (long)1e9+7;
        
        while(start<=end){
            int sum = nums[start]+nums[end];
            
            if(sum<=target){
                long count = end-start;
                
                subseqCount+=(pow(2, count, mod)+mod)%mod;
                start++;
            }else end--;
        }
        
        int ans = (int)(subseqCount%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long pow(long x, long n, long mod){
        long ans = 1;
        
        while(n>0){
            if(n%2!=0) ans = ((ans*x)+mod)%mod;
            x = ((x*x)+mod)%mod;
            n = n/2;
        }
        
        return ans;
    }
}