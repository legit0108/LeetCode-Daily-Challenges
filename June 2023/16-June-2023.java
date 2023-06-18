// TC: O(n^2)
// SC: O(n)

class Solution {
    public int numOfWays(int[] nums) {
        List<Long> list = new ArrayList();
        for(int num: nums) list.add((long)num);
        
        int size = list.size();
        long[] factorial = new long[size+1];
        long mod = (long)1e9+7;
        factorial[0] = 1;
        for(int num=1; num<=size; num++){
            factorial[num] = ((num*factorial[num-1])+mod)%mod;
        }
        
        long ways = getNumOfWays(list, factorial, mod);
        int ans = (int)(((ways-1)+mod)%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
    
    private long getNumOfWays(List<Long> list, long[] factorial, long mod){
        int n = list.size();
        if(n<=2) return 1l;
        
        List<Long> leftSubtreeList = new ArrayList();
        List<Long> rightSubtreeList = new ArrayList();
        long root = list.get(0);
        
        for(long num: list){
            if(num<root) leftSubtreeList.add(num);
            else if(num>root) rightSubtreeList.add(num);
        }
        
        long ways = ((nCr(n-1, leftSubtreeList.size(), mod, factorial)%mod * getNumOfWays(leftSubtreeList, factorial, mod)%mod * getNumOfWays(rightSubtreeList, factorial, mod)%mod)+mod)%mod;
            
        return ways;
    }
    
    private long nCr(int n, int r, long mod, long[] factorial){
        return ((factorial[n]%mod * inv(factorial[r], mod)%mod * inv(factorial[n-r], mod)%mod)+mod)%mod;
    }
    
    private long inv(long val, long mod){
        return pow(val, mod-2, mod)%mod;
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