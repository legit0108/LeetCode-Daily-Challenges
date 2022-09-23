// TC : O(n)
// SC : O(1)

class Solution {
    public int concatenatedBinary(int n) {
        long mod = (long)1e9+7;
        long val = 0;
        long len = 0;
        
        for(long num = 1; num<=n; num++){
            if(isPowerOfTwo(num)) len++; // length of binary string increases every time power of two encountered 
            val = (((val << len)%mod) + (num%mod))%mod; // val = (val * 2^len) + num, previous bits shift by len when num is appended
        }
        
        int ans = (int)(val%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private boolean isPowerOfTwo(long num){
        if(num>0 && (num&(num-1)) == 0) return true;
        return false;
    } 
}